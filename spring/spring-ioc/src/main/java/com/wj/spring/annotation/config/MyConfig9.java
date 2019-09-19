package com.wj.spring.annotation.config;

import com.wj.spring.annotation.cls2.Moon;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;


@Configuration
@ComponentScan("com.wj.spring.annotation.extend.processor")
public class MyConfig9 {

    @Bean
    public Moon getMoon() {
        return new Moon();
    }

}
