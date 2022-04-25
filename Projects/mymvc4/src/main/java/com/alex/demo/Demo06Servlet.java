package com.alex.demo;

import java.io.IOException;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//演示application保存作用域
@WebServlet("/demo06")
public class Demo06Servlet extends HttpServlet{
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        //1. 获取appliction保存作用域保存的数据
        ServletContext application = req.getServletContext();
        Object unameObj = application.getAttribute("uname");
        System.out.println(unameObj);
    }

}