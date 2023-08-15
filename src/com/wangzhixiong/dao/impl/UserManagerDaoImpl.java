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

    /**
     * 根据用户id查询用户
     * @param userid
     * @return
     */
    @Override
    public Users selectUserByUserId(int userid)
    {
        Connection conn = null;
        Users user = null;
        try{
            conn = JdbcUtils.getConnection();
            PreparedStatement ps = conn.prepareStatement("select * from users where userid = ?");
            ps.setInt(1,userid);
            ResultSet resultSet = ps.executeQuery();
            while(resultSet.next()){
                user = new Users();
                user.setUserid(resultSet.getInt("userid"));
                user.setPhonenumber(resultSet.getString("phonenumber"));
                user.setUsersex(resultSet.getString("usersex"));
                user.setQqnumber(resultSet.getString("qqnumber"));
                user.setUserpwd(resultSet.getString("userpwd"));
                user.setUsername(resultSet.getString("username"));
            }
        }catch (Exception e){
            e.printStackTrace();
            JdbcUtils.rollbackConnection(conn);
        }finally
        {
            JdbcUtils.closeConnection(conn);
        }
        return user;
    }

    /**
     * 更新用户
     * @param users
     */
    @Override
    public void updateUserByUserId(Users users)
    {
        Connection conn = null;
        try{
            conn = JdbcUtils.getConnection();
            conn.setAutoCommit(false);
            PreparedStatement ps = conn.prepareStatement("update users set username=?,usersex = ?,phonenumber = ?,qqnumber = ? where userid = ?");
            ps.setString(1,users.getUsername());
            ps.setString(2,users.getUsersex());
            ps.setString(3,users.getPhonenumber());
            ps.setString(4,users.getQqnumber());
            ps.setInt(5,users.getUserid());
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

    @Override
    public void deleteUserByUserId(int userid)
    {
        Connection conn = null;
        try{
            conn = JdbcUtils.getConnection();
            conn.setAutoCommit(false);
            PreparedStatement ps = conn.prepareStatement("delete from users where userid = ?");
            ps.setInt(1,userid);
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
