package com.wj.producerconfirm;

import com.rabbitmq.client.*;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * 批量异步
 */
public class ProducerBatchConfirmAsync {


    public static final String EXCHANGE_NAME = "confirm_logs";

    public static void main(String[] args) throws IOException, TimeoutException, InterruptedException {
        ConnectionFactory connectionFactory = new ConnectionFactory();
        connectionFactory.setHost("10.0.0.141");
        connectionFactory.setUsername("admin");
        connectionFactory.setPassword("admin");
        Connection connection = connectionFactory.newConnection();
        //信道
        Channel channel = connection.createChannel();
        channel.exchangeDeclare(EXCHANGE_NAME,"direct");


        channel.addConfirmListener(new ConfirmListener() {
            public void handleAck(long l, boolean b) throws IOException {
                System.out.println("deliveryTag:" + l + ",multi:" + b);
            }

            public void handleNack(long l, boolean b) throws IOException {

            }
        });

        channel.addReturnListener(new ReturnListener() {
            public void handleReturn(int i, String s, String s1, String s2, AMQP.BasicProperties basicProperties, byte[] bytes) throws IOException {
                System.out.println("RabbitMq路由失败：" + s2 + ",message:" + new String(bytes));
            }
        });


        //发送消息
        String[] serverties = {"error","info"};
        //启用发送者确认模式
        channel.confirmSelect();
        for (int i = 0; i < 100; i++) {
            String server = serverties[i % 2];
            String msg = "hello rabbitmq " + (i + 1);
            channel.basicPublish(EXCHANGE_NAME,server,true,null,msg.getBytes());
        }
        channel.waitForConfirmsOrDie();

        channel.close();
        connection.close();
    }
}
