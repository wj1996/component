package com.wj.apply.getredpackage;

import com.wj.utils.JedisUtils;
import redis.clients.jedis.Jedis;

import java.util.UUID;
import java.util.concurrent.CountDownLatch;

public class GetRedPack {

    public static void getHongBao() throws InterruptedException {
        final CountDownLatch countDownLatch = new CountDownLatch(Basic.threadCount);
        final JedisUtils jedisUtils = new JedisUtils();
        for (int i = 0; i < Basic.threadCount; i++) {
            Thread thread = new Thread() {
                @Override
                public void run() {
                    Jedis jedis = jedisUtils.getJedis();
                    try {
                        while (true) {
                            String userId = UUID.randomUUID().toString();
                            Object object = jedis.eval(Basic.getHongBaoScript,4,Basic.hongBaoPoolKey,Basic.hongBaoDetailListKey,Basic.userIdRecordKey,userId);
                            if (null != object) {
                                System.out.println("用户ID号：" + userId + "，红包详情为：" + object);
                            } else {
                                if (jedis.llen(Basic.hongBaoPoolKey) == 0) {
                                    break;
                                }
                            }
                        }
                    } finally {
                       jedisUtils.returnResource(jedisUtils.getPool(),jedis);
                    }


                    countDownLatch.countDown();
                }
            };
            thread.start();
        }

        countDownLatch.await();
    }
}
