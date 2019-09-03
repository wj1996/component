package com.wj;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * SpringBoot入门案例
 */
@RestController    //就是个组合注解 controller+ResponseBody+...组合的注解
@EnableAutoConfiguration
public class Example {

    @RequestMapping("/")
    String home() {
        return "hello world";
    }


    public static void main(String[] args) {
        //启动类
        //定制化
        SpringApplication.run(Example.class);
    }

}
