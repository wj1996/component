package com.wj.spring.kafka;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.listener.MessageListener;
import org.springframework.stereotype.Service;

@Service
public class RegistryListener implements MessageListener<Integer,String> {
    public void onMessage(ConsumerRecord<Integer, String> consumerRecord) {
        System.out.println("receive message:" + consumerRecord.value());
    }
}
