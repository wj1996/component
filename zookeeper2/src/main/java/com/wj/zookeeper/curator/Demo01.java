package com.wj.zookeeper.curator;

import com.wj.zookeeper.Utils;
import org.apache.curator.RetryPolicy;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.recipes.cache.NodeCache;
import org.apache.curator.framework.recipes.cache.NodeCacheListener;
import org.apache.curator.framework.recipes.cache.PathChildrenCache;
import org.apache.curator.framework.recipes.cache.PathChildrenCacheListener;
import org.apache.curator.retry.ExponentialBackoffRetry;

import java.util.concurrent.CountDownLatch;

public class Demo01 {

    public static void main(String[] args) throws Exception {
        CountDownLatch countDownLatch = new CountDownLatch(1);
        CuratorFramework curatorFramework = CuratorFrameworkFactory.builder()
                .connectString("10.0.0.151:2181")
                .retryPolicy(new ExponentialBackoffRetry(1000,3))
                .build();
        curatorFramework.start();
//        testWatch(curatorFramework);
        testWatch2(curatorFramework);
        countDownLatch.await();
        curatorFramework.close();
    }
    public static void testWatch(CuratorFramework curatorFramework) throws Exception {
        NodeCache nodeCache = new NodeCache(curatorFramework,"/watch",false);
        NodeCacheListener nodeCacheListener = () -> {
            System.out.println("receive changed");
            System.out.println(nodeCache.getCurrentData().getPath() + "," + new String(nodeCache.getCurrentData().getData()));
        };
        nodeCache.getListenable().addListener(nodeCacheListener);
        nodeCache.start();
    }

    /**
     * 只能关注子节点上的动态变化 自身节点的变化 无法感知
     * @param curatorFramework
     * @throws Exception
     */
    public static void testWatch2(CuratorFramework curatorFramework) throws Exception {
        PathChildrenCache pathChildrenCache = new PathChildrenCache(curatorFramework,"/watch",true);
        PathChildrenCacheListener pathChildrenCacheListener = (cf,pathChildrenCacheEvent) -> {
            System.out.println("receive changed");
            Utils.printPathChildrenCacheEvent(pathChildrenCacheEvent);
        };
        pathChildrenCache.getListenable().addListener(pathChildrenCacheListener);
        pathChildrenCache.start();
    }
}
