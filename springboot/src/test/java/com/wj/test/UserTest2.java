package com.wj.test;

import com.wj.App4;
import com.wj.dao.orders.OrdersMapper;
import com.wj.model.Orders;
import com.wj.model.Users;
import com.wj.service.IOrderService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

@SpringBootTest(classes = {App4.class})
@RunWith(SpringRunner.class)  //建议使用这个
public class UserTest2 {

    @Resource
    private IOrderService orderService;

    @Resource
    private OrdersMapper ordersMapper;

    @Test
    public void testAddOrder() {
        Orders orders = new Orders();
        orders.setName("test1");
        orders.setAccount(100);
        orders.setId(101);
        ordersMapper.insert(orders);
    }

    @Test
    public void test() {
        Users user = new Users();
        user.setId(1);
        user.setUsername("test");
        user.setPasswd("123");
        Orders orders = new Orders();
        orders.setAccount(123);
        orders.setId(12);
        orders.setName("test");

        orderService.addOrder(orders,user);
    }
}
