package com.wangzhixiong.dao;

import com.wangzhixiong.pojo.Users;

import java.util.List;

public interface UserManagerDao
{
    public void insertUser(Users users);
    List<Users> selectUserByProperty(Users users);
}
