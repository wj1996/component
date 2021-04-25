package com.wj.test;

import com.wj.RegistryInfo;
import com.wj.rpc.client.ILoadBalanceStrategy;
import com.wj.rpc.client.LoadBalanceRandomStrategy;
import org.junit.Test;

public class TestStrategy {

    @Test
    public void test() throws Exception {
        ILoadBalanceStrategy loadBalanceStrategy = new LoadBalanceRandomStrategy("server");
        RegistryInfo registryInfo = loadBalanceStrategy.find();
        System.out.println("1");

    }
}
