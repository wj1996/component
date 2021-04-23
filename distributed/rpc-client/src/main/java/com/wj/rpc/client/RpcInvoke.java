package com.wj.rpc.client;


import com.wj.Data;
import com.wj.MyRegistry;
import com.wj.RegistryInfo;
import org.apache.log4j.Logger;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.net.ConnectException;
import java.net.Socket;

public class RpcInvoke<T> {
    private static Logger logger = Logger.getLogger(RpcInvoke.class);

    public RpcInvoke() throws Exception {

    }

    public T getSV(Class clazz) {
        T t = (T) Proxy.newProxyInstance(clazz.getClassLoader(), new Class[]{clazz}, new InvocationHandler() {

            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
               return handle(clazz,method,args,0);
            }
        });
        return t;
    }

    private Object handle(Class clazz,Method method,Object[] args,int retry) throws Exception {
        try {
            return myInvoke(clazz, method, args);
        } catch (Exception e) {
            if (e instanceof ConnectException && retry < 3) {
                return handle(clazz,method,args,retry + 1);
            }
            throw e;
        }
    }

    private Object myInvoke(Class clazz,Method method,Object[] args) throws Exception {
        RegistryInfo registryInfo = myRegistry.findOneServer("server");
        Socket socket = new Socket(registryInfo.getIp(),registryInfo.getPort());
        logger.info("connect success：" + registryInfo.getIp() + ":" + registryInfo.getPort());
        Data data = Data.builder()
                .className(getImplName(clazz.getTypeName()))
                .methodName(method.getName())
                .paramTypes(method.getParameterTypes())
                .paramValues(args)
                .build();
        logger.info(data);
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
        objectOutputStream.writeObject(data);
        objectOutputStream.flush();
        ObjectInputStream objectInputStream = new ObjectInputStream(socket.getInputStream());
        Object o = objectInputStream.readObject();
        if (o instanceof Exception) {
            throw (Exception)o;
        }
        return o;
    }

    private String getImplName(String name) {
        int index = name.lastIndexOf(".");
        String pkg = name.substring(0,index+1);
        return pkg + name.substring(index + 2) + "Impl";
    }

}
