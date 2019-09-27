package com.wj;

import com.wj.model.TUser;
import com.wj.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * mybatis集成spring使用
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:application.xml")
public class Demo02 {


    @Autowired
    private UserService userService;


    @Test
    public void test1() {
        TUser user = userService.getUser(1);
        System.out.println(user);
    }



}
