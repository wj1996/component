package com.wj;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
/*
* 这个注解就是@EnableAutoConfiguration和@ComponentScan的组合实现，再加上一些别的实现
* 扫描包的路径就是当前类所在的路径下的所有包
* */
public class App2 {

    public static void main(String[] args) {
        //启动类
        //定制化
        SpringApplication.run(App2.class);
    }



}
