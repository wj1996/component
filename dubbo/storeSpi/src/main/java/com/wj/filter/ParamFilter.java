package com.wj.filter;

import com.alibaba.dubbo.common.extension.Activate;
import com.alibaba.dubbo.rpc.*;

@Activate
public class ParamFilter implements Filter {

    public Result invoke(Invoker<?> invoker, Invocation invocation) throws RpcException {
        String ip = RpcContext.getContext().getAttachment("rpcIp");
        System.out.println("get rpcIp:" + ip);
        return invoker.invoke(invocation);
    }
}
