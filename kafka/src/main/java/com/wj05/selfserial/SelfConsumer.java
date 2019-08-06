package com.wj05.selfserial;

import com.wj.constant.Constant;
import com.wj.constant.KafkaConst;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.clients.producer.Callback;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;
import org.apache.kafka.common.serialization.StringDeserializer;

import java.util.Collections;

public class SelfConsumer {


    public static void main(String[] args) {
        KafkaConsumer<String,DemoUser> consumer = new KafkaConsumer<String, DemoUser>(
                KafkaConst.consumerConfig("selfserial", StringDeserializer.class,SelfDeserializer.class));
        try {
            consumer.subscribe(Collections.singletonList(Constant.SELF_SERIAL_TOPIC));
            while (true) {
                ConsumerRecords<String,DemoUser> records = consumer.poll(500);
                for (ConsumerRecord<String,DemoUser> record : records) {
                    System.out.printf("偏移量：%s,分区：%s,value:%s",record.offset(),record.partition(),record.value());
                }
            }
        } catch (Exception e) {

        } finally {
            consumer.close();
        }
    }
}
