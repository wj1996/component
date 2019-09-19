package com.wj.spring.annotation.config;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.beans.PropertyVetoException;


/**
 * InfrastructureAdvisorAutoProxyCreator
 *  1.注册
 *  2.利用后置处理器机制在创建以后，返回一个代理对象（增强），代理对象执行方法时，利用拦截器进行调用
 *
 *  AnnotationTransactionAttributeSource  事务管理器要用事务注解的信息，使用这个类解析事务注解
 *  TransactionInterceptor 保存事务属性信息，事务管理器 MethodInterceptor
 *      invokeWithinTransaction(invocation.getMethod(), targetClass, invocation::proceed); 》》》》》TransactionAspectSupport
 *
 *  当执行目标方法时：
 *      执行拦截器链
 *      事务拦截器：
 *      1.先获取事务相关属性
 *          TransactionAttributeSource tas = getTransactionAttributeSource();
 *      2.获取PlatformTransactionManager事务管理器，直接到容器中获取实例
 *          final PlatformTransactionManager tm = determineTransactionManager(txAttr);
 *      执行目标方法
 *          retVal = invocation.proceedWithInvocation();
 *      如果异常，利用事务管理器进行回滚操作
 *          completeTransactionAfterThrowing(txInfo, ex);
 *      如果正常，利用事务管理器进行提交
 *          commitTransactionAfterReturning(txInfo);
 *  事务管理器：
 *
 *
 */


@Configuration
@ComponentScan("com.wj.spring.annotation.transaction")
@EnableTransactionManagement    //注意要用事务 必须开启事务管理功能
public class MyConfig8 {

    //创建数据源
    @Bean
    public DataSource dataSource() throws PropertyVetoException {
        ComboPooledDataSource dataSource = new ComboPooledDataSource();
        dataSource.setUser("root");
        dataSource.setPassword("root");
        dataSource.setDriverClass("com.mysql.jdbc.Driver");
        dataSource.setJdbcUrl("jdbc:mysql://localhost:3307/spring");
        return dataSource;
    }

    //jdbcTemplate

    @Bean
    public JdbcTemplate jdbcTemplate() throws PropertyVetoException {
        return new JdbcTemplate(dataSource());
    }

    //事务管理器
    @Bean
    public PlatformTransactionManager platformTransactionManager() throws PropertyVetoException {
        return new DataSourceTransactionManager(dataSource());
    }


}
