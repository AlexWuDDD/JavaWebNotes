package com.alex.bookcity.controllers;

import javax.servlet.http.HttpSession;

import com.alex.bookcity.pojo.Book;
import com.alex.bookcity.pojo.Cart;
import com.alex.bookcity.pojo.CartItem;
import com.alex.bookcity.pojo.User;
import com.alex.bookcity.service.CartItemService;

public class CartController {

    private CartItemService cartItemService = null;

    //加载当前用户的购物车信息
    public String index(HttpSession session){
        User user = (User)session.getAttribute("currUser");
        Cart cart = cartItemService.getCart(user);
        user.setCart(cart);
        session.setAttribute("currUser", user);
        return "cart/cart";
    }
    
    public String addCart(Integer bookId, HttpSession session){
        User user = (User) session.getAttribute("currUser");
        //将指定的图书添加到当前用户的购物车中
        CartItem cartItem = new CartItem();
        cartItem.setBook(new Book(bookId));
        cartItem.setBuyCount(1);
        cartItem.setUSER(user);


        cartItemService.addOrUpdateCartItem(cartItem, user.getCart());

        return "redirect:cart.do";
    }

    public String editCart(Integer cartItemId, Integer buyCount){
        cartItemService.updateCartItem(new CartItem(cartItemId, buyCount));
        return "redirect:cart.do";
    }
}
