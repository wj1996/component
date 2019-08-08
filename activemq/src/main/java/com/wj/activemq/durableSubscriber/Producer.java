package com.wj.activemq.durableSubscriber;

import com.wj.activemq.utils.CloseResourceUtils;
import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;

/**
 *
 */
public class Producer {



    public static void main(String[] args) throws Exception {
        ConnectionFactory factory = null; //连接工厂
        Connection connection = null;
        Destination destination = null; //目的地
        Session session = null; //会话
        MessageProducer producer = null;
        //消息对象
        Message message = null;
        String datas = "my message data";
        try {
            factory = new ActiveMQConnectionFactory("admin","admin","tcp://10.0.0.141:61616");
            connection = factory.createConnection();
            connection.start();
            session = connection.createSession(false,Session.AUTO_ACKNOWLEDGE);
            destination = session.createTopic("durableSubscriber");
            producer = session.createProducer(destination);
            for (int i = 0; i < 3; i++) {
                message = session.createTextMessage("data" + i);
                producer.send(message);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            CloseResourceUtils.closeResource(producer,session,connection);
        }
    }

}
