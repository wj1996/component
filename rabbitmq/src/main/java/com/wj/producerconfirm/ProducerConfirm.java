package com.wj.producerconfirm;

import com.rabbitmq.client.*;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class ProducerConfirm {


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
        //启用发送者确认模式
        channel.confirmSelect();
        for (int i = 0; i < 3; i++) {
            String server = serverties[i % 3];
            String msg = "hello rabbitmq " + (i + 1);
            channel.basicPublish(EXCHANGE_NAME,server,true,null,msg.getBytes());
            boolean isSendSuccess = channel.waitForConfirms();
            if (isSendSuccess) {
                System.out.println("success");
            } else {
                System.out.println("error");
            }
        }

        channel.close();
        connection.close();
    }
}
