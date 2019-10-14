package com.wj.exchange.direct;

import com.rabbitmq.client.*;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class MultiBindConsumer {

    public static final String EXCHANGE_NAME = "direct_logs";

    public static void main(String[] args) throws IOException, TimeoutException {
        ConnectionFactory connectionFactory = new ConnectionFactory();
        connectionFactory.setHost("10.0.0.141");
        connectionFactory.setUsername("admin");
        connectionFactory.setPassword("admin");
        Connection connection = connectionFactory.newConnection();
        //信道
        Channel channel = connection.createChannel();
        channel.exchangeDeclare(EXCHANGE_NAME,"direct");
        //声明一个随机队列
        String queueName = channel.queueDeclare().getQueue();

        String[] serverties = {"error","info","warning"};
        //一个信道可以绑定多个队列
        for (String server : serverties) {
            channel.queueBind(queueName,EXCHANGE_NAME,server);
        }

        final Consumer consumer = new DefaultConsumer(channel) {

            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                String msg = new String(body,"utf-8");
                System.out.println("received[" + envelope.getRoutingKey() + "]" + msg);
            }
        };


        System.out.println("* waiting for msg....");

        //消费者开始消费数据
        channel.basicConsume(queueName,true,consumer);



    }
}
