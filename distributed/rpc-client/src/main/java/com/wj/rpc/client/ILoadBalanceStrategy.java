package com.wj.rpc.client;

import com.wj.RegistryInfo;

public interface ILoadBalanceStrategy {
    RegistryInfo find() throws Exception;

}
