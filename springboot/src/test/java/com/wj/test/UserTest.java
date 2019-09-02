package com.wj.test;

import com.wj.App2;
import com.wj.dao.UserMapper;
import com.wj.model.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

/**
 * SpringBoot使用单元测试
 */
@SpringBootTest(classes = App2.class)
@RunWith(SpringRunner.class)  //建议使用这个
public class UserTest {


    @Resource
    public UserMapper userMapper;

    @Test
    public void add() {
        User user = new User();
        user.setId(1);
        user.setUsername("test");
        user.setPasswd("123");
        userMapper.insert(user);
    }
 }
