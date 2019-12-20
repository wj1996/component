package com.wj.test;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class MyTest {

    public static void main(String[] args) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        Class clz = MyTest2.class;
        Method method = clz.getMethod("main",String[].class);
        method.invoke(null,new Object[]{null});
    }
}


