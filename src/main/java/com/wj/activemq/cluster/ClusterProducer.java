package com.wj.activemq.cluster;

import com.wj.activemq.utils.CloseResourceUtils;
import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;

public class ClusterProducer {


    public void sendMessage(String data) throws Exception {
        ConnectionFactory factory = null;
        Connection connection = null;
        Destination destination = null;
        Session session = null;
        MessageProducer producer = null;
        Message message = null;
        try {
            //创建连接工厂
            //failover：失败转移，当任意节点宕机，自动转移
            factory = new ActiveMQConnectionFactory("admin","admin",
                    "failover:(tcp://10.0.0.141:61617,tcp://10.0.0.141:61618,tcp://10.0.0.141:61619)?Randomize=false");
            connection = factory.createConnection();
            connection.start();
            session = connection.createSession(false,Session.CLIENT_ACKNOWLEDGE);
            destination = session.createQueue("first-cluster");
            producer = session.createProducer(destination);
            for (int i = 0; i < 10000; i++) {
                message = session.createTextMessage(data + i);
                producer.send(message);
            }
        } catch (Exception e) {

        } finally {
            CloseResourceUtils.closeResource(producer,session,connection);
        }
    }


    public static void main(String[] args) throws Exception {
        new ClusterProducer().sendMessage("test");
    }
}
