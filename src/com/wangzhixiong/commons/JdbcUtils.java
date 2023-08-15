package com.wangzhixiong.commons;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class JdbcUtils
{
    private static String driver;
    private static String url;
    private static String username;
    private static String password;

    static{
        ResourceBundle bundle = ResourceBundle.getBundle("db");
        driver = bundle.getString("jdbc.driver");
        url = bundle.getString("jdbc.url");
        username = bundle.getString("jdbc.username");
        password = bundle.getString("jdbc.password");
        try
        {
            // 加载数据库驱动 应用程序代码和数据库之间连接的标准成为驱动driver
            Class.forName(driver);
        } catch (ClassNotFoundException e)
        {
            throw new RuntimeException(e);
        }
    }

    // 获取链接方法
    public static Connection getConnection(){
        Connection conn = null;
        try
        {
            conn = DriverManager.getConnection(url,username,password);
        } catch (SQLException e)
        {
            throw new RuntimeException(e);
        }
        return conn;
    }

    // 关闭连接
    public static void closeConnection(Connection conn){
        try
        {
            conn.close();
        } catch (SQLException e)
        {
            throw new RuntimeException(e);
        }
    }

    // 事务回滚
    public static void rollbackConnection(Connection conn)
    {
        try
        {
            conn.rollback();
        } catch (SQLException e)
        {
            throw new RuntimeException(e);
        }
    }
}
