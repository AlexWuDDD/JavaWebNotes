package com.alex.bookcity.controllers;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.alex.bookcity.pojo.Cart;
import com.alex.bookcity.pojo.User;
import com.alex.bookcity.service.CartItemService;
import com.alex.bookcity.service.UserService;

public class UserController {

    private UserService userService;
    private CartItemService cartItemService;
    
    public String login(String uname, String pwd, HttpSession session){  

        User user = userService.login(uname, pwd);
        System.out.println("登入" + user==null?"失败":"成功");
        if(user != null){
            Cart cart = cartItemService.getCart(user);

            user.setCart(cart);

            session.setAttribute("currUser", user);
            return "redirect:book.do";
        }
        return "user/login";
    }

    public String regist(String uname, String pwd, String email, String verifyCode, HttpSession session, HttpServletResponse response) throws IOException{

        Object obj = session.getAttribute("KAPTCHA_SESSION_KEY");
        if(obj == null || !verifyCode.equals(obj)){
            response.setCharacterEncoding("UTF-8");
            response.setContentType("text/html;charset=UTF-8");
            PrintWriter out = response.getWriter();
            out.println("<script language='javascript'>alert('验证码不正确');window.location.href='page.do?operate=page&page=/user/regist'</script>");
            //return "user/regist";
            return null;
        }
        else{
            if(verifyCode.equals(obj)){
                User user = new User();
                user.setUname(uname);
                user.setPwd(pwd);
                user.setEmail(email);
                user.setRole(0);

                userService.addUser(user);
            }
        }

        return "user/login";
    }

    public String ckUname(String uname){
        User user = userService.getUser(uname);
        if(user != null){
            //用户名已经被占用，不可以注册
            return "json:{'uanme': '1'}";
        }
        else{
            //用户可以注册
            return "json:{'uanme': '0'}";
        }
    }
}
