package com.wj.spring.kafka;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class KafkaDemo {

    @Autowired
    private KafkaTemplate kafkaTemplate;

    public String doTest() {
        kafkaTemplate.send("test","hello mac");
        return "success";
    }


}
