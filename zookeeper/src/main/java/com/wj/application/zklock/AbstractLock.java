package com.wj.application.zklock;

public abstract class AbstractLock {

    public void getLock() {
        if (tryLock()) {
            System.out.println("获取锁资源成功");
        } else {
            waitLock();
            getLock();
        }
    }

    public abstract boolean tryLock();

    public abstract void waitLock();

    public abstract void unlock();
}
