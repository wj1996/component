package com.wj.dao.impl;

import com.wj.utils.Constant;
import com.wj.utils.DBUtils;
import com.wj.dao.IBrandDao;
import com.wj.web.model.Brand;
import com.wj.web.model.PageBean;
import com.wj.web.model.PageRequest;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

@Repository
public class BrandDaoImpl implements IBrandDao {


    @Override
    public void save(Brand brand) throws Exception {
        if (null == brand) return;
        Connection connection = null;
        PreparedStatement ps = null;
        try {
            connection = DBUtils.getConn();
            ps = connection.prepareStatement("insert into brand(name,ctime) values(?,?)");
            ps.setString(1,brand.getName());
            ps.setDate(2,new java.sql.Date(brand.getCtime().getTime()));
            ps.execute();
        } finally {
            DBUtils.close(ps,connection);
        }

    }

    @Override
    public List<Brand> getBrandList(PageRequest pageRequest) throws Exception {
        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<Brand> brands = new ArrayList<>();
        int pageNum = pageRequest.getPageNum();
        int pageSize = pageRequest.getPageSize();
        try {
            connection = DBUtils.getConn();
            ps = connection.prepareStatement("select * from brand limit ?,?");
            ps.setInt(1,(pageNum - 1) * pageSize);
            ps.setInt(2,pageSize * pageNum);
            rs = ps.executeQuery();
            while (rs.next()) {
                brands.add(
                        Brand.builder()
                                .id(rs.getInt(1))
                                .name(rs.getString(2))
                                .ctime(rs.getDate(3))
                                .build()
                );
            }
        } finally {
            DBUtils.close(rs,ps,connection);
        }

        return brands;
    }

    @Override
    public int getTotalSize() throws Exception {
        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        int count = 0;
        try {
            connection = DBUtils.getConn();
            ps = connection.prepareStatement("select count(1) from brand");
            rs = ps.executeQuery();
            if (rs.next()) {
                count = rs.getInt(1);
            }
        } finally {
            DBUtils.close(rs,ps,connection);
        }
        return count;
    }
}
