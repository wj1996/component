package com.wj.dlx;

import com.rabbitmq.client.*;
import com.wj.qos.BatchAckConsumer;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeoutException;

public class DlxRouteConsumer {


    public static final String EXCHANGE_NAME = "dlx_make";
    public static final String DLX_EXCHANGE_NAME = "dlx_accept";

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
        String queueName = "dlx_make";
        Map<String,Object> argsMap = new HashMap<String,Object>();
        //死信交换器
        argsMap.put("x-dead-letter-exchange",DLX_EXCHANGE_NAME);

        channel.queueDeclare(queueName,false,false,false,argsMap);

        channel.queueBind(queueName,EXCHANGE_NAME,"#");
        System.out.println("waiting for msg....");

        final Consumer consumer = new DefaultConsumer(channel) {

            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                String msg = new String(body,"utf-8");
                if ("error".equals(envelope.getRoutingKey())) {
                    System.out.println("received[" + envelope.getRoutingKey() + "]" + msg);
                    channel.basicAck(envelope.getDeliveryTag(),false);
                } else {
                    System.out.println("will reject[" + envelope.getRoutingKey() + "]" + msg);
                    channel.basicReject(envelope.getDeliveryTag(),false);
                }

             }
        };

        channel.basicConsume(queueName,false,consumer);
    }
}
