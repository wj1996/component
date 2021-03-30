package com.kafka;

import org.apache.kafka.clients.producer.*;

import java.util.Properties;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

public class Producer2Demo extends Thread {

    private final KafkaProducer<Integer,String> producer;
    private final String topic;
    private final boolean isAsync;

    public Producer2Demo(String topic, boolean isAsync) {
        Properties properties = new Properties();
        properties.setProperty(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG,
                "10.0.0.151:9092,10.0.0.172:9092,10.0.0.173:9092");
        properties.setProperty(ProducerConfig.CLIENT_ID_CONFIG,"KafkaProducer");
        properties.setProperty(ProducerConfig.ACKS_CONFIG,"-1");
        properties.setProperty(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG,"org.apache.kafka.common.serialization.IntegerSerializer");
        properties.setProperty(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG,"org.apache.kafka.common.serialization.StringSerializer");
        //指定分区策略
        properties.setProperty(ProducerConfig.PARTITIONER_CLASS_CONFIG,"com.kafka.MyPartition");
        this.producer = new KafkaProducer<Integer, String>(properties);;
        this.topic = topic;
        this.isAsync = isAsync;
    }

    public static void main(String[] args) {
        new Producer2Demo("test2",false).run();
    }

    @Override
    public void run() {
        int num = 0;
        while (num < 50) {
            String message = "message_" + num;
            System.out.println("begin send message:" + message);
            if (false) {
                producer.send(new ProducerRecord<Integer, String>(topic, message), new Callback() { //异步发送
                    public void onCompletion(RecordMetadata recordMetadata, Exception e) {
                        if (null != recordMetadata) {
                            System.out.println("async-offset:" + recordMetadata.offset() + ",partition:" + recordMetadata.partition());
                        }
                    }
                });
            } else {
                Future<RecordMetadata> future = producer.send(new ProducerRecord<Integer, String>(topic, message));
                try {
                    RecordMetadata recordMetadata = future.get();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (ExecutionException e) {
                    e.printStackTrace();
                }
            }
            num++;
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
