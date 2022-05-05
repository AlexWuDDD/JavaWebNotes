package com.alex.bookcity.controllers;

import com.alex.bookcity.pojo.User;
import com.alex.bookcity.service.UserService;

public class UserController {

    private UserService userService;
    
    public String login(String uname, String pwd){  

        User user = userService.login(uname, pwd);
        System.out.println("user: " + user);
        return "index";
    }
}
