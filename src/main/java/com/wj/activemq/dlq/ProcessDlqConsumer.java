package com.wj.activemq.dlq;

import com.wj.activemq.AbstractMq;
import org.apache.activemq.ActiveMQConnection;
import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;

/**
** 处理死信队列()
 */
public class ProcessDlqConsumer extends AbstractMq {


    public void processDlq() throws Exception {
        try {
            init();
            session = connection.createSession(false,Session.AUTO_ACKNOWLEDGE);
            destination = session.createQueue("DLQ.>");  // 通配符方式
            obj = session.createConsumer(destination);
            ((MessageConsumer)obj).setMessageListener(new MessageListener() {
                public void onMessage(Message message) {
                    try {
                        System.out.println("accept DEAD message : " + ((TextMessage)message).getText());
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws Exception {
        new ProcessDlqConsumer().processDlq();
    }

}
