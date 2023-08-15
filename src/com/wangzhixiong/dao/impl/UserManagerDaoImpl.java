package com.wangzhixiong.dao.impl;

import com.wangzhixiong.commons.JdbcUtils;
import com.wangzhixiong.dao.UserManagerDao;
import com.wangzhixiong.pojo.Users;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

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

    /**
     * 查询用户
     * @param users
     * @return
     */
    @Override
    public List<Users> selectUserByProperty(Users users)
    {
        Connection conn = null;
        List<Users> list = new ArrayList<>();
        try{
            conn = JdbcUtils.getConnection();
            String sql = this.createSQL(users);
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet resultSet = ps.executeQuery();
            while(resultSet.next()){
                Users user = new Users();
                user.setUserid(resultSet.getInt("userid"));
                user.setPhonenumber(resultSet.getString("phonenumber"));
                user.setUsersex(resultSet.getString("usersex"));
                user.setQqnumber(resultSet.getString("qqnumber"));
                user.setUserpwd(resultSet.getString("userpwd"));
                user.setUsername(resultSet.getString("username"));
                list.add(user);
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally
        {
            JdbcUtils.closeConnection(conn);
        }
        return list;
    }

    // 拼接查询的SQL语句
    private String createSQL(Users users)
    {
        StringBuffer stringBuffer = new StringBuffer("select * from users where 1=1");
        if(users.getUsersex() != null && users.getUsersex().length() > 0){
            stringBuffer.append(" and usersex = "+users.getUsersex());
        }
        if(users.getQqnumber() != null && users.getQqnumber().length() > 0){
            stringBuffer.append(" and qqnumber = "+users.getQqnumber());
        }
        if(users.getUsername() != null && users.getUsername().length() > 0){
            stringBuffer.append(" and username = '"+users.getUsername()+"'");
        }
        if(users.getPhonenumber() != null && users.getPhonenumber().length() > 0){
            stringBuffer.append(" and phonenumber = "+users.getPhonenumber());
        }
        return stringBuffer.toString();
    }
}
