package com.wangzhixiong.web.filter;

import com.wangzhixiong.commons.Constants;
import com.wangzhixiong.pojo.Users;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * 判断当前浏览器是否登录
 */
@WebFilter(urlPatterns = {"*.do","*.jsp"})
public class UserLoginFilter implements Filter
{
    @Override
    public void init(FilterConfig filterConfig) throws ServletException
    {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException
    {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        String uri = request.getRequestURI();
        if(uri.indexOf("login.jsp") != -1 || uri.indexOf("login.do") != -1){ // 放行两类uri
            filterChain.doFilter(servletRequest, servletResponse);
        }else {
            HttpSession session = request.getSession();
            Users users = (Users) session.getAttribute(Constants.USER_SESSION_KEY);
            if(users != null){
                filterChain.doFilter(servletRequest, servletResponse);
            }else {
                request.setAttribute(Constants.REQUEST_MSG,"请先登录！");
                request.getRequestDispatcher("login.jsp").forward(servletRequest, servletResponse);
            }
        }
    }

    @Override
    public void destroy()
    {

    }
}
