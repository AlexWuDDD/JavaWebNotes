package com.alex.cookies;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/cookie01")
public class CookieServlet01 extends HttpServlet{
    
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //1. 创建一个Cookie对象
        Cookie cookie = new Cookie("uname", "jim");
        cookie.setMaxAge(60); //秒
        // cookie.setDomain("");
        // cookie.setPath("");

        //2. 将这个Cookie对象保存到浏览器端
        resp.addCookie(cookie);

        req.getRequestDispatcher("hello01.html").forward(req, resp);
    }
}

/**
 * 可记住用户名和密码10天 setMaxAge(60 * 60 * 24 * 10)
 * 十天免登陆
 */