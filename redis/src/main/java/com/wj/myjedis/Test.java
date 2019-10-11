package com.wj.myjedis;

import redis.clients.jedis.Jedis;

public class Test {

    public static void main(String[] args) {
        Jedis jedis = new Jedis("127.0.0.1",6379);
        jedis.set("key1","value1");
        jedis.close();
    }
}
