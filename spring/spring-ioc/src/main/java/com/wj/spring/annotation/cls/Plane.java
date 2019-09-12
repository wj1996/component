package com.wj.spring.annotation.cls;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

@Component
public class Plane implements ApplicationContextAware {

    private ApplicationContext applicationContext;


    public Plane() {
        System.out.println("Plane()");
    }



    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        //可以获取到上下文
        this.applicationContext = applicationContext;
    }
}
