package com.alex.bookcity.controllers;

import javax.servlet.http.HttpSession;

import com.alex.bookcity.pojo.User;
import com.alex.bookcity.service.UserService;

public class UserController {

    private UserService userService;
    
    public String login(String uname, String pwd, HttpSession session){  

        User user = userService.login(uname, pwd);
        System.out.println("登入" + user==null?"失败":"成功");
        if(user != null){
            session.setAttribute("currUser", user);
            return "redirect:book.do";
        }
        return "user/login";
    }
}
