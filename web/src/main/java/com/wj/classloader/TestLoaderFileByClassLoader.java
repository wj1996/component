package com.wj.classloader;

import java.net.URL;

public class TestLoaderFileByClassLoader {

    public static void main(String[] args) {
        //使用加载当前类的类加载器加载文件
        ClassLoader classLoader = TestLoaderFileByClassLoader.class.getClassLoader(); //AppClassLoader
        //资源写在了包里面
        URL resource = classLoader.getResource("my.file"); //不能加载到
        resource = classLoader.getResource("/com/wj/my.file"); //不能加载到
        resource = classLoader.getResource("com/wj/my.file"); //能
        //资源直接加载到classpath下
        resource = classLoader.getResource("s.xml");
        resource = classLoader.getResource("/spring/s.xml");
        resource = classLoader.getResource("spring/s.xml");



        System.out.println("1");

    }
}
