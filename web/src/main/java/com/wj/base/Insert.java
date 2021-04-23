package com.wj.base;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Random;

public class Insert {


    private static final String str = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private static int num = 2;
    public static void main(String[] args) throws SQLException, ClassNotFoundException {
//        insert();
        insert2();
    }

    private static void insert() throws SQLException, ClassNotFoundException {
//        Class.forName("com.mysql.jdbc.Driver");
        Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/my","root","root");
        PreparedStatement ps = conn.prepareStatement("insert into tt values(?,?,?)");
        ps.setInt(1,new Random().nextInt(10000));
        ps.setString(2,null);
        ps.setString(3,getString(10000));
        ps.execute();

    }

    private static void insert2() throws SQLException, ClassNotFoundException {
//        Class.forName("com.mysql.jdbc.Driver");
        Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/my","root","root");
        for (int i = 0; i < 20000; i++) {
            PreparedStatement ps = conn.prepareStatement("insert into index_demo values(?,?,?)");
            ps.setInt(1,num);
            num += 2;
            ps.setInt(2,new Random().nextInt(10000));
            ps.setString(3,getString(1));
            ps.execute();
        }
    }


    private static String getString(int length) {
        Random random = new Random();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < length; i++) {
            sb.append(str.charAt(random.nextInt(str.length())));
        }
        return sb.toString();
    }
}
