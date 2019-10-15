package com.wj.dlx;

import com.rabbitmq.client.*;
import com.wj.qos.BatchAckConsumer;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeoutException;

/**
 * 用来消费DlxRouteConsumer产生的死信信息
 */
public class DlxConsumer {


    public static final String EXCHANGE_NAME = "dlx_accept";

    public static void main(String[] args) throws IOException, TimeoutException {
        ConnectionFactory connectionFactory = new ConnectionFactory();
        connectionFactory.setHost("10.0.0.141");
        connectionFactory.setUsername("admin");
        connectionFactory.setPassword("admin");
        Connection connection = connectionFactory.newConnection();
        //信道
        final Channel channel = connection.createChannel();
        channel.exchangeDeclare(EXCHANGE_NAME,BuiltinExchangeType.TOPIC);


        //声明一个队列，绑定死信交换器
        String queueName = "dlx_accept";
        channel.queueDeclare(queueName,false,false,false,null);

        channel.queueBind(queueName,EXCHANGE_NAME,"#");
        System.out.println("waiting for msg....");

        final Consumer consumer = new DefaultConsumer(channel) {

            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                String msg = new String(body,"utf-8");
                System.out.println("received[" + envelope.getRoutingKey() + "]" + msg);
             }
        };

        channel.basicConsume(queueName,true,consumer);


    }
}
