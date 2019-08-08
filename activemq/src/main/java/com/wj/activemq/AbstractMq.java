package com.wj.activemq;

import com.wj.activemq.utils.CloseResourceUtils;
import org.apache.activemq.ActiveMQConnection;
import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;

public class AbstractMq {

    public ConnectionFactory factory = null; //连接工厂
    public Connection connection = null;
    public Destination destination = null; //目的地
    public Session session = null; //会话
    public Object obj = null;
    //消息对象
    public Message message = null;
    public boolean isInit = false;
    public final static int SENDNUM = 1;

    public void init() throws JMSException {
        if (!isInit) {
            System.out.println(this + "init调用了");
            factory = new ActiveMQConnectionFactory("admin","admin","tcp://10.0.0.141:61616");
            connection = (ActiveMQConnection)factory.createConnection();
            connection.start();
            isInit = true;
        }

    }

    public void close() throws Exception{
        CloseResourceUtils.closeResource(obj,session,connection);
    }





}
