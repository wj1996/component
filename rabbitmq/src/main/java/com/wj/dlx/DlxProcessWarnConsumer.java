package com.wj.dlx;

import com.rabbitmq.client.*;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeoutException;

/**
 * 用来处理DlxRouteWarnConsumer产生的死信队列，重新设置了消息的路由key
 */
public class DlxProcessWarnConsumer {


    public static final String EXCHANGE_NAME = "dlx_warn_accept";
    public static final String ROUTE_KEY = "dlx_warn";


    public static void main(String[] args) throws IOException, TimeoutException {
        ConnectionFactory connectionFactory = new ConnectionFactory();
        connectionFactory.setHost("10.0.0.141");
        connectionFactory.setUsername("admin");
        connectionFactory.setPassword("admin");
        Connection connection = connectionFactory.newConnection();
        //信道
        final Channel channel = connection.createChannel();
        channel.exchangeDeclare(EXCHANGE_NAME,BuiltinExchangeType.TOPIC);


        String queueName = "dlx_warn_accept";

        channel.queueDeclare(queueName,false,false,false,null);

        channel.queueBind(queueName,EXCHANGE_NAME,ROUTE_KEY);
        System.out.println("waiting for msg....");

        final Consumer consumer = new DefaultConsumer(channel) {

            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                String msg = new String(body,"utf-8");
                    System.out.println("received dead leter[" + envelope.getRoutingKey() + "]" + msg);
             }
        };

        channel.basicConsume(queueName,true,consumer);

    }
}
