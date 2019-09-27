package com.wj.mapper;

import com.wj.model.TUser;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface TUserMapper {


    /*
    * 一对一关联查询
    * */
    List<TUser> selectUserPosition1();

    List<TUser> selectUserPosition2();


    List<TUser> selectUserRole();


    List<TUser> selectUserHealthReport();


    TUser selectByPrimaryKey(Integer id);

    List<TUser> selectByEmailAndSex1(Map<String, Object> param);

    List<TUser> selectByEmailAndSex2(@Param("email")String email, @Param("sex")Byte sex);
}