package com.wj.activemq.api;

import com.wj.activemq.utils.CloseResourceUtils;
import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;

/**
 * 使用监听器的方式，实现消息的处理
 */
public class ConsumerReceive {

    public void consumerMeesage(String destinationName) throws Exception {
        ConnectionFactory factory = null; //连接工厂
        Connection connection = null;
        Destination destination = null; //目的地
        Session session = null; //会话
        MessageConsumer consumer = null;
        try {
            factory = new ActiveMQConnectionFactory("admin", "admin", "tcp://10.0.0.141:61616");
            connection = factory.createConnection();
            connection.start();
            session = connection.createSession(false,Session.AUTO_ACKNOWLEDGE);
            destination = session.createQueue(destinationName);
            consumer = session.createConsumer(destination);
            consumer.setMessageListener(new MessageListener() {
                public void onMessage(Message message) {
                    ObjectMessage obj = (ObjectMessage) message;
                    try {
                        Object data = obj.getObject();
                        System.out.println(data);
                    } catch (JMSException e) {
                        e.printStackTrace();
                    }
                }
            });
            System.in.read();
        } catch (Exception e) {
;
        } finally {
            CloseResourceUtils.closeResource(consumer,session,connection);
        }
    }

    public static void main(String[] args) throws Exception{
        ConsumerReceive consumerReceive = new ConsumerReceive();
//        consumerReceive.consumerMeesage("test-send");
        consumerReceive.consumerMeesage("mytest");
    }
}
