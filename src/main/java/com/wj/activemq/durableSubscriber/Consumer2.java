package com.wj.activemq.durableSubscriber;

import com.wj.activemq.utils.CloseResourceUtils;
import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;

public class Consumer2 {

    public static void main(String[] args) throws Exception {
        ConnectionFactory factory = null; //连接工厂
        Connection connection = null;
        Destination destination = null; //目的地
        Session session = null; //会话
        MessageConsumer consumer = null;
        //消息对象
        Message message = null;
        try {
            factory = new ActiveMQConnectionFactory("admin","admin","tcp://10.0.0.141:61616");
            connection = factory.createConnection();
            connection.setClientID("wj-02"); //如果持久化订阅，此处必须设置一个唯一的ID标识
            connection.start();
            session = connection.createSession(false,Session.AUTO_ACKNOWLEDGE);
            destination = session.createTopic("durableSubscriber");
            consumer = session.createDurableSubscriber((Topic) destination,"wj");  //持久化订阅
            consumer.setMessageListener(new MessageListener() {
                public void onMessage(Message message) {
                    try {
                        System.out.println("Accept msg : "
                                +((TextMessage)message).getText());
                    } catch (JMSException e) {
                        e.printStackTrace();
                    }
                }
            });
            System.in.read();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            CloseResourceUtils.closeResource(consumer,session,connection);
        }
    }
}
