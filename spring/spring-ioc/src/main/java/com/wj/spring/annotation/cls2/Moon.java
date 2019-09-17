package com.wj.spring.annotation.cls2;

import org.springframework.stereotype.Component;

@Component
public class Moon {

    public Moon() {
        System.out.println("Moon()");
    }

    public void init() {
        System.out.println("moon init()");
    }

    public void destroy() {
        System.out.println("moon destroy()");
    }


 }
