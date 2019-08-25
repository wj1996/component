package com.wj.loadbalance;

import com.alibaba.dubbo.common.URL;
import com.alibaba.dubbo.rpc.Invocation;
import com.alibaba.dubbo.rpc.Invoker;
import com.alibaba.dubbo.rpc.RpcException;

import java.util.List;

public class FirstLoadBalance implements com.alibaba.dubbo.rpc.cluster.LoadBalance {


    /**
     *
     * @param list 所有provider的实现
     * @param url
     * @param invocation
     * @param <T>
     * @return
     * @throws RpcException
     */
    public <T> Invoker<T> select(List<Invoker<T>> list, URL url, Invocation invocation) throws RpcException {
        System.out.println("启动第一个：" + list.size());
        return list.get(0);
    }
}
