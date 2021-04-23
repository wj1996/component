package com.wj.api.curator;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.apache.zookeeper.ZooDefs;
import org.apache.zookeeper.data.ACL;
import org.apache.zookeeper.data.Id;
import org.apache.zookeeper.data.Stat;
import org.apache.zookeeper.server.auth.DigestAuthenticationProvider;

import java.util.Collections;

public class Demo01 {



    public static void main(String[] args) throws Exception {
        CuratorFramework curatorFramework = CuratorFrameworkFactory.builder()
                .connectString("10.0.0.151:2181")
                .retryPolicy(new ExponentialBackoffRetry(1000,3))
                .build();
        curatorFramework.start();

//        curatorFramework.create().forPath("/tt2","test".getBytes());
//        curatorFramework.setData().forPath("/tt2","test2".getBytes());

       /* ACL acl = new ACL(ZooDefs.Perms.READ,new Id("digest", DigestAuthenticationProvider.generateDigest("admin:admin")));
        Stat stat = new Stat();
        curatorFramework.create().withACL(Collections.singletonList(acl)).forPath("/t3","hello world".getBytes());*/
        test1(curatorFramework);
        curatorFramework.close();
    }

    public static void test1(CuratorFramework curatorFramework) throws Exception {
        ACL acl = new ACL(ZooDefs.Perms.READ | ZooDefs.Perms.WRITE,new Id("digest", DigestAuthenticationProvider.generateDigest("admin:admin")));
        curatorFramework.setACL().withACL(Collections.singletonList(acl)).forPath("/tmp");
    }
}
