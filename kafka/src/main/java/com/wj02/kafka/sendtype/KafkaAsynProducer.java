package com.wj02.kafka.sendtype;

import com.wj.constant.Constant;
import com.wj.constant.KafkaConst;
import org.apache.kafka.clients.producer.Callback;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;
import org.apache.kafka.common.serialization.StringSerializer;

import java.util.concurrent.Future;

public class KafkaAsynProducer {

    public static void main(String[] args) {
        KafkaProducer<String,String> producer = new KafkaProducer<String, String>(KafkaConst.producerConfig(StringSerializer.class,StringSerializer.class));
        try {
            final ProducerRecord<String, String> record = new ProducerRecord<String, String>(Constant.HELLO_TOPIC, "test2", "123");
            producer.send(record, new Callback() {
                public void onCompletion(RecordMetadata recordMetadata, Exception e) {
                    if (null != e) {
                        e.printStackTrace();
                    }

                    if (null != recordMetadata) {
                        System.out.println(recordMetadata.offset() + "," + recordMetadata.partition());
                    }
                }
            });
        } finally {
            producer.close();
        }
    }
}
