package com.wj.ackfalse;

import com.rabbitmq.client.*;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class AckFlaseConsumer1 {


    public static final String EXCHANGE_NAME = "ackfalse";

    public static void main(String[] args) throws IOException, TimeoutException {
        ConnectionFactory connectionFactory = new ConnectionFactory();
        connectionFactory.setHost("10.0.0.141");
        connectionFactory.setUsername("admin");
        connectionFactory.setPassword("admin");
        Connection connection = connectionFactory.newConnection();
        //信道
        final Channel channel = connection.createChannel();
        channel.exchangeDeclare(EXCHANGE_NAME,"direct");
        //声明一个队列
        String queueName = "focuserror";
        channel.queueDeclare(queueName,false,false,false,null);
        //绑定
        String routeKey = "error";
        channel.queueBind(queueName,EXCHANGE_NAME,routeKey);


        System.out.println("waiting for msg....");

        final Consumer consumer = new DefaultConsumer(channel) {

            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                String msg = new String(body,"utf-8");
                System.out.println("received[" + envelope.getRoutingKey() + "]" + msg);
                channel.basicAck(envelope.getDeliveryTag(),false);   //单条确认
             }
        };
        //消费者开始消费数据
        channel.basicConsume(queueName,false,consumer);



    }
}
