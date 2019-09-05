package com.wj.service.impl;


import com.wj.dao.orders.OrdersMapper;
import com.wj.dao.users.UsersMapper;
import com.wj.model.Orders;
import com.wj.model.Users;
import com.wj.service.IOrderService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

@Service
public class OrderServiceImpl  implements IOrderService {

    @Resource
    private UsersMapper usersMapper;

    @Resource
    private OrdersMapper ordersMapper;

    @Override
//    @Transactional
    public void addOrder(Orders orders, Users users) {
//        usersMapper.insertSelective(users);
//        int i = 10/0;
        ordersMapper.insertSelective(orders);
    }
}