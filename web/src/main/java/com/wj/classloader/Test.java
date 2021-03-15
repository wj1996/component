package com.wj.classloader;

import sun.net.spi.nameservice.dns.DNSNameService;

public class Test {

    static {
        System.out.println("test class load");
    }

    public static void main(String[] args) throws Exception {
        //加载Ext下的jar包内容 （使用ExtClassLoader加载）
        DNSNameService dnsNameService = new DNSNameService();
        System.out.println("haha");
        Test test = new Test();
        //使用AppClassLoader加载Test

    }
}
