package com.wj.activemq.listener;

import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;

public class ConsumerListener {

    public void consumeMessage() {
        ConnectionFactory factory = null; //连接工厂
        Connection connection = null;
        Destination destination = null; //目的地
        Session session = null; //会话
        MessageConsumer consumer = null;
        try {
            factory = new ActiveMQConnectionFactory("admin","admin","tcp://10.0.0.141:61616");
            connection = factory.createConnection();
            //消费者必须启动连接，否则无法消费
            connection.start();
            session = connection.createSession(false,Session.CLIENT_ACKNOWLEDGE);
            destination = session.createQueue("test-listener2");
            consumer = session.createConsumer(destination);
            //队列中的消息变化会自动触发监听器，接收消息并处理
            consumer.setMessageListener(new MessageListener() {
                /**
                 * 监听器一旦注册，永久有效
                 * 永久---consumer不关闭
                 * 处理消息的方式：只要有消息未处理，自动调用onMessage方法，处理消息
                 * 监听器可以注册若干，注册多个监听器，相当于集群
                 * ActiveMq自动的循环调用多个监听器，处理队列中的消息，实现并行处理
                 * @param message
                 */
                public void onMessage(Message message) {

                    try {
                        //确认方法，代表consumer方法已经收到消息，确定后，删除对应的消息
                        ObjectMessage om = (ObjectMessage) message;
                        Object data = om.getObject();
                        System.out.println(data);
                        throw new RuntimeException("123");
//                        message.acknowledge();
                    } catch (Exception e) {
//                        throw e;
                    }
                }
            });
            //阻塞当前代码
            System.in.read();
        } catch (Exception e) {

        } finally {
            if (null != consumer) {
                try {
                    consumer.close();
                } catch (JMSException e) {
                    e.printStackTrace();
                }
            }
            if (null != session) {
                try {
                    session.close();
                } catch (JMSException e) {
                    e.printStackTrace();
                }
            }
            if (null != connection) {
                try {
                    connection.close();
                } catch (JMSException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static void main(String[] args) {
        ConsumerListener consumerListener = new ConsumerListener();
        consumerListener.consumeMessage();
    }
}
