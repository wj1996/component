package com.wj;

import com.wj.mapper.TbUserMapper;
import com.wj.mapper2.TbUserMapper2;
import com.wj.model.User;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

/**
 * mybatis入门测试
 */
public class Demo01 {


    @Test
    public void test() throws IOException {
        InputStream resourceAsStream = Resources.getResourceAsStream("mybatis-config.xml");
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(resourceAsStream);
        SqlSession sqlSession = sqlSessionFactory.openSession();

        TbUserMapper mapper = sqlSession.getMapper(TbUserMapper.class);

        List<User> users = mapper.selectByPrimaryKey(1);
        System.out.println(users);

    }


    @Test
    public void test2() throws IOException {
        InputStream resourceAsStream = Resources.getResourceAsStream("mybatis-config.xml");
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(resourceAsStream);
        SqlSession sqlSession = sqlSessionFactory.openSession();

        TbUserMapper2 mapper = sqlSession.getMapper(TbUserMapper2.class);

        List<User> users = mapper.selectByPrimaryKey(1);
        System.out.println(users);
    }

}
