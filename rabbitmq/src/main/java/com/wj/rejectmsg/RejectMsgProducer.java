package com.wj.rejectmsg;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class RejectMsgProducer {


    public static final String EXCHANGE_NAME = "rejectmsg";

    public static void main(String[] args) throws IOException, TimeoutException {
        ConnectionFactory connectionFactory = new ConnectionFactory();
        connectionFactory.setHost("10.0.0.141");
        connectionFactory.setUsername("admin");
        connectionFactory.setPassword("admin");
        Connection connection = connectionFactory.newConnection();
        //信道
        Channel channel = connection.createChannel();
        channel.exchangeDeclare(EXCHANGE_NAME,"direct");

        //发送消息
        String[] serverties = {"error"};
        for (int i = 0; i < 10; i++) {
            String server = serverties[i % 1];
            String msg = "hello rabbitmq " + (i + 1);
            channel.basicPublish(EXCHANGE_NAME,server,null,msg.getBytes());
        }

        channel.close();
        connection.close();
    }

}
