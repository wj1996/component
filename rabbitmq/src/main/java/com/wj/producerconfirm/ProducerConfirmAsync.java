package com.wj.producerconfirm;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.ConfirmListener;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class ProducerConfirmAsync {


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



        //发送消息
        String[] serverties = {"error","info","warning"};
        /**
         * 启用发送者确认模式
         *  跟消费者没有任何关系
         *
         *
         */

        channel.confirmSelect();
        channel.addConfirmListener(new ConfirmListener() {
            public void handleAck(long l, boolean b) throws IOException {
                System.out.println("deliveryTag:" + l + ",multi:" + b);
            }

            public void handleNack(long l, boolean b) throws IOException {

            }
        });
        for (int i = 0; i < 3; i++) {
            String server = serverties[i % 3];
            String msg = "hello rabbitmq " + (i + 1);
            channel.basicPublish(EXCHANGE_NAME,server,true,null,msg.getBytes());
        }

        channel.close();
        connection.close();
    }
}
