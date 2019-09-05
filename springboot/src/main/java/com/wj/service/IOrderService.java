package com.wj.service;


import com.wj.model.Orders;
import com.wj.model.Users;

public interface IOrderService {
     void addOrder(Orders orders, Users users);
}