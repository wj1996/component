package com.wj.activemq.cluster;

import com.wj.activemq.utils.CloseResourceUtils;
import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;

/**
 * broker集群配置后测试，在141主机发送消息，142主机消费
 */
public class ClusterConsumer2 {

    public void consumerMessage() throws Exception {
        ConnectionFactory factory = null;
        Connection connection = null;
        Destination destination = null;
        Session session = null;
        MessageConsumer consumer = null;
        try {
            //创建连接工厂
            //failover：失败转移，当任意节点宕机，自动转移
            factory = new ActiveMQConnectionFactory("admin","admin",
                    "tcp://10.0.0.142:61616");
            connection = factory.createConnection();
            connection.start();
            session = connection.createSession(false,Session.CLIENT_ACKNOWLEDGE);
            destination = session.createQueue("first-cluster");
            consumer = session.createConsumer(destination);
            consumer.setMessageListener(new MessageListener() {
                public void onMessage(Message message) {
                    try {
                        message.acknowledge();
                        String msg = ((TextMessage)message).getText();
                        System.out.println("接收到的消息为：" + msg);
                    } catch (JMSException e) {
                        e.printStackTrace();
                    }
                }
            });
            System.in.read();
        } catch (Exception e) {

        } finally {
            CloseResourceUtils.closeResource(consumer,session,connection);
        }
    }


    public static void main(String[] args) throws Exception {
        new ClusterConsumer2().consumerMessage();
    }
}
