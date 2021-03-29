package com.kafka;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;

import java.util.Collections;
import java.util.Properties;

public class ConsumerDemo2_1 extends Thread {

    private final KafkaConsumer kafkaConsumer;

    public ConsumerDemo2_1(String topic) {
        Properties properties = new Properties();
        properties.setProperty(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG,
                "10.0.0.151:9092,10.0.0.172:9092,10.0.0.173:9092");
        properties.setProperty(ConsumerConfig.GROUP_ID_CONFIG,"kafkaConsumer");
        properties.setProperty(ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG,"true");
        properties.setProperty(ConsumerConfig.AUTO_COMMIT_INTERVAL_MS_CONFIG,"1000");
        properties.setProperty(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG,"org.apache.kafka.common.serialization.IntegerDeserializer");
        properties.setProperty(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG,"org.apache.kafka.common.serialization.StringDeserializer");
        properties.setProperty(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG,"earliest");  //是否拉取之前发送的消息
        kafkaConsumer = new KafkaConsumer(properties);
        kafkaConsumer.subscribe(Collections.singletonList(topic));
    }
    @Override
    public void run() {
        while (true) {
            ConsumerRecords<Integer,String> consumerRecords = kafkaConsumer.poll(1000);
            for (ConsumerRecord consumerRecord : consumerRecords) {
                System.out.println("partition:" + consumerRecord.partition() + ", receive message : " + consumerRecord.value());
            }
        }
    }
    public static void main(String[] args) {
        new ConsumerDemo2_1("test2").start();
    }
}
