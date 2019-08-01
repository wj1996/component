package com.wj02.kafka.sendtype;

import com.wj.constant.Constant;
import com.wj.constant.KafkaConst;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;
import org.apache.kafka.common.serialization.StringSerializer;

import java.util.concurrent.Future;

public class KafkaFutureProducer {

    public static void main(String[] args) {
        KafkaProducer<String,String> producer = new KafkaProducer<String, String>(KafkaConst.producerConfig(StringSerializer.class,StringSerializer.class));
        try {
            ProducerRecord<String, String> record = new ProducerRecord<String, String>(Constant.HELLO_TOPIC, "test", "123");
            Future<RecordMetadata> future = producer.send(record);
            System.out.println("do other things");
            RecordMetadata recordMetadata = future.get();
            if (null != recordMetadata) {
                System.out.println(recordMetadata.offset() + "," + recordMetadata.partition());
            }
        } catch (Exception e) {

        } finally {

        }
    }
}
