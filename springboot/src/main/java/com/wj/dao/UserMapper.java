package com.wj.dao;

import com.wj.model.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

//@Mapper
/**
 * 如果使用@Mapper的方式，有一个就需要写一个，比较麻烦，实际使用过程中，可用可不用，建议使用再启动类上使用@MapperScan注解 见APP2
 */
public interface UserMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(User record);

    int insertSelective(User record);

    User selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);

    User findByUsernameAndPasswd(@Param("username") String username, @Param("passwd") String passwd);
}