package com.wj.backupexchange;

import com.rabbitmq.client.BuiltinExchangeType;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeoutException;

public class BackupExchangeProducer {


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

        //声明备用交换器
        Map<String,Object> argsMap = new HashMap<String,Object>();
        argsMap.put("alternate-exchange",BAK_EXCHANGE_NAME);
        channel.exchangeDeclare(EXCHANGE_NAME,"direct",false,false,argsMap);
        channel.exchangeDeclare(BAK_EXCHANGE_NAME, BuiltinExchangeType.FANOUT,false,false,null);

        //发送消息
        String[] serverties = {"error","info","warning"};
        for (int i = 0; i < 3; i++) {
            String server = serverties[i % 3];
            String msg = "hello rabbitmq " + (i + 1);
            channel.basicPublish(EXCHANGE_NAME,server,null,msg.getBytes());
            System.out.println("消息发送成功");
        }

        channel.close();
        connection.close();
    }

}
