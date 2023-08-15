package com.wangzhixiong.service.impl;

import com.wangzhixiong.dao.UserManagerDao;
import com.wangzhixiong.dao.impl.UserManagerDaoImpl;
import com.wangzhixiong.pojo.Users;
import com.wangzhixiong.service.UserManagerService;

import java.util.List;

/**
 * 用户管理业务层
 */
public class UserManagerServiceImpl implements UserManagerService
{
    /**
     * 添加用户
     * @param users
     */
    @Override
    public void addUser(Users users)
    {
        UserManagerDao userManagerDao = new UserManagerDaoImpl();
        userManagerDao.insertUser(users);
    }

    /**
     * 查询用户
     * @param users
     * @return
     */
    @Override
    public List<Users> findUsers(Users users)
    {
        UserManagerDao userManagerDao = new UserManagerDaoImpl();
        return userManagerDao.selectUserByProperty(users);
    }
}
