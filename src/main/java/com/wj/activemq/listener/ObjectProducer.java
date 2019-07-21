package com.wj.activemq.listener;

import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;
import java.util.Random;

public class ObjectProducer {

    public void productMessage() {
        ConnectionFactory factory = null; //连接工厂
        Connection connection = null;
        Destination destination = null; //目的地
        Session session = null; //会话
        MessageProducer producer = null;
        //消息对象
        Message message = null;
        try {
            factory = new ActiveMQConnectionFactory("admin","admin","tcp://10.0.0.141:61616");
            connection = factory.createConnection();
            connection.start();
            Random random = new Random();
            session = connection.createSession(false,Session.AUTO_ACKNOWLEDGE);
            destination = session.createQueue("test-listener2");
            producer = session.createProducer(destination);
            for (int i = 0 ;i < 1; i++) {
                Integer data = random.nextInt(100);
                message = session.createObjectMessage(data);
                producer.send(message);
            }


        } catch (Exception e) {

        } finally {
            if (null != producer) {
                try {
                    producer.close();
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
        ObjectProducer objectProducer = new ObjectProducer();
        objectProducer.productMessage();
    }
}
