package com.wj.service;


import com.wj.Rpc;

@Rpc("hellWorldService")
public class HelloWorldService implements IHelloWorldService {

    @Override
    public String doService(String str,int i) {
        return "hello world" + str + i;
    }
}
