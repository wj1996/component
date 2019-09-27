package com.wj;

import com.wj.mapper.TUserMapper;
import com.wj.mapper.TbUserMapper;
import com.wj.mapper2.TbUserMapper2;
import com.wj.model.TUser;
import com.wj.model.User;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * mybatis入门测试
 */
public class Demo01 {


    private SqlSession sqlSession = null;
    private SqlSessionFactory sqlSessionFactory = null;


    @Before
    public void before() throws Exception{
        InputStream resourceAsStream = Resources.getResourceAsStream("mybatis-config.xml");
        sqlSessionFactory = new SqlSessionFactoryBuilder().build(resourceAsStream);
        sqlSession = sqlSessionFactory.openSession();
    }


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


    /**
     * 关联查询
     */

    @Test
    public void test4() throws Exception {
        InputStream resourceAsStream = Resources.getResourceAsStream("mybatis-config.xml");
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(resourceAsStream);
        SqlSession sqlSession = sqlSessionFactory.openSession();
        TUserMapper tUserMapper = sqlSession.getMapper(TUserMapper.class);
        List<TUser> tUsers = tUserMapper.selectUserPosition1();
        for (TUser tUser : tUsers) System.out.println(tUser.getSex());
    }

    /**
     * 关联查询第二种
     */
    @Test
    public void test5() throws Exception {
        TUserMapper tUserMapper = sqlSession.getMapper(TUserMapper.class);
        List<TUser> tUsers = tUserMapper.selectUserPosition2();
        for (TUser tUser : tUsers) System.out.println(tUser.getSex());
    }

    /**
     * 多表关联
     * @throws Exception
     */
    @Test
    public void test6() throws Exception {
        TUserMapper tUserMapper = sqlSession.getMapper(TUserMapper.class);
        List<TUser> tUsers = tUserMapper.selectUserRole();
        for (TUser tUser : tUsers) System.out.println(tUser);
    }

    @Test
    public void test7() {
        TUserMapper tUserMapper = sqlSession.getMapper(TUserMapper.class);
        List<TUser> tUsers = tUserMapper.selectUserHealthReport();
        for (TUser tUser : tUsers) System.out.println(tUser);
    }

    /**
     * 测试一级缓存
     */
    @Test
    public void test8() {
        TUserMapper tUserMapper = sqlSession.getMapper(TUserMapper.class);
        TUser tUser = tUserMapper.selectByPrimaryKey(1);
        System.out.println(tUser);
        TUser tUser2 = tUserMapper.selectByPrimaryKey(1);
        System.out.println(tUser2);

        Byte sex = 1;

        List<TUser> user = tUserMapper.selectByEmailAndSex2("qq.com",sex);


        Map map = new HashMap();
        map.put("email","qq.com");
        map.put("sex",sex);
        //这样写 会走库
        List<TUser> list = tUserMapper.selectByEmailAndSex1(map);

        sqlSession.close();


        SqlSession sqlSession = sqlSessionFactory.openSession();

        TUserMapper mapper = sqlSession.getMapper(TUserMapper.class);
        //走库
        TUser tUser3 = mapper.selectByPrimaryKey(1);

    }

    /**
     * 二级缓存
     */
    @Test
    public void test9() {
        SqlSession session1 = sqlSessionFactory.openSession();
        TUserMapper userMapper1 = session1.getMapper(TUserMapper.class);
        String email = "qq.com";
        Byte sex = 1;
        List<TUser> list1 = userMapper1.selectByEmailAndSex2(email, sex);
        System.out.println(list1.size());


//		TUser userInsert = new TUser();
//		userInsert.setUserName("test1");
//		userInsert.setRealName("realname1");
//		userInsert.setEmail("myemail1");
//		userMapper1.insert1(userInsert);

        List<TUser> list2 = userMapper1.selectByEmailAndSex2(email, sex);
        System.out.println(list2.toString());
        session1.close();


        SqlSession session2 = sqlSessionFactory.openSession();
        TUserMapper userMapper2 = session2.getMapper(TUserMapper.class);
        List<TUser> list3 = userMapper2.selectByEmailAndSex2(email, sex);
        System.out.println(list3.toString());
        session2.close();

        /*SqlSession session3 = sqlSessionFactory.openSession();
        TJobHistoryMapper userMapper3 = session3.getMapper(TJobHistoryMapper.class);
        List<TUser> list4 = userMapper3.selectByEmailAndSex2(email, sex);
        System.out.println(list4.toString());
        session3.close();*/
    }

}
