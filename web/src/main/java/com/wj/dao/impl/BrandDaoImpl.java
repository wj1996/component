package com.wj.dao.impl;

import com.wj.utils.DBUtils;
import com.wj.dao.IBrandDao;
import com.wj.web.model.Brand;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;

@Repository
public class BrandDaoImpl implements IBrandDao {


    @Override
    public void save(Brand brand) throws Exception {
        if (null == brand) return;
        Connection connection = DBUtils.getConn();
        PreparedStatement ps = connection.prepareStatement("insert into brand(name,ctime) values(?,?)");
        ps.setString(1,brand.getName());
        ps.setDate(2,new java.sql.Date(brand.getCtime().getTime()));
        ps.execute();
    }
}
