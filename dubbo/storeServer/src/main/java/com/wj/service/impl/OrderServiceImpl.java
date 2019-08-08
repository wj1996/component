package com.wj.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.wj.dao.OrderDao;
import com.wj.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderDao orderDao;

    public String getDetail(String id) {
        System.out.println(super.getClass().getName()+"被调用一次："+System.currentTimeMillis());
        return orderDao.getDetail(id);
    }
}
