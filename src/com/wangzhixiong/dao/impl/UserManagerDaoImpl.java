package com.wangzhixiong.dao.impl;

import com.wangzhixiong.commons.JdbcUtils;
import com.wangzhixiong.dao.UserManagerDao;
import com.wangzhixiong.pojo.Users;

import java.sql.Connection;
import java.sql.PreparedStatement;

/**
 * 用户管理持久层
 */
public class UserManagerDaoImpl implements UserManagerDao
{
    @Override
    public void insertUser(Users users)
    {
        Connection conn = null;
        try{
            conn = JdbcUtils.getConnection();
            conn.setAutoCommit(false);
            PreparedStatement ps = conn.prepareStatement("insert into users values (default,?,?,?,?,?)");
            ps.setString(1,users.getUsername());
            ps.setString(2,users.getUserpwd());
            ps.setString(3,users.getUsersex());
            ps.setString(4,users.getPhonenumber());
            ps.setString(5,users.getQqnumber());
            ps.execute();
            conn.commit();
        }catch (Exception e){
            e.printStackTrace();
            JdbcUtils.rollbackConnection(conn);
        }finally
        {
            JdbcUtils.closeConnection(conn);
        }
    }
}
