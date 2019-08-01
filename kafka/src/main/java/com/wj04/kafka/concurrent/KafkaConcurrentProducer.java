package com.wj04.kafka.concurrent;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Kafka多线程下的使用
 */
public class KafkaConcurrentProducer {

    private static final int MSG_SIZE = 1000;

    private static ExecutorService executorService = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());
    private static CountDownLatch countDownLatch = new CountDownLatch(MSG_SIZE);

    private static DemoUser makeUser(int id) {
        DemoUser demoUser = new DemoUser(id);
        String username = "test_" + id;
        demoUser.setName(username);
        return demoUser;
    }

    public static void main(String[] args) {

    }



    private static class ProducerWorker implements Runnable {

        private KafkaProducer<String,String> producer;
        private ProducerRecord<String,String> record;

        public ProducerWorker(KafkaProducer<String,String> producer,ProducerRecord<String,String> record) {
            this.producer = producer;
            this.record = record;
        }

        public void run() {

        }
    }

}

class DemoUser {
    private int id;
    private String name;

    public DemoUser(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
