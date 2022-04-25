package com.alex.demo;

import java.io.IOException;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//演示application保存作用域
@WebServlet("/demo05")
public class Demo05Servlet extends HttpServlet{
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //1.向application保存作用域保存数据
        //servletContext: Servlet上下文
        ServletContext application = req.getServletContext();
        application.setAttribute("uname", "lili");
    }

}