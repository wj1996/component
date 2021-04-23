package com.kafka;

import com.Utils;
import org.apache.kafka.clients.producer.*;

import java.util.Properties;
import java.util.concurrent.CountDownLatch;
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
        int n = 3;
        final CountDownLatch countDownLatch = new CountDownLatch(n);
        for (int i = 0; i < n; i++) {

            ProducerRecord<String, String> record = new ProducerRecord<String, String>(topic,"key" + i,"value" + i);
            //同步
           /* RecordMetadata recordMetadata = kafkaProducer.send(record).get();
            Utils.dealRecordMetadata(recordMetadata);
            System.out.println("哈哈哈");*/

            kafkaProducer.send(record, new Callback() {
                public void onCompletion(RecordMetadata metadata, Exception exception) {
                    try {
                        Utils.dealRecordMetadata(metadata);
                    } finally {
                        countDownLatch.countDown();
                    }


                }
            });
            System.out.println("哈哈哈");
        }

        countDownLatch.await();
    }

}
