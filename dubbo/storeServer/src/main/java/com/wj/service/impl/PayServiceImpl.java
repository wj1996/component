package com.wj.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.wj.service.PayService;
@Service
public class PayServiceImpl implements PayService {


    public String cancelPay(int orderId) {
        System.out.println("取消支付");
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        return "100";
    }
}
