package com.wj.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.alibaba.dubbo.rpc.RpcContext;
import com.wj.dao.OrderDao;
import com.wj.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;

@Service(filter = {"paramFilter"})
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderDao orderDao;

    public String getDetail(String id) {
        System.out.println(super.getClass().getName()+"被调用一次："+System.currentTimeMillis());
        return orderDao.getDetail(id);
    }

    public void submit(double money) {
        if ("1.0".equals(String.valueOf(money))) {
            System.out.println("警告：订单重复提交");
            throw new RuntimeException("订单重复提交");
        }
        System.out.println("提交订单：" + money);

//        return "1";
    }
}
