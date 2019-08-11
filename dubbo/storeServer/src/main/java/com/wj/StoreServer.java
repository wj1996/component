package com.wj;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.IOException;
import java.util.concurrent.CountDownLatch;

public class StoreServer {
    public static void main(String[] args) throws IOException {
        /**
         * dubbo.xml
         * dubbo_annotation.xml
         */
        CountDownLatch countDownLatch = new CountDownLatch(1);
        ClassPathXmlApplicationContext context =
                new ClassPathXmlApplicationContext("classpath:dubbo.xml");
        context.start();

        System.out.println("-----dubbo开启-----");
        try {
            countDownLatch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
//        System.in.read(); // 为保证服务一直开着，利用输入流的阻塞来模拟
    }
}
