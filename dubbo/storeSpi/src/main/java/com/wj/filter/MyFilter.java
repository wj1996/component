package com.wj.filter;

import com.alibaba.dubbo.common.extension.Activate;
import com.alibaba.dubbo.rpc.*;

import java.net.InetAddress;
import java.util.HashMap;
import java.util.Map;
@Activate
public class MyFilter implements Filter {


    public Result invoke(Invoker<?> invoker, Invocation invocation) throws RpcException {
        Map<String,String> rpcIp = new HashMap<String,String>();
        try {
            String ip = InetAddress.getLocalHost().getHostAddress();
            rpcIp.put("rpcIp",ip);
            RpcContext.getContext().setAttachments(rpcIp);
            System.out.println("add rpcIp:" + ip);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return invoker.invoke(invocation);
    }
}
