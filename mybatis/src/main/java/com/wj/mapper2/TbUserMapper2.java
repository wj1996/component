package com.wj.mapper2;

import com.wj.model.User;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.ResultType;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface TbUserMapper2 {


    @ResultType(User.class)
    @Select("select id,username,passwd from user where id = #{id}")
    List<User> selectByPrimaryKey(Integer id);



}
