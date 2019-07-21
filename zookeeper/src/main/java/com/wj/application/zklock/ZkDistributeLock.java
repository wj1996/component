package com.wj.application.zklock;

import org.I0Itec.zkclient.IZkDataListener;

import java.util.concurrent.CountDownLatch;

/**
 * zk实现分布式锁之基于同名节点
 *      这个锁的问题：
 *          可能会出现羊群效应
 *          需要高性能分布式锁
 *              带序号的锁（排队）
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
        //注册时间
        zkClient.subscribeDataChanges(PATH,iZkDataListener);
        //如果节点存在
        if (zkClient.exists(PATH)) {
            try {
                countDownLatch = new CountDownLatch(1);
                countDownLatch.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void unlock() {
        if (null != zkClient) {
            zkClient.delete(PATH);
            zkClient.close();
            System.out.println("释放资源");
        }
    }
}
