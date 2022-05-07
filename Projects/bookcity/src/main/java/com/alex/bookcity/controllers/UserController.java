package com.alex.bookcity.controllers;

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
}
