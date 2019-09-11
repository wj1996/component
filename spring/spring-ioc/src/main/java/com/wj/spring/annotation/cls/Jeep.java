package com.wj.spring.annotation.cls;

import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

/**
 * 使用@Component方式
 */
@Component
public class Jeep {


    public Jeep() {
        System.out.println("Jeep()");
    }


    @PreDestroy
    public void destroy() throws Exception {
        System.out.println("Jeep destroy()");
    }

    @PostConstruct
    public void init() throws Exception {
        System.out.println("Jeep init()");
    }
}
