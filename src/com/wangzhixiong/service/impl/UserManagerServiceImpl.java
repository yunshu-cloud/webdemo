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

    /**
     * 更新用户的预查询
     * @param userid
     * @return
     */
    @Override
    public Users findUserByUserId(int userid)
    {
        UserManagerDao userManagerDao = new UserManagerDaoImpl();
        return userManagerDao.selectUserByUserId(userid);
    }

    /**
     * 修改用户
     * @param users
     */
    @Override
    public void modifyUser(Users users)
    {
        UserManagerDao userManagerDao = new UserManagerDaoImpl();
        userManagerDao.updateUserByUserId(users);
    }
}
