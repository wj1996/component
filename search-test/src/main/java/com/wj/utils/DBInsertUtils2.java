package com.wj.utils;

import com.wj.dao.GoodsInfoDao;
import com.wj.model.GoodsInfo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 往mysql数据库中插入大量数据
 */
public class DBInsertUtils2 {




    private static int length = BaseConstant.names.length;
    private static String[] strs = BaseConstant.name.split(" ");
    private static int strsLength = strs.length;

    private static List<GoodsInfo> list = Collections.synchronizedList(new ArrayList<>());

    /*static {
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }*/

    public static void main(String[] args) throws InterruptedException {
//        try {
//            insertTestData();
//        System.setProperty("https.proxyHost", "localhost");
//        System.setProperty("https.proxyPort", "8888");
//        } catch (SQLException throwables) {
//            throwables.printStackTrace();
//        }
        long start = System.currentTimeMillis();
        ExecutorService executorService = Executors.newFixedThreadPool(8);
        int num = 500000;
        CountDownLatch countDownLatch = new CountDownLatch(num);
        for (int i = 0 ; i < num ; i++) {
            executorService.submit(() -> {
                list.add(productGoodsInfo());
                countDownLatch.countDown();
            });
        }
//        System.out.println(list);
        countDownLatch.await();
        GoodsInfoDao goodsInfoDao = new GoodsInfoDao();
        try {
            goodsInfoDao.batchInsertData(list);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        System.out.println("耗时为：" + (System.currentTimeMillis() - start) + "ms");

    }

    private static void insertTestData() throws SQLException {
//        Connection conn = getConn();
//        productGoodsInfo();

      /*  for (int i = 0 ;i < 10 ; i++) {
            System.out.println(getRandomNumber(5));
        }*/
//        System.out.println("test...............");
    }

    public static Connection getConn() throws SQLException {
        return DriverManager.getConnection("jdbc:mysql://localhost:3307/my?useSSL=false","root","root");
    }
    private static GoodsInfo productGoodsInfo() {
        StringBuilder sb = new StringBuilder();
        sb.append(System.currentTimeMillis()).append(getRandomNumber(2)).append(getRandomNumber(3));
        return GoodsInfo.builder()
                .id(Long.valueOf(sb.toString()))
                .goodsId(sb.toString())
                .goodsName(getName())
                .goodsPrice(new Random().nextInt(10000))
                .goodsImgUrl(BaseConstant.imgUrls[new Random().nextInt(9)])
                .build();
    }

    private static String getName() {
        StringBuilder sb = new StringBuilder();
        Random random = new Random();
        for (int i = 0 ; i < 3 ; i++) {
            sb.append(BaseConstant.names[random.nextInt(length)]);
        }
        for (int i = 0 ; i < 3 ; i++) {
            sb.append(strs[random.nextInt(strsLength)]);
        }
        return sb.toString();
    }

    private static long getRandomNumber(int length) {
        if (length < 0) return 0;
        StringBuilder sb = new StringBuilder("1");
        for (int i = 0 ; i < length-1 ; i++) {
            sb.append("0");
        }
        long limit = Long.valueOf(sb.toString());
        long number = (long)((Math.random() * 9 + 1) * limit);
        return number;
    }



}
