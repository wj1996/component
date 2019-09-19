package com.wj.spring.annotation.transaction;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class OrderService {

    @Autowired
    private OrderDao orderDao;

    @Transactional
    public void insert() {
        orderDao.insert();
        int a = 1 / 0;
    }
}
