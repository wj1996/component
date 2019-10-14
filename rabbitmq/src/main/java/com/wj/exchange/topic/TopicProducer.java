package com.wj.exchange.topic;

import com.rabbitmq.client.BuiltinExchangeType;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class TopicProducer {


    public static final String EXCHANGE_NAME = "topic_logs";

    public static void main(String[] args) throws IOException, TimeoutException {
        ConnectionFactory connectionFactory = new ConnectionFactory();
        connectionFactory.setHost("10.0.0.141");
        connectionFactory.setUsername("admin");
        connectionFactory.setPassword("admin");
        Connection connection = connectionFactory.newConnection();
        //信道
        Channel channel = connection.createChannel();
        channel.exchangeDeclare(EXCHANGE_NAME, BuiltinExchangeType.TOPIC);

        //发送消息
        String[] serverties = {"error","info","warning"};
        String[] modules = {"user","order","email"};
        String[] servers = {"A","B","C"};
        //路由键类似于info.order.B
        for (int i = 0; i < 3; i++) {
            for (int j = 0 ; j < 3 ; j++) {
                //发送的消息
                for (int k = 0 ; k < 3 ; k++) {
                    String message = "hello topic [" + i + j + k + "]";
                    String routeKey = serverties[i % 3] + "." + modules[i % 3] + "." + servers[i % 3];
                    channel.basicPublish(EXCHANGE_NAME,routeKey,null,message.getBytes());
                }
            }
        }

        channel.close();
        connection.close();
    }

}
