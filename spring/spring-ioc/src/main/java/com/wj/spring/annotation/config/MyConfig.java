package com.wj.spring.annotation.config;

import com.wj.spring.base.Person;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;

/*
* 注解方式的
* */
@Configuration
@ComponentScan(value = "com.wj", includeFilters = {
     @ComponentScan.Filter(type = FilterType.CUSTOM,classes = {MyTypeFilter.class})
},useDefaultFilters = false)
public class MyConfig {

    //默认将方法名作为bean的id
    //指定bean的id
    @Bean("getPerson")
    public Person person() {
        return new Person(1,"testannotation");
    }
}
