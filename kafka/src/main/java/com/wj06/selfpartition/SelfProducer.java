package com.wj06.selfpartition;

import com.wj.constant.Constant;
import com.wj.constant.KafkaConst;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;
import org.apache.kafka.common.serialization.StringSerializer;

import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

public class SelfProducer {

    public static void main(String[] args) throws IOException, ExecutionException, InterruptedException {
        Properties properties = KafkaConst.producerConfig(StringSerializer.class, StringSerializer.class);
        properties.put(ProducerConfig.PARTITIONER_CLASS_CONFIG,SelfPartitioner.class); //配置自定义分区器信息
        KafkaProducer<String, String> producer = new KafkaProducer<String, String>(properties);
        ProducerRecord<String,String> record = new ProducerRecord<String, String>(Constant.SELF_SERIAL_TOPIC,null,String.valueOf(System.currentTimeMillis()),"teacher01");

        Future<RecordMetadata> future = producer.send(record);
        RecordMetadata recordMetadata = future.get();
        if (null != recordMetadata) {
            System.out.printf("偏移量：%s,分区：%s",recordMetadata.offset(),recordMetadata.partition());
        }

        System.in.read();
    }
}
