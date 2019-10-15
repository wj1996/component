package com.wj.ackfalse;

import com.rabbitmq.client.*;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * 如果消费者的消费确认方式改为手动，如果消费者消费完消息，没有确认，rabbitmq会一直等待这个消费者确认，直到消费者确认或者这个消费者挂掉，将消息投递给其它消费者处理。
 *
 * 注意：
 *  这样的处理，可能会造成消息重复消费，此处的逻辑处理需要注意
 *
 *  使用
 *      try {
 *
 *      } catch (Exception e) {
 *
 *      }
 *
 *
 */
public class AckFlaseConsumer2 {


    public static final String EXCHANGE_NAME = "ackfalse";

    public static void main(String[] args) throws IOException, TimeoutException {
        ConnectionFactory connectionFactory = new ConnectionFactory();
        connectionFactory.setHost("10.0.0.141");
        connectionFactory.setUsername("admin");
        connectionFactory.setPassword("admin");
        Connection connection = connectionFactory.newConnection();
        //信道
        Channel channel = connection.createChannel();
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
            }
        };
        //消费者开始消费数据
        channel.basicConsume(queueName,false,consumer);



    }
}
