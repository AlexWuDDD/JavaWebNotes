package com.alex.bookcity.controllers;

import javax.servlet.http.HttpSession;

import com.alex.bookcity.pojo.CartItem;
import com.alex.bookcity.pojo.User;
import com.alex.bookcity.service.BookService;
import com.alex.bookcity.service.CartItemService;

public class CartController {

    private CartItemService cartItemService = null;
    private BookService bookService = null;
    
    public String addCart(Integer bookId, HttpSession session){
        User user = (User) session.getAttribute("currUser");
        //将指定的图书添加到当前用户的购物车中
        CartItem cartItem = new CartItem();
        cartItem.setBook(bookService.getBook(bookId));
        cartItem.setBuyCount(1);
        cartItem.setUSER(user);


        cartItemService.addOrUpdateCartItem(cartItem, user.getCart());

        return "redirect:cart.do";
    }
}
