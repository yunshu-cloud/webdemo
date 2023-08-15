package com.wangzhixiong.dao;

import com.wangzhixiong.pojo.Users;

import java.util.List;

public interface UserManagerDao
{
    void insertUser(Users users);
    List<Users> selectUserByProperty(Users users);
    Users selectUserByUserId(int userid);
    void updateUserByUserId(Users users);
    void deleteUserByUserId(int userid);

}
