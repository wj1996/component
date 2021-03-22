package com.wj.kafka;

import com.wj.spring.kafka.KafkaDemo;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class TestKafkaDemo {

    public static void main(String[] args) {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("applicationKafka.xml");
        KafkaDemo kafkaDemo = context.getBean("kafkaDemo",KafkaDemo.class);
        System.out.println(kafkaDemo);
        kafkaDemo.doTest();
    }
}
