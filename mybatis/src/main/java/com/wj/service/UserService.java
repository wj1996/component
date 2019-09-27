package com.wj.service;

import com.wj.mapper.TUserMapper;
import com.wj.model.TUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {


    @Autowired
    private TUserMapper userMapper;


    public TUser getUser(Integer id) {
        return userMapper.selectByPrimaryKey(id);
    }

}
