package com.wj;

import com.wj.model.TUser;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;

import java.io.InputStream;

/**
 * 源码分析
 */
public class Demo04 {


    @Test
    public void quickStart() throws Exception {
        InputStream resourceAsStream = Resources.getResourceAsStream("mybatis-config.xml");
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(resourceAsStream);
        SqlSession sqlSession = sqlSessionFactory.openSession();
        //执行查询语句
        TUser user = sqlSession.selectOne("com.wj.mapper.TUserMapper.selectByPrimaryKey", 1);
        System.out.println(user);



    }

    /**
     * 源码解析
     * @throws Exception
     *
     */
    @Test
    public void test2() throws Exception {
        InputStream resourceAsStream = Resources.getResourceAsStream("mybatis-config.xml");
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(resourceAsStream);
        System.out.println(1);
    }
}
