package com.wj.spring.annotation.transaction;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class OrderDao {


    @Autowired
    private JdbcTemplate jdbcTemplate;


    public void insert() {
        String sql = "insert into myorder(id,name) values(?,?)";
        jdbcTemplate.update(sql,"2","test2");
    }

}
