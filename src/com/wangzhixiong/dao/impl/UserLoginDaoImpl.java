package com.wangzhixiong.dao.impl;

import com.wangzhixiong.commons.JdbcUtils;
import com.wangzhixiong.dao.UserLoginDao;
import com.wangzhixiong.pojo.Users;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserLoginDaoImpl implements UserLoginDao
{
    /**
     * 用户登录的数据查询
     * @param username
     * @param userpwd
     * @return
     */
    @Override
    public Users selectUsersByUserNameAndUserPwd(String username, String userpwd)
    {
        Users user = null;
        Connection con = null;
        Connection conn = JdbcUtils.getConnection();
        try
        {
            PreparedStatement ps = conn.prepareStatement("select * from users where username = ? and userpwd = ?");
            ps.setString(1,username);
            ps.setString(2,userpwd);
            ResultSet resultSet = ps.executeQuery();
            while(resultSet.next()){
                user = new Users();
                user.setUsersex(resultSet.getString("usersex"));
                user.setUserpwd(resultSet.getString("userpwd"));
                user.setUsername(resultSet.getString("username"));
                user.setPhonenumber(resultSet.getString("phonenumber"));
                user.setQqnumber(resultSet.getString("qqnumber"));
            }
        } catch (SQLException e)
        {
            throw new RuntimeException(e);
        }
        return user;


    }
}
