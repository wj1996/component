package com.wj.activemq.test1;

import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;

public class TestProducer {

    public static void main(String[] args) {
        ConnectionFactory factory = null; //连接工厂
        Connection connection = null;
        Destination destination = null; //目的地
        Session session = null; //会话
        MessageProducer producer = null;
        //消息对象
        Message message = null;
        String datas = "my message data";
        try {
            /**
             * 创建连接工厂，连接ActiveMq服务的连接工厂
             * 创建工厂，构造方法有3个参数，分别是用户名，密码，连接地址
             * 无参构造，有默认的连接地址，本地连接，localhost
             * 单参数构造，无验证模式的，没有用户的认证
             * 三参数构造，有认证+指定地址
             */
            factory = new ActiveMQConnectionFactory("admin","admin","tcp://10.0.0.141:61616");
            connection = factory.createConnection();
            //建立启动连接，消息的发送者不是必须启动连接，消息的消费者必须启动连接
            //producer在发送消息的时候，会检测是否启动连接，如果没有，自动启动
            connection.start();

            /**
             * 通过连接对象，创建会话对象，确定绑定目的地
             *  创建会话的时候必须传递两个参数，分别代表是否支持事务和如何确认消息处理
             *  transacted --是否支持事务，数据类型是Boolean，true-支持，false-不支持
             *  支持事务，第二个参数默认无效，建议传递的数据是Session.SESSION_TRANSACTED
             *  不支持事务，常用参数，第二个参数必须传递，且必须有效
             *  acknowledgeMode - 如何确认消息的处理，使用确认机制实现的
             *  AUTO_ACKNOWLEDGE - 自动确认消息，消息的消费者处理消息后，自动确认，常用
             *  CLIENT_ACKNOWLEDGE - 客户端手动确认，消息的消费者处理后，必须手动确认
             *  DUPS_OK_ACKNOWLEDGE - 由副本的客户端必须手动确认
             *      一个消息可以多次处理
             *      可以降低Session的消耗，在可以容忍重复消息时使用（不推荐使用）
             *
             */
            session = connection.createSession(false,Session.CLIENT_ACKNOWLEDGE);
            destination = session.createQueue("first-mq");
            //通过会话对象，创建消息的发送者producer
            producer = session.createProducer(destination);
            //创建文本消息对象，作为具体内容的载体
            message = session.createTextMessage(datas);
            //如果消息发送失败，抛出异常
            producer.send(message);
            System.out.println("消息已经发送");
        } catch (Exception e) {
            e.printStackTrace();
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
}
