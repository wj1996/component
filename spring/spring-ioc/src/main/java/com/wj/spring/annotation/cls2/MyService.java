package com.wj.spring.annotation.cls2;

import org.springframework.stereotype.Service;

//@Service("myService3")
public class MyService {


    private int flag;

    public int getFlag() {
        return flag;
    }

    public void setFlag(int flag) {
        this.flag = flag;
    }

    public MyService() {
    }

    public MyService(int flag) {
        this.flag = flag;
    }

    public void test() {
        System.out.println("myService>" + flag);
    }
}
