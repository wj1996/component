package com.kafka;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.TopicPartition;

import java.util.Collections;
import java.util.List;
import java.util.Properties;
import java.util.Set;

public class MyConsumer {

    public static void main(String[] args) {
        Properties properties = new Properties();
        properties.setProperty(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG,"10.0.0.151:9092,10.0.0.172:9092,10.0.0.173:9092");
        properties.setProperty(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, "org.apache.kafka.common.serialization.StringDeserializer");
        properties.setProperty(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG,"org.apache.kafka.common.serialization.StringDeserializer");
        properties.setProperty(ConsumerConfig.GROUP_ID_CONFIG,"myGroup2");
        properties.setProperty(ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG,"false");
//        properties.setProperty(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG,"latest");
        properties.setProperty(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG,"earliest");
        KafkaConsumer<String,String> kafkaConsumer = new KafkaConsumer<String, String>(properties);
        kafkaConsumer.subscribe(Collections.singletonList("mytopic"));
        consumer(kafkaConsumer);
    }

    public static void consumer(KafkaConsumer<String,String> kafkaConsumer) {
        while (true) {
            ConsumerRecords<String, String> records = kafkaConsumer.poll(1000);
            if (records.isEmpty()) {
                continue;
            }

            /**
             * 如果手动提交offset
             *  1.按消息进度同步提交
             *  2.分区粒度同步提交
             *  3.按当前poll的批次同步提交
             *
             *  在多个线程下：
             *      1. 按照2的方式来
             */
            Set<TopicPartition> partitions = records.partitions();
            for (TopicPartition topicPartition : partitions) {
                List<ConsumerRecord<String, String>> pRecords = records.records(topicPartition);
                for (ConsumerRecord record : pRecords) {
                    System.out.println("key:" + record.key() + ",value:" + record.value() + ",partition:" + record.partition() + ",offset:" + record.offset());
                }
            }

            /*for (ConsumerRecord record : records) {
                System.out.println("key:" + record.key() + ",value:" + record.value() + ",partition:" + record.partition() + ",offset:" + record.offset());
            }*/
        }

    }
}
