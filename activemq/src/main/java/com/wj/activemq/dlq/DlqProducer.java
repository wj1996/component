package com.wj.activemq.dlq;

import com.wj.activemq.AbstractMq;
import javax.jms.*;

public class DlqProducer extends AbstractMq {

    public void producerMsg() throws Exception {
        if (!isInit) {
            this.init();
            isInit = true;
        }
        try {
            this.session = this.connection.createSession(true, Session.AUTO_ACKNOWLEDGE);
            this.destination =  session.createQueue("TestDlq-2");
            obj = session.createProducer(destination);
            for (int i = 0;i < SENDNUM; i++) {
                String msg = "发送消息：" + i + "-" + System.currentTimeMillis();
                System.out.println("发送消息：" + msg);
                ((MessageProducer)obj).send(session.createTextMessage(msg));
            }
            session.commit();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            close();
        }
    }

    public static void main(String[] args) throws Exception {
        new DlqProducer().producerMsg();
    }

}
