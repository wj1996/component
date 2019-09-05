package com.wj.test;

import com.wj.App2;
import com.wj.App4;
import com.wj.dao.UserMapper;
import com.wj.model.Orders;
import com.wj.model.User;
import com.wj.model.Users;
import com.wj.service.IOrderService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

/**
 * SpringBoot使用单元测试
 */
@SpringBootTest(classes = App4.class)
@RunWith(SpringRunner.class)  //建议使用这个
public class UserTest {


    @Resource
    public UserMapper userMapper;


    @Resource
    private IOrderService orderService;

    @Test
    public void add() {
        User user = new User();
        user.setId(1);
        user.setUsername("test");
        user.setPasswd("123");
        userMapper.insert(user);
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
