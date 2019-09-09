package com.wj;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@MapperScan("com.wj.mapper")
@EnableEurekaClient   //标识是Eureka的客户端
@EnableDiscoveryClient  //标识服务发现客户端
public class ProductApp3 {
    public static void main(String[] args) {
        SpringApplication.run(ProductApp3.class,args);
    }
}