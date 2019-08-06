package com.wj06.selfpartition;

import org.apache.kafka.clients.producer.Partitioner;
import org.apache.kafka.common.Cluster;
import org.apache.kafka.common.PartitionInfo;

import java.util.List;
import java.util.Map;

public class SelfPartitioner implements Partitioner {

    public int partition(String topic, Object key, byte[] keyBytes, Object value, byte[] valueBytes, Cluster cluster) {
        //以value值进行分区
        List<PartitionInfo> partitionInfos = cluster.partitionsForTopic(topic);
        int num = partitionInfos.size();
        int parId = (parId = ((String)value).hashCode() % num) > 0 ? parId : 0;
        return parId;
    }

    public void close() {

    }

    public void configure(Map<String, ?> configs) {

    }
}
