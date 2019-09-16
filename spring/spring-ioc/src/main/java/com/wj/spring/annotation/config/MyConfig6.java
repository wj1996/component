package com.wj.spring.annotation.config;

import com.wj.spring.annotation.cls2.MyService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;


@Configuration
@ComponentScan("com.wj.spring.annotation.cls2")
public class MyConfig6 {



//    @Primary
    @Bean("myService2")
//    @Bean
    public MyService myService() {
        return new MyService(2);
    }

}
