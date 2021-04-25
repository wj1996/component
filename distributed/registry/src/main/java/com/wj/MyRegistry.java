package com.wj;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.recipes.cache.NodeCache;
import org.apache.curator.framework.recipes.cache.PathChildrenCache;
import org.apache.curator.framework.recipes.cache.PathChildrenCacheEvent;
import org.apache.curator.framework.recipes.cache.PathChildrenCacheListener;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.apache.zookeeper.CreateMode;

import java.io.File;
import java.net.InetAddress;
import java.util.ArrayList;
import java.util.List;

public class MyRegistry {


    private static String connectString = "10.0.0.151:2181";
    private static CuratorFramework curatorFramework;
    private List<RegistryInfo> registryInfoList;
    private String sysCode;

    public List<RegistryInfo> getRegistryInfoList() {
        return registryInfoList;
    }

    static {
        curatorFramework = CuratorFrameworkFactory.builder()
                .connectString(connectString)
                .retryPolicy(new ExponentialBackoffRetry(1000,3))
                .build();
        curatorFramework.start();
    }

    public MyRegistry() {

    }

    public MyRegistry(String syscode) throws Exception {
        this.sysCode = syscode;
        this.registryInfoList = findSever();
        listen();
    }
    public void registry(RegistryInfo registryInfo) {
        InetAddress localHost = null;
        try {
            localHost = InetAddress.getLocalHost();
            String hostAddress = localHost.getHostAddress();
            curatorFramework.create().creatingParentsIfNeeded().withMode(CreateMode.EPHEMERAL).forPath("/server/ip/" + (registryInfo.getIp() + ":" + registryInfo.getPort()),"".getBytes());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public List<RegistryInfo> findSever() throws Exception {
        String path = "/" + this.sysCode + "/ip";
        List<String> list = curatorFramework.getChildren().forPath(path);
        if (null == list || list.isEmpty()) return null;
        List<RegistryInfo> registryInfoList = new ArrayList<>();
        list.forEach(str -> {
            String[] split = str.split(":");
            registryInfoList.add(RegistryInfo.builder()
                    .ip(split[0])
                    .port(Integer.valueOf(split[1]))
                    .build());
        });
        return registryInfoList;
    }

    public void listen() throws Exception {
        String path = "/" + this.sysCode + "/ip";
        PathChildrenCache pathChildrenCache = new PathChildrenCache(curatorFramework,path,true);
        PathChildrenCacheListener pathChildrenCacheListener = (cf,pathChildrenCacheEvent) -> {
            if (pathChildrenCacheEvent.getType() == PathChildrenCacheEvent.Type.CHILD_ADDED || pathChildrenCacheEvent.getType() == PathChildrenCacheEvent.Type.CHILD_REMOVED) {
                registryInfoList = findSever();
            }
        };
        pathChildrenCache.getListenable().addListener(pathChildrenCacheListener);
        pathChildrenCache.start();
    }
    public void scan(String path) {
        File file = new File(path);
        if (!file.exists())
            throw new RuntimeException("文件不存在:" + path);

        if (file.isDirectory()) {

        } else {

        }

    }

    private void handleFile(File file) {
        if(file.getName().endsWith(".class")) {

        }
    }





    public static void main(String[] args) throws Exception {
      /*  InetAddress localHost = InetAddress.getLocalHost();
//        System.out.println(localHost.getAddress());
        System.out.println(localHost.getHostAddress());
//        System.out.println(localHost.getHostName());
        Enumeration<NetworkInterface> networkInterfaces = NetworkInterface.getNetworkInterfaces();
        while (networkInterfaces.hasMoreElements()) {
            NetworkInterface networkInterface = networkInterfaces.nextElement();
            Enumeration<InetAddress> inetAddresses = networkInterface.getInetAddresses();
            while (inetAddresses.hasMoreElements()) {
                InetAddress inetAddress = inetAddresses.nextElement();
                System.out.println(inetAddress.getHostAddress());
            }
            System.out.println("-----------------------------");
        }*/
        curatorFramework.create().creatingParentsIfNeeded().withMode(CreateMode.EPHEMERAL).forPath("/server/ip","10.0.0.0".getBytes());
        Thread.sleep(10000);
        curatorFramework.close();
    }

}
