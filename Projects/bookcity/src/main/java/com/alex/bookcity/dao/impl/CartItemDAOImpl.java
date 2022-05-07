package com.alex.bookcity.dao.impl;

import java.util.List;

import com.alex.bookcity.dao.CartItemDAO;
import com.alex.bookcity.myssm.basedao.BaseDAO;
import com.alex.bookcity.pojo.CartItem;
import com.alex.bookcity.pojo.User;

public class CartItemDAOImpl extends BaseDAO<CartItem> implements CartItemDAO {

    @Override
    public void addCartItem(CartItem cartItem) {
        String sql = "insert into t_cart_item(book, buyCount, USER) values(?, ?, ?)";
        super.executeUpdate(sql, cartItem.getBook().getId(), cartItem.getBuyCount(), cartItem.getUSER().getId());
    }

    @Override
    public void updateCartItem(CartItem cartItem) {
        String sql = "update t_cart_item set buyCount=? where id=?";
        super.executeUpdate(sql, cartItem.getBuyCount(), cartItem.getId());
        
    }

    @Override
    public List<CartItem> getCartItemList(User user) {
        String sql = "select * from t_cart_item where USER=?";
        return super.executeQuery(sql, user.getId());
    }

}
