package com.wj.activemq.utils;


import javax.jms.Connection;
import javax.jms.MessageConsumer;
import javax.jms.MessageProducer;
import javax.jms.Session;

public class CloseResourceUtils {







    public static void closeResource( Object object, Session session,Connection connection) throws Exception {
        if (object instanceof MessageProducer) ((MessageProducer) object).close();
        if (object instanceof MessageConsumer) ((MessageConsumer) object).close();
        if (null != session) session.close();
        if (null != connection) connection.close();

    }
}
