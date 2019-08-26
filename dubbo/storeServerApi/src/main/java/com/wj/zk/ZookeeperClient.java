package com.wj.zk;

import org.I0Itec.zkclient.IZkDataListener;
import org.I0Itec.zkclient.ZkClient;

public class ZookeeperClient {

    private static ZkClient zkClient;

    public static void main(String[] args) throws InterruptedException {
        zkClient = new ZkClient("10.0.0.141:2181",Integer.MAX_VALUE);
        listen();

        zkClient.createPersistent("/super","1234");
        Thread.sleep(1000);
        zkClient.writeData("/super","456",-1);
        Thread.sleep(1000);
        zkClient.delete("/super");
        Thread.sleep(1000);
    }

    /**
     * 监听数据的变化
     */
    public static void listen() {
      zkClient.subscribeDataChanges("/super", new IZkDataListener() {
          public void handleDataChange(String s, Object o) throws Exception {
              System.out.println("变更的结点为：" + s + "，变更的内容为：" + o);
          }

          public void handleDataDeleted(String s) throws Exception {
              System.out.println("删除的节点为：" + s);
          }
      });
    }
}
