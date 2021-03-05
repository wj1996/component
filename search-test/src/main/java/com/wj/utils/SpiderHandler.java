package com.wj.utils;

import org.apache.commons.lang3.time.FastDateFormat;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class SpiderHandler {
    private SpiderService spiderService = new SpiderService();


    public void spiderData(Map<String,String> headers) {
        Date startDate = new Date();
        // 使用现线程池提交任务
        ExecutorService executorService = Executors.newFixedThreadPool(3);
        //引入countDownLatch进行线程同步，使主线程等待线程池的所有任务结束，便于计时
        int count = 92;
        int limit = count / SysConstant.pageSeg + 1;
        CountDownLatch countDownLatch = new CountDownLatch(limit);
        for(int i = 1; i < count; i += SysConstant.pageSeg) {
            Map<String, String> params = new HashMap<>();
            params.put("wc", "香水");
            params.put("enc", "utf-8");
            params.put("keyword", "香水");
//            params.put("page", i + "");
            params.put("pvid","b75f3e9ea5c74ff5ae264b02825ce91e");
            final int page = i;
            try {
                TimeUnit.SECONDS.sleep(new Random().nextInt(2));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            executorService.submit(() -> {
                spiderService.spiderData(SysConstant.BASE_URL, params,page,headers);
                countDownLatch.countDown();
            });
        }
       /* Map<String, String> params = new HashMap<>();
        params.put("pvid","beb4589c59384288be4c36fffe483707");
        params.put("wc", "手机");
        params.put("enc", "utf-8");
        params.put("keyword", "手机");
        params.put("page","2");
        spiderService.spiderData(SysConstant.BASE_URL, params);*/


        try {
            countDownLatch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        executorService.shutdown();
        Date endDate = new Date();

        FastDateFormat fdf = FastDateFormat.getInstance(SysConstant.DEFAULT_DATE_FORMAT);
        System.out.println("爬虫结束....");
        System.out.println("[开始时间:" + fdf.format(startDate) + ",结束时间:" + fdf.format(endDate) + ",耗时:"
                + (endDate.getTime() - startDate.getTime()) + "ms]");

    }
}
