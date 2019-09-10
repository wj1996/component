package com.wj.spring.annotation.config;

import com.wj.spring.base.Person;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Conditional;
import org.springframework.context.annotation.Configuration;

/*
* 注解方式的
* */
@Configuration
public class MyConfig2 {

    @Bean("getPerson")
    public Person person() {
        return new Person(1,"testannotation");
    }


    @Conditional(MyCondition.class)
    @Bean("test2")
    public Person getPerson() {
        System.out.println("注册到容器中");
        return new Person(12,"test2");
    }


    /*
    * @Bean  第三方的jar包，需要注册到IOC容器中
    * 包扫描+组件的标注注解 @ComponentScan @Controller @Service @Repository  @Component    一般针对自己写的类
    * @Import 快速给容器导入一个组件  注意：@Bean有点简单
    * */



}
