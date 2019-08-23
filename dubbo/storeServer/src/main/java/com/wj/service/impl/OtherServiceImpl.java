package com.wj.service.impl;

import com.alibaba.dubbo.config.annotation.Service;

@Service
public class OtherServiceImpl implements OtherService{


    public String test(String name) {
        System.out.println("otherService test " + name);
        return "other";
    }
}
