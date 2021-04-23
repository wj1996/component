package com.wj.rpc.client;

import com.wj.RegistryInfo;

public interface ILoadBalanceStrategy {

    public RegistryInfo findRegistryInfo() throws Exception;

}
