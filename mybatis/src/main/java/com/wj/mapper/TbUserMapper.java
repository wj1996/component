package com.wj.mapper;

import com.wj.model.User;

import java.util.List;

public interface TbUserMapper {


    List<User> selectByPrimaryKey(Integer id);


    int batchInsert(List<User> list);



}
