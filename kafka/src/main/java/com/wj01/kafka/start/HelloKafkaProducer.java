package com.wj01.kafka.start;

import com.wj.constant.Constant;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;

import java.util.Properties;

public class HelloKafkaProducer {

    public static void main(String[] args) {
        Properties properties = new Properties();
        properties.put("bootstrap.servers","10.0.0.141:9092");
        properties.put("key.serializer","org.apache.kafka.common.serialization.StringSerializer");
        properties.put("value.serializer","org.apache.kafka.common.serialization.StringSerializer");
        KafkaProducer<String,String> producer = new KafkaProducer<String, String>(properties);
        try {
            ProducerRecord<String,String> record;
            record = new ProducerRecord<String, String>(Constant.HELLO_TOPIC,"teacher","lision");
            producer.send(record);
            System.out.println("消息发送");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            producer.close();
        }


    }
}
