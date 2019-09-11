package com.wj.spring.annotation.config;

import com.wj.spring.annotation.cls.Bike;
import com.wj.spring.base.Person;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class MyConfig4 {


    @Bean("getPerson")
    public Person person() {
        return new Person(1,"testannotation");
    }

    @Bean(value="bike",initMethod = "initBike",destroyMethod = "destroyBike")
    public Bike bike() {
        return new Bike();
    }

}
