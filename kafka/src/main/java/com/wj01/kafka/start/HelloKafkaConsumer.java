package com.wj01.kafka.start;


import com.wj.constant.Constant;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.serialization.StringDeserializer;

import java.util.Collections;
import java.util.Properties;

public class HelloKafkaConsumer {

    public static void main(String[] args) {
        Properties properties = new Properties();
        properties.put("bootstrap.servers","10.0.0.141:9092");
        properties.put("key.deserializer", StringDeserializer.class);  //这种写的方式也是OK的
        properties.put("value.deserializer","org.apache.kafka.common.serialization.StringDeserializer");

        properties.put(ConsumerConfig.GROUP_ID_CONFIG,"test");  //指定消费者群组
        KafkaConsumer<String,String> consumer = new KafkaConsumer<String, String>(properties);
        try {
            consumer.subscribe(Collections.singletonList(Constant.HELLO_TOPIC));
            //kafka的消息只有拉取机制，没有推送机制
            while (true) {
                ConsumerRecords<String, String> records = consumer.poll(500); //每500毫秒拉取一次
                if (null != records) {
                    for (ConsumerRecord<String,String> record : records) {
                        System.out.printf("topic:%s，分区：%d,偏移量:%d,key:%s,value:%s",record.topic(),record.partition(),record.offset(),record.key(),record.value());
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            consumer.close();
        }


    }
}
