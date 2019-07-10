package com.wj.activemq.test1;

import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;

public class TestConsumer {

    public static void main(String[] args) {
        String resultCode = "";
        ConnectionFactory factory = null; //连接工厂
        Connection connection = null;
        Destination destination = null; //目的地
        Session session = null; //会话
        MessageConsumer consumer = null;
        Message message = null;
        try {
            factory = new ActiveMQConnectionFactory("admin","admin","tcp://10.0.0.141:61616");
            connection = factory.createConnection();
            //消费者必须启动连接，否则无法消费
            connection.start();
            session = connection.createSession(false,Session.AUTO_ACKNOWLEDGE);
            destination = session.createQueue("first-mq");
            consumer = session.createConsumer(destination);
            //receive方法是一个主动获取消息的方法，执行以此，拉取一个消息，开发少用
            message = consumer.receive();

            resultCode = ((TextMessage)message).getText();

            System.out.println("消息内容：" + resultCode);

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
}
