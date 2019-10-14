package com.wj.exchange.direct;

import com.rabbitmq.client.*;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * 一个连接多个信道
 * 一个队列多个消费者
 */
public class MultiThreadConsumer {

    public static final String EXCHANGE_NAME = "direct_logs";

    private static class ConsumerWorker implements Runnable {

        final Connection connection;
        final String queueName;


        public ConsumerWorker(Connection connection,String queueName) {
            this.connection = connection;
            this.queueName = queueName;
        }


        public void run() {
            try {
                final Channel channel = connection.createChannel();
                channel.exchangeDeclare(MultiThreadConsumer.EXCHANGE_NAME,"direct");
                String factQueueName = queueName;
                final String consumerName;
                if (null == factQueueName) {
                    factQueueName = channel.queueDeclare().getQueue();
                    System.out.println(factQueueName);
//                    factQueueName += "-all";
                } else {
                    channel.queueDeclare(factQueueName,false,false,false,null);
                    consumerName = Thread.currentThread().getName();
                }

                String[] serverties = {"error","info","warning"};
                //一个信道可以绑定多个队列
                for (String server : serverties) {
                    channel.queueBind(factQueueName,EXCHANGE_NAME,server);
                }

                final Consumer consumer = new DefaultConsumer(channel) {

                    @Override
                    public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                        String msg = new String(body,"utf-8");
                        System.out.println("consumername :" + Thread.currentThread().getName()  + " received[" + envelope.getRoutingKey() + "]" + msg);
                    }
                };
                System.out.println("* waiting for msg....");
                channel.basicConsume(factQueueName,true,consumer);


            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) throws IOException, TimeoutException {
        ConnectionFactory connectionFactory = new ConnectionFactory();
        connectionFactory.setHost("10.0.0.141");
        connectionFactory.setUsername("admin");
        connectionFactory.setPassword("admin");
        Connection connection = connectionFactory.newConnection();


        //一个连接多个信道
        for (int i = 0 ; i < 2 ; i++) {
            Thread worker = new Thread(new ConsumerWorker(connection,null));
            worker.start();
        }


        //一个队列多个消费者
        for (int i = 0 ; i < 3 ; i++) {

            //默认会轮询去调用
            Thread worker = new Thread(new ConsumerWorker(connection,"focusAll"));
            worker.start();
        }



    }

}
