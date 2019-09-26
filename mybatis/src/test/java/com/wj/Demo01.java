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
import java.util.Arrays;
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

    /**
     * 测试批量插入
     * foreach标签
     */
    @Test
    public void test3() throws IOException {
        InputStream resourceAsStream = Resources.getResourceAsStream("mybatis-config.xml");
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(resourceAsStream);
        SqlSession sqlSession = sqlSessionFactory.openSession(true);
        TbUserMapper mapper = sqlSession.getMapper(TbUserMapper.class);

        User user = new User();
        user.setPasswd("123");
        user.setUsername("test3");
        User user2 = new User();
        user2.setPasswd("1234");
        user2.setUsername("test4");

        int i = mapper.batchInsert(Arrays.asList(user,user2));

//        sqlSession.commit();
        System.out.println(i);

    }

}
