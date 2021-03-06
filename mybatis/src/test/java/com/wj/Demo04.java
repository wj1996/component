package com.wj;

import com.wj.mapper.TUserMapper;
import com.wj.mapper.TbUserMapper;
import com.wj.model.TUser;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;
import sun.misc.ProxyGenerator;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.*;

/**
 * 源码分析
 */
public class Demo04 {


    /**
     * 快速入门本质分析
     * @throws Exception
     */
    @Test
    public void quickStart() throws Exception {
        InputStream resourceAsStream = Resources.getResourceAsStream("mybatis-config.xml");
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(resourceAsStream);
        SqlSession sqlSession = sqlSessionFactory.openSession();
        /**
         * 执行查询语句   命名空间+id   ibatis之前的写法
         * 这样的写法 存在 强耦合的问题
         *
         * 分析（追本朔源）：
         *  找到session中对应的方法执行
         *  找到命名空间和方法名
         *  传递参数
         *
         */

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
        SqlSession sqlSession = sqlSessionFactory.openSession();
        TbUserMapper tUserMapper = sqlSession.getMapper(TbUserMapper.class);
//        generateClassFile(TbUserMapper.class,tUserMapper.getClass().getSimpleName());
        tUserMapper.selectByPrimaryKey(1);
        System.out.println(1);
    }


    public static void generateClassFile(Class clazz,String proxyName) {
        byte[] proxyClassFile= ProxyGenerator.generateProxyClass(proxyName,new Class[]{clazz});
        String paths = clazz.getResource(".").getPath();
        System.out.println(paths);
        FileOutputStream fos = null;
        try {
            fos = new FileOutputStream("D:/test/" + proxyName + ".class");
            fos.write(proxyClassFile);
            fos.flush();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (null != fos) {
                try {
                    fos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }


    @Test
    public void jdbcDemo() throws ClassNotFoundException, SQLException {
        //加载数据库驱动
        Class.forName("com.mysql.jdbc.Driver");
        //创建数据库连接
        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3307/spring", "root", "root");
        //创建PrepareStatment
        Statement stmt;
        stmt = connection.prepareStatement("select * from user where id = ?");
        //设置参数(占位符处理)
        ((PreparedStatement) stmt).setInt(1,1);
        //执行查询
        ResultSet resultSet = ((PreparedStatement) stmt).executeQuery();
        //结果处理
        while (resultSet.next()) {
            System.out.println(resultSet.getInt(1) + "," + resultSet.getString(2) + "," + resultSet.getString(3));
        }
    }
}
