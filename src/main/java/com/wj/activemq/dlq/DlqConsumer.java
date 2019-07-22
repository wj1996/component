package com.wj.activemq.dlq;

import com.wj.activemq.AbstractMq;
import org.apache.activemq.ActiveMQConnection;
import org.apache.activemq.RedeliveryPolicy;
import org.apache.activemq.broker.region.policy.RedeliveryPolicyMap;
import org.apache.activemq.command.ActiveMQDestination;

import javax.jms.*;

public class DlqConsumer extends AbstractMq {

    /**
     * 指定被消费几次后（实际上就是消费失败后，broker重新发送消息给消费者），进入死信队列
     * @throws Exception
     */

    public void consumerMsg() throws Exception {
        try {
            if (!isInit) {
                this.init();
                isInit = true;
            }
            ActiveMQConnection conn = (ActiveMQConnection) connection;
            RedeliveryPolicy redeliveryPolicy = new RedeliveryPolicy();
            RedeliveryPolicyMap redeliveryPolicyMap = conn.getRedeliveryPolicyMap();
            redeliveryPolicy.setMaximumRedeliveries(1); //重复消费一次后，如果还有问题，进入死信队列
            this.session = this.connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
            this.destination =  session.createQueue("TestDlq-2");
            redeliveryPolicyMap.put(((ActiveMQDestination)destination),redeliveryPolicy);
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
//            e.printStackTrace();
        } finally {
        }
    }

    public static void main(String[] args) throws Exception {
        new DlqConsumer().consumerMsg();
    }
}
