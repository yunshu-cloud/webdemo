package com.wangzhixiong.web.servlet;

import com.wangzhixiong.commons.Constants;
import com.wangzhixiong.exception.UserNotFoundException;
import com.wangzhixiong.pojo.Users;
import com.wangzhixiong.service.UserLoginService;
import com.wangzhixiong.service.impl.UserLoginServiceImpl;

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
