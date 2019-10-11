package com.wj.apply.getredpackage;

import com.alibaba.fastjson.JSONObject;
import com.wj.utils.JedisUtils;

import java.util.concurrent.CountDownLatch;

public class GenRedPack {

    public static void genHongBao() throws InterruptedException {
        final JedisUtils jedisUtils = new JedisUtils();

        //发枪器
        final CountDownLatch countDownLatch = new CountDownLatch(Basic.threadCount);

        for (int i = 0; i < Basic.threadCount; i++) {
            final int page = i;
            Thread thread = new Thread(){
                public void run() {
                    //每个线程要初始化多少个红包
                    int per = Basic.hongBaoCount / Basic.threadCount;

                    JSONObject object = new JSONObject();
                    for (int j = page * per; j < (page + 1) * per; j++) {
                        object.put("id","rid" + j); //红包ID
                        object.put("money",j); //红包金额
                        jedisUtils.lpush("hongBaoPoolKey",object.toJSONString());
                    }

                    countDownLatch.countDown();
                }
            };

            thread.start();
        }

        countDownLatch.await();
    }
}
