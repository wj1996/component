//package com.wj.service.impl;
//
//
//import com.wj.dao.UserMapper;
//import com.wj.model.User;
//import com.wj.service.IUserService;
//import org.springframework.stereotype.Service;
//import org.springframework.transaction.annotation.Transactional;
//
//import javax.annotation.Resource;
//
//@Service
//public class UserServiceImpl implements IUserService {
//
//    @Resource
//    private UserMapper userMapper;
//
//    @Override
//    public boolean login(String username, String passwd) {
//        User users = userMapper.findByUsernameAndPasswd(username, passwd);
//        return users != null;
//    }
//
//    @Override
//    public boolean register(String username, String passwd) {
//        User users = new User();
//        users.setUsername(username);
//        users.setPasswd(passwd);
//        int cnt = userMapper.insertSelective(users);
//        return cnt > 0;
//    }
//
//    @Override
//    @Transactional  //事务控制
//    public void batchAdd(String username, String passwd) {
//        User users = new User();
//        users.setUsername(username);
//        users.setPasswd(passwd);
//        userMapper.insertSelective(users);
//        int i = 10 /0;
//        users = new User();
//        users.setUsername(username+"2");
//        users.setPasswd(passwd);
//        userMapper.insertSelective(users);
//    }
//}