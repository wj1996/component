package com.wj.api.zk;

import org.apache.zookeeper.*;
import org.apache.zookeeper.data.Stat;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.CountDownLatch;

/**
 * zookeeper原生API（官方提供的）
 */
public class CreateSession {

    private final static String CONNECTIONSTR = "10.0.0.141:2181";
    private static CountDownLatch countDownLatch = new CountDownLatch(1);

    public static void main(String[] args) throws IOException, InterruptedException, KeeperException {
        //创建zk，第三个参数，watcher
        final ZooKeeper zooKeeper = new ZooKeeper(CONNECTIONSTR, 5000, new Watcher() {
            public void process(WatchedEvent watchedEvent) {
                //如果已经获得连接了
                if(watchedEvent.getState() == Event.KeeperState.SyncConnected) {
                    countDownLatch.countDown();
                }
                if (watchedEvent.getType() == Event.EventType.NodeDataChanged) {
                    //如果数据发生了变化
                    System.out.println("节点发生了变化，变化的路径：" + watchedEvent.getPath());
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
//        zooKeeper.create("/node","node-value1".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);

        //获取数据
        /*Stat stat = new Stat();
        byte[] data = zooKeeper.getData("/node",true,stat);
        String result = new String(data);
        System.out.println(result);
        System.out.println(stat);*/

        //修改数据  -1代表不做版本控制
        zooKeeper.getData("/node",true,null);  //如果想让触发watch，必须先注册watch，设置为true，走默认的watch，只会生效一次
        zooKeeper.setData("/node","node-value2".getBytes(),-1);
        zooKeeper.getData("/node",true,null);
        zooKeeper.setData("/node","node-value3".getBytes(),-1);

        //删除数据
//        zooKeeper.delete("/node1",-1);

        //获取子节点
        /*List<String> children = zooKeeper.getChildren("/node1", true);
        System.out.println(children);*/

        /**
         *  watch机制是一次性的
         */

    }


}
