package com.alex.servlet;

import java.io.IOException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet(urlPatterns = {"/demo01"},
    initParams = {
        @WebInitParam(name = "hello", value = "world"),
        @WebInitParam(name = "uname", value = "jim"),
    }
)
public class Demo01Servlet extends HttpServlet{

    @Override
    public void init() throws ServletException {
        ServletConfig config = getServletConfig();
        String initValue = config.getInitParameter("hello");
        System.out.println(initValue);
        String initValue2 = config.getInitParameter("uname");
        System.out.println(initValue2);

        // ServletContext servletContext = getServletContext();
        // String param1 = servletContext.getInitParameter("contextConfigLoaction");
        // System.out.println(param1);
    }

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // ServletContext servletContext = req.getServletContext();
        ServletContext servletContext = req.getSession().getServletContext();
        String param1 = servletContext.getInitParameter("contextConfigLoaction");
        System.out.println(param1);
    }
    
}

//Servelt生命周期：实例化、初始化、服务、销毁
