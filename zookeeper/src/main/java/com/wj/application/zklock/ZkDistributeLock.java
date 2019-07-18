package com.wj.application.zklock;

import org.I0Itec.zkclient.IZkDataListener;

import java.util.concurrent.CountDownLatch;

/**
 * zk实现分布式锁之同步
 */
public class ZkDistributeLock extends ZkAbstractDistributeLock{

    private CountDownLatch countDownLatch = null;

    public boolean tryLock() {
        try {
            zkClient.createEphemeral(PATH);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public void waitLock() {
        IZkDataListener iZkDataListener = new IZkDataListener() {

            public void handleDataChange(String s, Object o) throws Exception {

            }

            public void handleDataDeleted(String s) throws Exception {
                countDownLatch.countDown();
            }
        };
        try {
            countDownLatch = new CountDownLatch(1);
            countDownLatch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
