package com.alex.servlets;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

//演示服务器内部转发及客户端重定向
public class Demo7Servlet extends HttpServlet{

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp){
        System.out.println("demo7......");

    }
}
