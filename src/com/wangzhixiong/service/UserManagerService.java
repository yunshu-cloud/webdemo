package com.wangzhixiong.service;

import com.wangzhixiong.pojo.Users;

import java.util.List;

public interface UserManagerService
{
    void addUser(Users users);
    List<Users> findUsers(Users users);
}
