package com.wangzhixiong.web.servlet;

import com.wangzhixiong.commons.Constants;
import com.wangzhixiong.exception.UserNotFoundException;
import com.wangzhixiong.pojo.Users;
import com.wangzhixiong.service.UserLoginService;
import com.wangzhixiong.service.impl.UserLoginServiceImpl;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * 处理用户登录请求
 */
@WebServlet("/login.do")
public class UserLoginServlet extends HttpServlet
{
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
    {
        this.doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
    {
        String username = req.getParameter("username");
        String userpwd = req.getParameter("userpwd");
        try{
            UserLoginService userLoginService = new UserLoginServiceImpl();
            Users users = userLoginService.userLogin(username, userpwd);
            // 建立客户端和服务端的会话状态
            HttpSession session = req.getSession();
            session.setAttribute(Constants.USER_SESSION_KEY,users);

            // 实现单点登录 首先判断在全局变量servletContext中是否存在之前登录的session，只要登录就将session存放到servletContext全局变量中，以userid作为key,session作为value
            ServletContext servletContext = this.getServletContext();
            HttpSession temp = (HttpSession) servletContext.getAttribute(users.getUserid() + "");
            if(temp != null){ // 若之前存在已经登录的session,将其从全局变量servletContext中移除，并将其session失效
                servletContext.removeAttribute(users.getUserid()+"");

                // 若之前登录的session由于超时时间到了已被销毁了 此处在此销毁会出现错误，所以需要session生命周期的监听器来移除servletContext中已经超时的session
                temp.invalidate();
            }

            // 只要登录就将session存放到servletContext全局变量中
            servletContext.setAttribute(users.getUserid()+"",session);

            // 使用重定向跳转首页 地址栏会发生改变,不传递参数
            resp.sendRedirect("main.jsp");
        }catch (UserNotFoundException e){
            req.setAttribute("msg",e.getMessage());
            req.getRequestDispatcher("login.jsp").forward(req,resp);
        }catch (Exception e){
            resp.sendRedirect("error.jsp");
        }
    }
}
