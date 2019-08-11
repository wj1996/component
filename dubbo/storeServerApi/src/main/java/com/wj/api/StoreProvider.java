package com.wj.api;

import com.alibaba.dubbo.config.ApplicationConfig;
import com.alibaba.dubbo.config.ProtocolConfig;
import com.alibaba.dubbo.config.RegistryConfig;
import com.alibaba.dubbo.config.ServiceConfig;
import com.wj.service.VipUserService;
import com.wj.service.impl.VipUserServiceImpl;

import java.io.IOException;

/**
 * dubbo api方式发布dubbo应用（实际项目中不会被使用）
 */
public class StoreProvider {
    public static void main(String[] args) throws IOException {
        initDubbo();
    }

    public static void initDubbo() throws IOException {
            // 当前应用配置
            ApplicationConfig application = new ApplicationConfig();
            application.setName("StoreServerApi");

            // 连接注册中心配置
            RegistryConfig registry = new RegistryConfig();
            registry.setProtocol("zookeeper");
            registry.setAddress("10.0.0.141:2181");

            // 服务提供者协议配置
            ProtocolConfig protocol = new ProtocolConfig();
            protocol.setName("rmi");
            protocol.setPort(21880);
            protocol.setThreads(100);

            // 注意：ServiceConfig为重对象，内部封装了与注册中心的连接，以及开启服务端口
            // 服务提供者暴露服务配置
            // 此实例很重，封装了与注册中心的连接，请自行缓存，否则可能造成内存和连接泄漏
            ServiceConfig<VipUserService> service = new ServiceConfig<VipUserService>();

            service.setApplication(application);
            service.setRegistry(registry); // 多个注册中心可以用setRegistries()
            service.setProtocol(protocol); // 多个协议可以用setProtocols()
            service.setInterface(VipUserService.class);
            service.setRef(new VipUserServiceImpl());

            // 暴露及注册服务
            service.export();

            System.in.read();

    }
}
