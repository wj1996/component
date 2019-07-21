package com.wj.application.zklock;

import org.I0Itec.zkclient.IZkDataListener;

import java.util.Collections;
import java.util.List;
import java.util.concurrent.CountDownLatch;

/**
 * 方式2基于zookeeper分布式锁的实现，创建有序的临时节点，每个节点只需要监听上一个节点，当这个节点删除时，才去获取锁，这样就可以避免羊群效应，第一种方式的实现
 * 当这个锁被释放时，所有需要这个锁资源的进程 都去获取，但是 ，只会有一个进程能获取到锁资源
 */
public class ZkDistributeLock2 extends ZkAbstractDistributeLock{

    private CountDownLatch countDownLatch = null;

    private String beforePath;
    private String currentPath;

    public ZkDistributeLock2() {
        if (!this.zkClient.exists(PATH2)) {
            this.zkClient.createPersistent(PATH2);
        }
    }

    public boolean tryLock() {
        if (null == currentPath || currentPath.length() <= 0) {
            currentPath = this.zkClient.createEphemeralSequential(PATH2 + "/","lock");
        }
        List<String> children = this.zkClient.getChildren(PATH2);
        if (null != children && children.size() > 0) {
            Collections.sort(children);
            if (currentPath.equals(PATH2 + "/" + children.get(0))) {
                return true;
            } else {
                int wz = Collections.binarySearch(children,currentPath.substring(7));
                beforePath = PATH2 + "/" + children.get(wz - 1);
            }
        }
        return false;
    }

    public void waitLock() {
        IZkDataListener listener = new IZkDataListener() {
            public void handleDataChange(String s, Object o) throws Exception {

            }

            public void handleDataDeleted(String s) throws Exception {
                if (countDownLatch != null) {
                    countDownLatch.countDown();
                }
            }
        };
        this.zkClient.subscribeDataChanges(beforePath,listener);
        if (this.zkClient.exists(beforePath)) {
            countDownLatch = new CountDownLatch(1);
            try {
                countDownLatch.await();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void unlock() {
        zkClient.delete(currentPath);
        zkClient.close();
    }
}
