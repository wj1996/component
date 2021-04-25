package com.wj.rpc.client;

import com.wj.RegistryInfo;

import java.util.Random;

public class LoadBalanceRandomStrategy extends AbstractLoadBalanceStrategy {

    public LoadBalanceRandomStrategy(String sysCode) throws Exception {
        super(sysCode);
    }
    @Override
    public RegistryInfo findRegistryInfo() throws Exception {
        Random random = new Random();
        return getRegistryInfo().get(random.nextInt(getRegistryInfo().size()));
    }

}
