package com.wangzhixiong.service.impl;

import com.wangzhixiong.dao.UserLoginDao;
import com.wangzhixiong.dao.impl.UserLoginDaoImpl;
import com.wangzhixiong.exception.UserNotFoundException;
import com.wangzhixiong.pojo.Users;
import com.wangzhixiong.service.UserLoginService;

/**
 * 用户登录业务
 */
public class UserLoginServiceImpl implements UserLoginService
{

    /**
     * 用户登录
     * @param username
     * @param userpwd
     * @return
     */
    @Override
    public Users userLogin(String username, String userpwd)
    {
        UserLoginDao userLoginDao = new UserLoginDaoImpl();
        Users users = userLoginDao.selectUsersByUserNameAndUserPwd(username, userpwd);
        if(users == null){
            throw new UserNotFoundException("用户名或密码有误！");
        }
        return users;
    }
}
