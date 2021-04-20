package com.kafka;

import com.Utils;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;

import java.util.Properties;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

public class MyProducer<K,V> {
    private Properties properties;
    private KafkaProducer<K,V> kafkaProducer;
    public MyProducer(Properties properties) {
        this.properties = properties;
        kafkaProducer = new KafkaProducer<K, V>(properties);
    }



    public static void main(String[] args) throws ExecutionException, InterruptedException {
        Properties properties = new Properties();
        properties.setProperty(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG,"10.0.0.151:9092,10.0.0.172:9092,10.0.0.173:9092");
        properties.setProperty(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, "org.apache.kafka.common.serialization.StringSerializer");
        properties.setProperty(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG,"org.apache.kafka.common.serialization.StringSerializer");
        KafkaProducer<String,String> kafkaProducer = new KafkaProducer<String,String>(properties);
        String topic = "mytopic";
        sendMsg(topic,kafkaProducer);
    }

    public static void sendMsg(String topic,KafkaProducer<String,String> kafkaProducer) throws ExecutionException, InterruptedException {
        for (int i = 0; i < 10; i++) {
            ProducerRecord<String, String> record = new ProducerRecord<String, String>(topic,"key" + i,"value" + i);
            RecordMetadata recordMetadata = kafkaProducer.send(record).get();
            Utils.dealRecordMetadata(recordMetadata);
        }
    }

}
