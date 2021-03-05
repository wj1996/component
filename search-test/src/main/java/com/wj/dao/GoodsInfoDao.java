package com.wj.dao;

import com.wj.model.GoodsInfo;
import com.wj.utils.DBInsertUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class GoodsInfoDao {

    int factor = 10;


    public void batchInsertData(List<GoodsInfo> goodsInfoList) throws SQLException {
        if (null == goodsInfoList || goodsInfoList.size() < 1) return;
        Connection conn = null;
        PreparedStatement ps = null;
        try {
            conn = DBInsertUtils.getConn();
            conn.setAutoCommit(false);
            ps = conn.prepareStatement("insert into goods(goods_id,goods_name,goods_img_url,goods_price) values(?,?,?,?)");
            for (int i = 0 ; i < goodsInfoList.size() ; i++) {
                GoodsInfo goodsInfo = goodsInfoList.get(i);
                ps.setString(1,goodsInfo.getGoodsId());
                ps.setString(2,goodsInfo.getGoodsName());
                ps.setString(3,goodsInfo.getGoodsImgUrl());
                ps.setDouble(4,goodsInfo.getGoodsPrice());
                ps.addBatch();
                if (goodsInfoList.size() % 30000 == 0) {
                   ps.executeBatch();
                   continue;
                }

                if (i == goodsInfoList.size() - 1) ps.executeBatch();
            }
            conn.commit();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            if (null != ps) ps.close();
            if (null != conn) conn.close();
        }
    }

    public List<GoodsInfo> getList(int curPage,int pageSize) {
        List<GoodsInfo> list = new ArrayList<>();
        Connection conn = null;
        PreparedStatement ps = null;
        try {
            conn = DBInsertUtils.getConn();
            ps = conn.prepareStatement("select * from goods limit ?,?");
            ps.setInt(1,curPage * pageSize);
            ps.setInt(2,pageSize);
            ResultSet resultSet = ps.executeQuery();
            while (resultSet.next()) {
                list.add(GoodsInfo.builder()
                        .id(resultSet.getInt("id"))
                        .goodsId(resultSet.getString("goods_id"))
                        .goodsImgUrl(resultSet.getString("goods_img_url"))
                        .goodsName(resultSet.getString("goods_name"))
                        .goodsPrice(resultSet.getDouble("goods_price"))
                        .build());
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return list;
    }

    public long getTotalPageSize(int pageSize) {
        Connection conn = null;
        PreparedStatement ps = null;
        long totalPageSize = 0;
        try {
            conn = DBInsertUtils.getConn();
            ps = conn.prepareStatement("select count(1) from goods");
            ResultSet resultSet = ps.executeQuery();
            resultSet.next();
            totalPageSize = (long) Math.ceil((double)resultSet.getLong(1) / pageSize);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return totalPageSize;
    }
}
