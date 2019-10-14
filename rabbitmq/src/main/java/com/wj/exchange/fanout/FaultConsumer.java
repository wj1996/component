package com.wj.exchange.fanout;

import com.rabbitmq.client.*;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class FaultConsumer {


    public static final String EXCHANGE_NAME = "fanout_logs";

    public static void main(String[] args) throws IOException, TimeoutException {
        ConnectionFactory connectionFactory = new ConnectionFactory();
        connectionFactory.setHost("10.0.0.141");
        connectionFactory.setUsername("admin");
        connectionFactory.setPassword("admin");
        Connection connection = connectionFactory.newConnection();
        //信道
        Channel channel = connection.createChannel();
        channel.exchangeDeclare(EXCHANGE_NAME,BuiltinExchangeType.FANOUT);   //指定信道下面的交换器类型
        //声明一个队列
        String queueName = channel.queueDeclare().getQueue();   //让rabbitmq为我们随机创建一个队列名称
        //绑定
        String[] serverties = {"error","info","warning"};
        for (String str : serverties) {
            channel.queueBind(queueName,EXCHANGE_NAME,str);  //绑定队列，同时给出routeKey

        }

        /**
         * 注意：如果设置了同队列，此时消息的消费，就会被消费者轮询消费
         * 消息发送至Fanout交换器，不会有根据绑定路由值的判断，会发送到这个交换器下面的所有队列
         */

        /*String queueName = "producer_create";
        channel.queueDeclare(queueName,false,false,false,null);
        channel.queueBind(queueName,EXCHANGE_NAME,"test2");
        System.out.println("waiting for msg....");*/

        final Consumer consumer = new DefaultConsumer(channel) {

            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                String msg = new String(body,"utf-8");
                System.out.println("received[" + envelope.getRoutingKey() + "]" + msg);
            }
        };
        //消费者开始消费数据
        channel.basicConsume(queueName,true,consumer);



    }
}
