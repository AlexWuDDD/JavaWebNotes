package com.alex.bookcity.dao;

import java.util.List;

import com.alex.bookcity.pojo.CartItem;
import com.alex.bookcity.pojo.User;

public interface CartItemDAO {
    //新增购车车项
    void addCartItem(CartItem cartItem);
    //修改特定的购物车项
    void updateCartItem(CartItem cartItem);
    //获取特定用户的所有购物车项
    List<CartItem> getCartItemList(User user);
}
