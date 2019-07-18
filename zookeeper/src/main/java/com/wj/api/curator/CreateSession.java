package com.wj.api.curator;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.api.BackgroundCallback;
import org.apache.curator.framework.api.CuratorEvent;
import org.apache.curator.framework.api.transaction.CuratorTransactionResult;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.data.Stat;

import java.util.Collection;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Curator使用
 */
public class CreateSession {

    private final static String CONNECTIONSTR = "10.0.0.141:2181";

    public static void main(String[] args) throws Exception {
        //创建会话两种方式
//        CuratorFramework curatorFramework = CuratorFrameworkFactory.newClient(CONNECTIONSTR,5000,5000,
//                new ExponentialBackoffRetry(1000,3));
        /**
         * 重试连接3次，1s中重试3次吗？？？？，后面进行时间的叠加，不是固定的
         */
//        curatorFramework.start(); //start启动连接

        //fluent风格(链式编程)
        CuratorFramework curatorFramework = CuratorFrameworkFactory.builder().connectString(CONNECTIONSTR)
                .sessionTimeoutMs(5000).retryPolicy(new ExponentialBackoffRetry(1000,3)).build();
        curatorFramework.start();

        System.out.println("success");

        //创建
        /*String result = curatorFramework.create().creatingParentsIfNeeded().withMode(CreateMode.PERSISTENT)
                .forPath("/curator/curator1/curator2","123".getBytes());
        System.out.println(result);*/

        //删除节点
//        curatorFramework.delete().deletingChildrenIfNeeded().forPath("/curator");

        //查询节点
       /* Stat stat = new Stat();
        byte[] bytes = curatorFramework.getData().storingStatIn(stat).forPath("/node1");
        System.out.println(new String(bytes) + "," + stat);*/
        //更新节点

       /* Stat stat1 = curatorFramework.setData().forPath("/node1", "123".getBytes());
        System.out.println(stat1);*/

       //异步操作
//        ExecutorService service = Executors.newFixedThreadPool(1);
//        final CountDownLatch countDownLatch = new CountDownLatch(1);
//        try {
//            curatorFramework.create().creatingParentContainersIfNeeded().withMode(CreateMode.EPHEMERAL)
//                    .inBackground(new BackgroundCallback() {
//                        public void processResult(CuratorFramework curatorFramework, CuratorEvent curatorEvent) throws Exception {
//                            System.out.println(Thread.currentThread().getName() + "->resultCode" + curatorEvent.getResultCode() + "->" + curatorEvent.getType());
//                            countDownLatch.countDown();
//                        }
//                    },service).forPath("/node1","deer".getBytes());
//
//        } catch (Exception e) {
//
//        }
//        countDownLatch.await();
//        service.shutdown();

        //事务操作（curator独有）
        try {
            Collection<CuratorTransactionResult> resultCollection = curatorFramework.inTransaction().
                    create().forPath("/demo1","111".getBytes())
                    .and().setData().forPath("/demo2","112".getBytes()).and().commit();
            for (CuratorTransactionResult result : resultCollection) {
                System.out.println(result.getForPath() + "->" + result.getType());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
