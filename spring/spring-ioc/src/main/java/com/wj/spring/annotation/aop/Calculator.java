package com.wj.spring.annotation.aop;


public class Calculator {


    public int div(int i, int j) {
        System.out.println("div");
//        if (j == 0) return 0;
        return i / j;
    }
}
