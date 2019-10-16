package com.wj.callback;

import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

@Component
public class SendReturnCallback implements RabbitTemplate.ReturnCallback {


    public void returnedMessage(Message message, int i, String s, String s1, String s2) {
        String msg = new String(message.getBody());
        System.out.println("返回的replyText ："+ s);
        System.out.println("返回的exchange ："+ s1);
        System.out.println("返回的routingKey ："+ s2);
        System.out.println("返回的message ："+message);
    }
}
