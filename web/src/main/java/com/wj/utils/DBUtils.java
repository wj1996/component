package com.wj.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBUtils {

    static {
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
    public static Connection getConn() throws SQLException {
        return DriverManager.getConnection("jdbc:mysql://localhost:3307/my","root","root");
    }
}
