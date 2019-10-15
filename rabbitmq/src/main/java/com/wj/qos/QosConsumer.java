package com.wj.qos;

import com.rabbitmq.client.*;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class QosConsumer {


    public static final String EXCHANGE_NAME = "qos";

    public static void main(String[] args) throws IOException, TimeoutException {
        ConnectionFactory connectionFactory = new ConnectionFactory();
        connectionFactory.setHost("10.0.0.141");
        connectionFactory.setUsername("admin");
        connectionFactory.setPassword("admin");
        Connection connection = connectionFactory.newConnection();
        //信道
        final Channel channel = connection.createChannel();
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
                channel.basicAck(envelope.getDeliveryTag(),false);   //单条确认
             }
        };

        /**
         * prefetchSize   取消息的总大小，模式rabbitmq没哟实现，设置为0即可
         * prefetchCount  消息的总个数
         * global     设置是否对整个信道上生效  一个信道上是可以有多个消费者的
         *              设置false ，对每个消费者生效
         *
         *          下面的设置意思是：
         *              整个信道一次批次的数量不能超过150
         *              单个消费者的一个批次的数量不能超过100
         *
         *         channel.basicQos(100,true);
         *         channel.basicQos(150,false);
         */
        channel.basicQos(150,false);
        //消费者正式在队列上获取消息
        channel.basicConsume(queueName,false,consumer);
        BatchAckConsumer batchAckConsumer = new BatchAckConsumer(channel);
        channel.basicConsume(queueName,false,batchAckConsumer);


    }
}
