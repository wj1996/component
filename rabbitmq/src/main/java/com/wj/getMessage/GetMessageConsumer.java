package com.wj.getMessage;

import com.rabbitmq.client.*;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class GetMessageConsumer {


    public static final String EXCHANGE_NAME = "getmessage";

    public static void main(String[] args) throws IOException, TimeoutException, InterruptedException {
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

        //拉取方式消费, 使用下列方法获取一次消息  rabbitmq少用

        while (true) {
            GetResponse getResponse = channel.basicGet(queueName, true);  //确认机制，
            if (null != getResponse) {  //可能获取到的消息为空
                System.out.println("received[" + getResponse.getEnvelope().getRoutingKey() + "," + new String(getResponse.getBody()) + "]");
            }
            Thread.sleep(10);
        }




    }
}
