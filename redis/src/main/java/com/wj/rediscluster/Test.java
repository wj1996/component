package com.wj.rediscluster;

import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.JedisCluster;

import java.util.HashSet;
import java.util.Set;

public class Test {


    public static void main(String[] args) {
        Set<HostAndPort> set = new HashSet<HostAndPort>();
        set.add(new HostAndPort("10.0.0.141",7001));
        set.add(new HostAndPort("10.0.0.141",7002));
        set.add(new HostAndPort("10.0.0.141",7003));
        set.add(new HostAndPort("10.0.0.141",7004));
        set.add(new HostAndPort("10.0.0.141",7005));
        set.add(new HostAndPort("10.0.0.141",7006));
        JedisCluster jedisCluster = new JedisCluster(set);
        String set1 = jedisCluster.set("key2", "value2");
        System.out.println(set1);



        int a = 2;
        int b = 1;
        int c = 3;
        int max = 0;
        max = (max = a > b ? a : b) > c ? max : c;
        System.out.println(max);
    }

}
