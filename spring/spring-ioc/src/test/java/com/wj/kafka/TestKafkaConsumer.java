package com.wj.kafka;

import com.wj.spring.kafka.RegistryListener;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class TestKafkaConsumer {

    public static void main(String[] args) {
       new Thread(()->{
           ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("applicationKafka.xml");
           System.out.println("2");
           RegistryListener registryListener = context.getBean("registryListener", RegistryListener.class);
           System.out.println(registryListener);
           System.out.println("1");
       }).start();
    }
}
