package com.wj.api.zkclient;

import org.I0Itec.zkclient.IZkChildListener;
import org.I0Itec.zkclient.IZkDataListener;
import org.I0Itec.zkclient.ZkClient;

import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * zkClient使用
 */
public class CreateSession {

    private final static String CONNECTIONSTR = "10.0.0.141:2181";

    public static void main(String[] args) throws InterruptedException {

        ZkClient zkClient = new ZkClient(CONNECTIONSTR,4000);
//        System.out.println(zkClient + " --- > success");

        //级联创建
        /*zkClient.createPersistent("/zkClient/zkClient1/zkClient2",true); //级联创建，必须为true
        System.out.println("success");*/
        //级联删除
//        zkClient.deleteRecursive("/zkClient");

        //获取子节点
       /* List<String> children = zkClient.getChildren("/node1");
        System.out.println(children);*/

       //watch机制

        //数据内容变化
        zkClient.subscribeDataChanges("/node1", new IZkDataListener() {
            public void handleDataChange(String s, Object o) throws Exception {
                System.out.println("节点名称：" + s + "节点修改后的值:" + o);
            }

            public void handleDataDeleted(String s) throws Exception {

            }
        });

        zkClient.writeData("/node1","node2");
        TimeUnit.SECONDS.sleep(2); //为何要阻塞

        //数据结点变化触发


        zkClient.subscribeChildChanges("/node1", new IZkChildListener() {
            public void handleChildChange(String s, List<String> list) throws Exception {
                System.out.println("节点名称：" + s + ",当前的节点列表：" + list);
            }
        });

        zkClient.delete("/node1/node2-1");
        TimeUnit.SECONDS.sleep(2);
    }
}
