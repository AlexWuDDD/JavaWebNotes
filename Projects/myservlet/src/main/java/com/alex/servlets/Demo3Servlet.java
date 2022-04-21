package com.alex.servlets;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

//演示从HttoSession保存作用域中获取数据
public class Demo3Servlet extends HttpServlet{

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp){
        //获取session,如果获取不到，则创建一个新的
        HttpSession session =  req.getSession();
        Object unameObj = session.getAttribute("uname");
        System.out.println(unameObj);

    }
}
