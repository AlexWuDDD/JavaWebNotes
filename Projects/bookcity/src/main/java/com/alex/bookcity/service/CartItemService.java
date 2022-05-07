package com.alex.bookcity.service;

import com.alex.bookcity.pojo.Cart;
import com.alex.bookcity.pojo.CartItem;
import com.alex.bookcity.pojo.User;

public interface CartItemService {

    void addCartItem(CartItem cartItem);

    void updateCartItem(CartItem cartItem);

    void addOrUpdateCartItem(CartItem cartItem, Cart cart);

    //加载特定用户的购物车信息
    Cart getCart(User user);

}
