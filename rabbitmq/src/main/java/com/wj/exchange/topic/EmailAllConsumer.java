package com.wj.exchange.topic;

import com.rabbitmq.client.*;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class EmailAllConsumer {


    public static final String EXCHANGE_NAME = "topic_logs";

    public static void main(String[] args) throws IOException, TimeoutException {
        ConnectionFactory connectionFactory = new ConnectionFactory();
        connectionFactory.setHost("10.0.0.141");
        connectionFactory.setUsername("admin");
        connectionFactory.setPassword("admin");
        Connection connection = connectionFactory.newConnection();
        //信道
        Channel channel = connection.createChannel();
        channel.exchangeDeclare(EXCHANGE_NAME,BuiltinExchangeType.TOPIC);   //指定信道下面的交换器类型
        //声明一个队列
        String queueName = channel.queueDeclare().getQueue();   //让rabbitmq为我们随机创建一个队列名称

        channel.queueBind(queueName,EXCHANGE_NAME,"*.email.*");

        final Consumer consumer = new DefaultConsumer(channel) {

            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                String msg = new String(body,"utf-8");
                System.out.println("EmailAllConsumer received[" + envelope.getRoutingKey() + "]" + msg);
            }
        };
        //消费者开始消费数据
        channel.basicConsume(queueName,true,consumer);



    }
}
