package com.wj.activemq.dlq;

import com.wj.activemq.AbstractMq;
import org.apache.activemq.ActiveMQConnection;
import org.apache.activemq.RedeliveryPolicy;

import javax.jms.*;

public class DlqConsumer extends AbstractMq {


    public void consumerMsg() throws Exception {
        try {
            if (!isInit) {
                this.init();
                isInit = true;
            }
            ActiveMQConnection conn = (ActiveMQConnection) connection;
            RedeliveryPolicy redeliveryPolicy = conn.getRedeliveryPolicy();
            redeliveryPolicy.setMaximumRedeliveries(1);
            this.session = this.connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
            this.destination =  session.createQueue("TestDlq-2");
            obj = session.createConsumer(destination);
            ((MessageConsumer)obj).setMessageListener(new MessageListener() {
                public void onMessage(Message message) {
                    try {
                        System.out.println("accept message : " + ((TextMessage)message).getText());
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    throw new RuntimeException("test");
                }
            });
            System.in.read();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            close();
        }
    }

    public static void main(String[] args) throws Exception {
        new DlqConsumer().consumerMsg();
    }
}
