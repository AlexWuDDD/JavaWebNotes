package com.alex.demo;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//演示session保存作用域
@WebServlet("/demo04")
public class Demo04Servlet extends HttpServlet{
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        //1. 获取request保存作用域保存的数据
        Object unameObj = req.getSession().getAttribute("uname");
        System.out.println(unameObj);
    }

}