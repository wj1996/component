package com.wj.spring.annotation.cls;

import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;

/**
 * 使用@Component方式
 */
@Component
public class Train implements InitializingBean, DisposableBean {


    public Train() {
        System.out.println("Train()");
    }


    public void destroy() throws Exception {
        System.out.println("train destroy()");
    }

    //bean属性赋值和初始化完成调用
    public void afterPropertiesSet() throws Exception {
        System.out.println("train afterPropertiesSet()");
    }
}
