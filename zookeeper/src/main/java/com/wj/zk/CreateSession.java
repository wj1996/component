package com.wj.zk;

import org.apache.zookeeper.*;

import java.io.IOException;
import java.util.concurrent.CountDownLatch;

/**
 * zookeeper原生API（官方提供的）
 */
public class CreateSession {

    private final static String CONNECTIONSTR = "10.0.0.141:2181";
    private static CountDownLatch countDownLatch = new CountDownLatch(1);

    public static void main(String[] args) throws IOException, InterruptedException, KeeperException {
        //创建zk，第三个参数，watcher
        ZooKeeper zooKeeper = new ZooKeeper(CONNECTIONSTR, 5000, new Watcher() {
            public void process(WatchedEvent watchedEvent) {
                //如果已经获得连接了
                if(watchedEvent.getState() == Event.KeeperState.SyncConnected) {
                    countDownLatch.countDown();
                }
            }
        });
        countDownLatch.await();
        System.out.println(zooKeeper.getState());
        //获得连接后开始操作（增删改查）
        //需要确定已经连接成功
        //创建一个节点
        /**
         * 第一个参数：节点路径
         * 第二参数：节点value
         * 第三个参数：节点权限
         * 第四个参数：节点类型
         */
        zooKeeper.create("/node","node-value1".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
    }

}
