package com.wj;

import com.alibaba.dubbo.config.spring.context.annotation.DubboComponentScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@DubboComponentScan(basePackages = "com.wj")
public class BootApp {
    /*
    启动成功后 访问http://localhost:8090/swagger-ui.html  这个页面 ，集成了swagger
     */
    public static void main(String[] args) {
        SpringApplication.run(BootApp.class, args);
    }
}
