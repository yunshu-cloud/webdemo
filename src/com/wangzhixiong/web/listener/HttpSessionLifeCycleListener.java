package com.wangzhixiong.web.listener;

import com.wangzhixiong.commons.Constants;
import com.wangzhixiong.pojo.Users;

import javax.servlet.ServletContext;
import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

/**
 * 解决HttpSession被反复销毁的问题
 */
@WebListener
public class HttpSessionLifeCycleListener implements HttpSessionListener
{

    @Override
    public void sessionCreated(HttpSessionEvent httpSessionEvent)
    {

    }

    @Override
    public void sessionDestroyed(HttpSessionEvent httpSessionEvent)
    {
        HttpSession session = httpSessionEvent.getSession();
        ServletContext servletContext = session.getServletContext();
        Users users = (Users) session.getAttribute(Constants.USER_SESSION_KEY);
        servletContext.removeAttribute(users.getUserid()+"");

    }
}
