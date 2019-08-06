package com.wj05.selfserial;

import com.wj.constant.Constant;
import com.wj.constant.KafkaConst;
import org.apache.kafka.clients.producer.Callback;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;
import org.apache.kafka.common.serialization.StringSerializer;

import java.io.IOException;

public class SelfProducer {


    public static void main(String[] args) throws IOException {
        KafkaProducer<String,DemoUser> producer = new KafkaProducer<String, DemoUser>(KafkaConst.producerConfig(StringSerializer.class,SelfSerializer.class));
        DemoUser demoUser = new DemoUser(1,"test");
        ProducerRecord<String,DemoUser> record = new ProducerRecord<String, DemoUser>(Constant.SELF_SERIAL_TOPIC,null,String.valueOf(System.currentTimeMillis()),demoUser);
        producer.send(record, new Callback() {
            public void onCompletion(RecordMetadata recordMetadata, Exception e) {
                if (null != e) {
                    e.printStackTrace();
                }
                if (null != recordMetadata) {
                    System.out.printf("偏移量：%s,分区：%s",recordMetadata.offset(),recordMetadata.partition());
                }
            }
        });

        System.in.read();
    }
}
