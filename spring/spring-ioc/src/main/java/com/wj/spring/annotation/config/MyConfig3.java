package com.wj.spring.annotation.config;

import com.wj.spring.annotation.cls.*;
import com.wj.spring.base.Person;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Conditional;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/*
* 注解方式的
* */
@Configuration
@Import({Dog.class, Cat.class, MyImportSelector.class, MyImportBeanDefinitionRegistrar.class})
public class MyConfig3 {

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
    *   容器会自动注册这个Bean到IOC容器，id为全类名
    *   ImportSelector ：是一个接口，返回需要导入到容器的组件的全类名数组  见MyImportSelector
    *   ImportBeanDefinitionRegistrar:  见MyImportBeanDefinitionRegistrar  可以手动添加组件到IOC容器，所有bean的注册可以使用BeanDefinitionRegistry
    *
    * FactoryBean接口
    *   工厂bean进行注册
    *
    *
    * */

    @Bean
    public MyFactoryBean factoryBean() {
        return new MyFactoryBean();
    }


}
