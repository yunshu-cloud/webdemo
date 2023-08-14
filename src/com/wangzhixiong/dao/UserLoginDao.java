package com.wangzhixiong.dao;

import com.wangzhixiong.pojo.Users;

public interface UserLoginDao
{
    public Users selectUsersByUserNameAndUserPwd(String username,String userpwd);
}
