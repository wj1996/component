package com.wj.spring.annotation.config;

import com.wj.spring.annotation.cls2.Bird;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;


@Configuration
@PropertySource("classpath:/my.properties")  //指定读取的配置文件  此处的数据会被加载在容器的环境变量中
public class MyConfig5 {

    @Bean
    public Bird bird() {
        return new Bird();
    }


}
