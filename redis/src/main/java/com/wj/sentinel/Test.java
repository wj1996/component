package com.wj.sentinel;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisSentinelPool;

import java.util.HashSet;
import java.util.Set;

/**
 * 关于sentinel测试
 */
public class Test {

    public static void main(String[] args) {
        Set<String> sentinelSet = new HashSet<String>();
        sentinelSet.add("10.0.0.141:26379");
        sentinelSet.add("10.0.0.141:26380");
        sentinelSet.add("10.0.0.141:26381");
        JedisSentinelPool pool = new JedisSentinelPool("mymaster",sentinelSet);

        Jedis resource = pool.getResource();

        resource.set("key-sentinel1","value-sentinel1");




    }

}
