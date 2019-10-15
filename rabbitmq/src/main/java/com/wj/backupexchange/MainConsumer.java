package com.wj.backupexchange;

import com.rabbitmq.client.*;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeoutException;

/**
 * 注意：
 *  consumer还是producer都具备创建这个交换器的能力
 *  consumer和producer在declareExchange的时候，在对同一个交换器而言，必修设置一模一样的配置，负责会造成消费者先启动，创建交换器使用的配置跟生产者配置不一致，
 *  生产者在启动的时候去声明交换器的时候，发现现有的交换器的设置与自身想设置的配置不一样，就会报错，造成生产者无法正常启动
 *
 */
public class MainConsumer {


    public static final String EXCHANGE_NAME = "main-exchange";
    public static final String BAK_EXCHANGE_NAME = "alternate-exchange";

    public static void main(String[] args) throws IOException, TimeoutException {
        ConnectionFactory connectionFactory = new ConnectionFactory();
        connectionFactory.setHost("10.0.0.141");
        connectionFactory.setUsername("admin");
        connectionFactory.setPassword("admin");
        Connection connection = connectionFactory.newConnection();
        //信道
        Channel channel = connection.createChannel();
        Map<String,Object> argsMap = new HashMap<String,Object>();
        argsMap.put("alternate-exchange",BAK_EXCHANGE_NAME);
        channel.exchangeDeclare(EXCHANGE_NAME,"direct",false,false,argsMap);
        channel.exchangeDeclare(BAK_EXCHANGE_NAME, BuiltinExchangeType.FANOUT,false,false,null);   //备用交换机设置为FAOUT，接收所有投递到这里的消息
        //声明一个队列
        String queueName = "focuserror";
        channel.queueDeclare(queueName,false,false,false,null);
        //绑定
        String routeKey = "error";
        channel.queueBind(queueName,EXCHANGE_NAME,routeKey);


        System.out.println("main consumer waiting for msg....");

        final Consumer consumer = new DefaultConsumer(channel) {

            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                String msg = new String(body,"utf-8");
                System.out.println("main consumer received[" + envelope.getRoutingKey() + "]" + msg);
            }
        };
        //消费者开始消费数据
        channel.basicConsume(queueName,true,consumer);



    }
}
