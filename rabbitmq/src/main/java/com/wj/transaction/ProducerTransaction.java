package com.wj.transaction;

import com.rabbitmq.client.*;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class ProducerTransaction {


    public static final String EXCHANGE_NAME = "transaction_logs";

    public static void main(String[] args) throws IOException, TimeoutException, InterruptedException {
        ConnectionFactory connectionFactory = new ConnectionFactory();
        connectionFactory.setHost("10.0.0.141");
        connectionFactory.setUsername("admin");
        connectionFactory.setPassword("admin");
        Connection connection = connectionFactory.newConnection();
        //信道
        Channel channel = connection.createChannel();
        channel.exchangeDeclare(EXCHANGE_NAME,"direct");



        //回调
        channel.addReturnListener(new ReturnListener() {
            /**
             * @param i
             * @param s   replyText
             * @param s1  exChange
             * @param s2  routeKey
             * @param basicProperties
             * @param bytes
             * @throws IOException
             */
            public void handleReturn(int i, String s, String s1, String s2, AMQP.BasicProperties basicProperties, byte[] bytes) throws IOException {
                String message = new String(bytes);
                System.out.println("返回的文本为：" + s + " exChange:" + s1 + " routeKey:" + s2);
            }
        });

        //发送消息
        String[] serverties = {"error","info","warning"};
        channel.txSelect();   //开始事务
        try {
            for (int i = 0; i < 3; i++) {
                String server = serverties[i % 3];
                String msg = "hello rabbitmq " + (i + 1);
                channel.basicPublish(EXCHANGE_NAME,server,true,null,msg.getBytes());
            Thread.sleep(200);
            }
            channel.txCommit();  //提交事务
        } catch (IOException e) {
            e.printStackTrace();
            channel.txRollback();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


//        channel.close();
//        connection.close();
    }
}
