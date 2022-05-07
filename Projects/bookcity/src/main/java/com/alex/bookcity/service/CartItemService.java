package com.alex.bookcity.service;

import java.util.List;

import com.alex.bookcity.pojo.Cart;
import com.alex.bookcity.pojo.CartItem;
import com.alex.bookcity.pojo.User;

public interface CartItemService {

    void addCartItem(CartItem cartItem);

    void updateCartItem(CartItem cartItem);

    void addOrUpdateCartItem(CartItem cartItem, Cart cart);

    //获取指定用户的所有购物车列表
    List<CartItem> getCartItemList(User user);

    //加载特定用户的购物车信息
    Cart getCart(User user);

}
