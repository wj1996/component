package com.wj.backupexchange;

import com.rabbitmq.client.*;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class BackupExchangeConsumer {


    public static final String EXCHANGE_NAME = "alternate-exchange";

    public static void main(String[] args) throws IOException, TimeoutException {
        ConnectionFactory connectionFactory = new ConnectionFactory();
        connectionFactory.setHost("10.0.0.141");
        connectionFactory.setUsername("admin");
        connectionFactory.setPassword("admin");
        Connection connection = connectionFactory.newConnection();
        //信道
        Channel channel = connection.createChannel();
        channel.exchangeDeclare(EXCHANGE_NAME,BuiltinExchangeType.FANOUT);
        //声明一个队列
        String queueName = "focuserror";
        channel.queueDeclare(queueName,false,false,false,null);

        System.out.println("backupExchange waiting for msg....");

        final Consumer consumer = new DefaultConsumer(channel) {

            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                String msg = new String(body,"utf-8");
                System.out.println("backupExchange received[" + envelope.getRoutingKey() + "]" + msg);
            }
        };
        //消费者开始消费数据
        channel.basicConsume(queueName,true,consumer);



    }
}
