package com.wj.rwseparation.config;

import com.wj.rwseparation.dbutils.MyRoutingDataSource;
import com.wj.rwseparation.vo.DBTypeEnum;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

import static org.springframework.boot.jdbc.DataSourceBuilder.create;

/**
 * 类说明：数据源的相关配置
 */
@Configuration
public class DataSourceConfig {
    /*主库的数据源*/
    @Bean
    @ConfigurationProperties("spring.datasource.master")
    public DataSource masterDataSource() {
        return create().build();
    }

    /*从库1的数据源*/
    @Bean
    @ConfigurationProperties("spring.datasource.slave1")
    public DataSource slave1DataSource() {
        return create().build();
    }

    /*自定义数据源，内部持有了主库和从库的数据源，
通过某种机制让应用程序在进行数据读写时，按业务情况走主库或者从库*/
    @Bean
    public DataSource myRoutingDataSource(
            @Qualifier("masterDataSource")DataSource masterDataSource,
            @Qualifier("slave1DataSource")DataSource slaveDataSource){
        Map<Object, Object> targetDataSources = new HashMap<>();
        targetDataSources.put(DBTypeEnum.MASTER,masterDataSource);
        targetDataSources.put(DBTypeEnum.SLAVE,slaveDataSource);
        MyRoutingDataSource myRoutingDataSource = new MyRoutingDataSource();
        myRoutingDataSource.setTargetDataSources(targetDataSources);
        /*当执行的方法没有被Aop拦截时，缺省使用的数据源*/
        myRoutingDataSource.setDefaultTargetDataSource(masterDataSource);
        return myRoutingDataSource;
    }


}
