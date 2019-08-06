package com.wj04.kafka.concurrent;

import com.wj.constant.Constant;
import com.wj.constant.KafkaConst;
import org.apache.kafka.clients.producer.Callback;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;
import org.apache.kafka.common.serialization.StringSerializer;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Kafka多线程下的使用
 *
 *
 * ProducerConfig
 *      ！acks   必须要多少个分区副本收到了消息
 *             0  代表不可靠
 *             1   默认
 *             all  可靠性最高 ，延迟最高
 *             银行业务：
 *                  5个副本，跨集群
 *                  跨机架
 *                  跨异地
 *                  机架感知
 *      buffer.memory
 *          缺省 32M（字节）
 *      max.block.ms
 *          缺省 60000ms 60s  TimeoutException  getMetaData 获取元数据超时，这个可能需要做相关配置
 *      retries
 *          缺省 0  不重试
 *          retry.backoff.ms
 *              重发和上一次发送的时间间隔
 *      ！batch.size
 *          限制一次发送，有多少消息作为一个队列，不是消息条数，按消息占用内存大小
 *          默认16kb左右
 *      ！linger.ms
 *          发送消息时候，等待时间
 *          默认0，不等待  可以根据业务调整，消息不是时时刻刻都有发送的，调整后，可以提高系统的吞吐量，但是会造成某些消息的延迟
 *      compression.type
 *          none,gzip,snappy 压缩可采用的类型
 *              gzip  占CPU较高，压缩比可观
 *              snappy  占CPU少，压缩率跟gzip比差
 *
 *              带宽不足，使用gzip
 *      client.id
 *          识别消息
 *      max.in.flight.requests.per.connection
 *          每个连接里面的请求发送后，等待几个请求响应后 再发送请求 如果设置为1  每次都需要等待 请求响应后 才能再次发送请求
 *      ！max.request.size
 *          控制生产者发送一次请求的大小，
 *          跟服务器上的一个参数配置相关：message.max.bytes
 *          生产者配置的一此请求的大小超过了服务器配置的上述参数，kafka是可能会拒绝接收这个消息的，正常情况下，这两个值设置为一致
 *
 */
public class KafkaConcurrentProducer {

    private static final int MSG_SIZE = 1000;
    //发送消息的线程池
    private static ExecutorService executorService = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());
    private static CountDownLatch countDownLatch = new CountDownLatch(MSG_SIZE);

    private static DemoUser makeUser(int id) {
        DemoUser demoUser = new DemoUser(id);
        String username = "test_" + id;
        demoUser.setName(username);
        return demoUser;
    }

    public static void main(String[] args) {
        KafkaProducer<String,String> producer = new KafkaProducer<String, String>(KafkaConst.producerConfig(StringSerializer.class,StringSerializer.class));
        try {
            for (int i = 0; i < MSG_SIZE; i++) {
                DemoUser demoUser = new DemoUser(i);
                ProducerRecord<String,String> record = new ProducerRecord<String, String>(Constant.CONCURRENT_USER_INFO_TOPIC,null,String.valueOf(System.currentTimeMillis()),demoUser.getId() + "" + demoUser.toString());
                executorService.submit(new ProducerWorker(producer,record));
            }
            countDownLatch.await();
        } catch (Exception e) {

        } finally {
            producer.close();
            executorService.shutdown();
        }

    }


    /**
     * 发送消息任务
     */
    private static class ProducerWorker implements Runnable {

        private KafkaProducer<String,String> producer;
        private ProducerRecord<String,String> record;

        public ProducerWorker(KafkaProducer<String,String> producer,ProducerRecord<String,String> record) {
            this.producer = producer;
            this.record = record;
        }

        public void run() {
            final String id = Thread.currentThread().getId() + "-" + System.identityHashCode(producer);
            try {
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
                System.out.println(id + ":数据[" + record + "]已发送");
                countDownLatch.countDown();
            } catch (Exception e) {
                e.printStackTrace();
            }

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
