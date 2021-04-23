package com.wj.rpc.client;

import com.wj.MyRegistry;
import com.wj.RegistryInfo;
import org.apache.log4j.Logger;

import java.util.List;

public abstract class AbstractLoadBalanceStrategy implements ILoadBalanceStrategy {

    private static Logger logger = Logger.getLogger(AbstractLoadBalanceStrategy.class);
    private MyRegistry myRegistry;

    public List<RegistryInfo> getRegistryInfo() {
        return myRegistry.getRegistryInfoList();
    }

    public AbstractLoadBalanceStrategy(String sysCode) throws Exception {
        myRegistry = new MyRegistry(sysCode);

    }
    public RegistryInfo find() throws Exception {
        List<RegistryInfo> registryInfoList = getRegistryInfo();
        if (null == getRegistryInfo() || registryInfoList.isEmpty()) throw new RuntimeException("服务地址列表为空");
        if (registryInfoList.size() == 1) return registryInfoList.get(0);
        return findRegistryInfo();
        myRegistry.listen();
    }
}
