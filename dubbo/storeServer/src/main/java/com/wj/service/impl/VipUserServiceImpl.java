package com.wj.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.wj.service.VipUserService;

@Service
public class VipUserServiceImpl implements VipUserService {

    public String getVipDetail(String id) {
        System.out.println(super.getClass().getName()+"被调用一次："+System.currentTimeMillis());
        return "13221";
    }
}
