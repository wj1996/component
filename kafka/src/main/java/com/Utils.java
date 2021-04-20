package com;

import org.apache.kafka.clients.producer.RecordMetadata;

public class Utils {

    public static void dealRecordMetadata(RecordMetadata recordMetadata) {
        if (null == recordMetadata ) {
            return;
        }
        System.out.printf("partition:%s,offset:%s\n",recordMetadata.partition(),recordMetadata.offset());
    }
}
