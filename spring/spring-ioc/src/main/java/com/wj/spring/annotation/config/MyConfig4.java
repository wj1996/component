package com.wj.spring.annotation.config;

import com.wj.spring.annotation.cls.Bike;
import com.wj.spring.base.Person;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;


@Configuration
@ComponentScan("com.wj.spring.annotation.cls")
public class MyConfig4 {


    @Bean("getPerson")
    public Person person() {
        return new Person(1,"testannotation");
    }

    /*
    * 通过@Scope和@Bean的方式是经常使用的
    * */

    @Bean(value="bike",initMethod = "initBike",destroyMethod = "destroyBike")
    public Bike bike() {
        return new Bike();
    }

}
