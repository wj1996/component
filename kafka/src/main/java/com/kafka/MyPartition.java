package com.kafka;

import org.apache.kafka.clients.producer.Partitioner;
import org.apache.kafka.common.Cluster;
import org.apache.kafka.common.PartitionInfo;

import java.util.List;
import java.util.Map;
import java.util.Random;

/**
 * 自定义分区策略
 */
public class MyPartition implements Partitioner {

    private Random random = new Random();

    public int partition(String s, Object o, byte[] bytes, Object o1, byte[] bytes1, Cluster cluster) {
        List<PartitionInfo> list = cluster.partitionsForTopic("test2"); //获取topic的所有分区信息
        int partitionNum = 0;
        if (o == null) {
            partitionNum = random.nextInt(list.size());
        } else {
            partitionNum = Math.abs(s.hashCode()) % list.size();
        }
        System.out.println("key->" + o + ",value->" + o1 + "->" +partitionNum);
        return partitionNum; //指定发送的分区
    }

    public void close() {

    }

    public void configure(Map<String, ?> map) {

    }
}
