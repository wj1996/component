package com.wj.service;

public class HelloSVImpl implements IHelloSV{


    @Override
    public String doService(String str) throws Exception {
        return "doService " + str;
    }
}
