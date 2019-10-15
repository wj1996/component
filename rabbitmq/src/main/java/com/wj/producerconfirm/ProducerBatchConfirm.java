package com.wj.producerconfirm;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class ProducerBatchConfirm {


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
        String[] serverties = {"error","info"};
        //启用发送者确认模式
        channel.confirmSelect();
        for (int i = 0; i < 10; i++) {
            String server = serverties[i % 2];
            String msg = "hello rabbitmq " + (i + 1);
            channel.basicPublish(EXCHANGE_NAME,server,true,null,msg.getBytes());
        }
        channel.waitForConfirmsOrDie();

        channel.close();
        connection.close();
    }
}
