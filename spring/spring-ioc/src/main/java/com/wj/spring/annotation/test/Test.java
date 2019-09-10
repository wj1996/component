package com.wj.spring.annotation.test;

import com.wj.spring.annotation.config.MyConfig;
import com.wj.spring.base.Person;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.util.Assert;

public class Test {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(MyConfig.class);

//        Person person = (Person) applicationContext.getBean("person");

//        System.out.println(person);

        String[] beanNamesForType = applicationContext.getBeanNamesForType(Person.class);
        Assert.notNull(beanNamesForType,"不能为空");

        for (String name : beanNamesForType) System.out.println(name);


    }
}
