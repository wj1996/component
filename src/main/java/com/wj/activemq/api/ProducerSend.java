package com.wj.activemq.api;

import com.wj.activemq.utils.CloseResourceUtils;
import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;
import java.io.Serializable;

public class ProducerSend {


    private ActiveMQConnectionFactory factory;
    private Connection connection;
    private Session session;
    private Destination destination;
    private MessageProducer producer;
    private Message message;

    private void init() throws Exception {
        this.init(null);
    }

    private void init(String destinationName) throws Exception {
        this.init("admin","admin","tcp://10.0.0.141:61616",destinationName);
    }

    private void init(String username,String password,String brokerURL,String destinationName) throws Exception {
        try {
            factory = new ActiveMQConnectionFactory(username,password,brokerURL);
            connection = factory.createConnection();
            session = connection.createSession(false,Session.AUTO_ACKNOWLEDGE);
            if (null != destinationName) {
                destination = session.createQueue(destinationName);
                producer = session.createProducer(destination);
            } else {
                producer = session.createProducer(null);
            }
            connection.start();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    /**
     * 发送消息到默认目的地
     * @param obj
     */
    public void sendMessage(Serializable obj) {
        try {
            this.init("test-send");
            message = session.createObjectMessage(obj);
            producer.send(message);
            System.out.println("sendMessage method run");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 发送消息到指定目的地
     * @param obj
     * @param destinationName
     */
    public void sendMessage2Destination(Serializable obj,String destinationName) {
        try {
            this.init();
            message = session.createObjectMessage(obj);
            Destination destination = session.createQueue(destinationName);
            producer.send(destination,message);
            System.out.println("sendMessage2Destination method run");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 多参数发送消息
     * @param obj
     * @param deliveryMode  持久化方式
     *                      DeliveryMode.PERSISTENT  持久化方式
     *                      DeliveryMode.NON_PERSISTENT 不持久化
     * @param priority      优先级，0-9取值范围，取值越大优先级越高，不能保证决定顺序，必须在activemq.xml中配置文件的broker标签中增加配置
     * @param timeToLive    消息有效期，单位毫秒，有效期超时，消息自动放弃
     */
    public void sendMessageWithParams(Serializable obj,int deliveryMode,int priority,long timeToLive) {
        try {
            this.init("test-send-param");
            message = session.createObjectMessage(obj);
            producer.send(message,deliveryMode,priority,timeToLive);

            System.out.println("sendMessageWithParams method run");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void destroy() throws Exception {
        CloseResourceUtils.closeResource(producer,session,connection);
    }


    public static void main(String[] args) {
        ProducerSend producerSend = new ProducerSend();

        try {
//            producerSend.sendMessage("send message");
//            producerSend.sendMessage2Destination("send message","mytest");
//            producerSend.sendMessageWithParams("send message",DeliveryMode.PERSISTENT,0,1000 * 10);
            producerSend.sendMessageWithParams("send message",DeliveryMode.NON_PERSISTENT,0,1000 * 10);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (null != producerSend) producerSend.destroy();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
