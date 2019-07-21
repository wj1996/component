package com.wj.application.zklock;

public class Test {


    /**
     * 注意debug调式程序时，会带来zookeeper临时有序节点的删除,所以加睡眠 和在 监听处打断点来调式证明分布式锁的实现
     * @param args
     */
    public static void main(String[] args) {
        AbstractLock lock2 = new ZkDistributeLock2();
        lock2.getLock();
        System.out.println("加锁成功");
        try {
            Thread.sleep(1000 * 10 * 6);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        lock2.unlock();
    }
}
