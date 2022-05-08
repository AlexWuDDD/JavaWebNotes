package com.alex.bookcity.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.alex.bookcity.dao.CartItemDAO;
import com.alex.bookcity.pojo.Cart;
import com.alex.bookcity.pojo.CartItem;
import com.alex.bookcity.pojo.User;
import com.alex.bookcity.service.BookService;
import com.alex.bookcity.service.CartItemService;

public class CartItemServiceImpl implements CartItemService{

    private CartItemDAO cartItemDAO = null;
    private BookService bookService = null;

    @Override
    public void addCartItem(CartItem cartItem) {
        cartItemDAO.addCartItem(cartItem);
    }

    @Override
    public void updateCartItem(CartItem cartItem) {
        cartItemDAO.updateCartItem(cartItem);
    }

    @Override
    public void addOrUpdateCartItem(CartItem cartItem, Cart cart) {
        // 判断当前用户的购物车中是否有这本书的CartItem，有->updtae, 无->add
        if(cart != null){
            Map<Integer, CartItem> cartItemMap = cart.getCartItemMap();
            if(cartItemMap == null){
                cartItemMap = new HashMap<>();
            }

            if(cartItemMap.containsKey(cartItem.getBook().getId())){
                CartItem cartItem1 =  cartItemMap.get(cartItem.getBook().getId());
                cartItem1.setBuyCount(cartItem1.getBuyCount() + cartItem.getBuyCount());
                updateCartItem(cartItem1);
            }else{
                addCartItem(cartItem);
            }
        }
    }

    @Override
    public Cart getCart(User user) {
        List<CartItem> cartItemList = getCartItemList(user);
        Map<Integer, CartItem> cartItemMap = new HashMap<>();
        for(CartItem cartItem : cartItemList){
            cartItemMap.put(cartItem.getBook().getId(), cartItem);
        }
        Cart cart = new Cart();
        cart.setCartItemMap(cartItemMap);
        return cart;
    }

    @Override
    public List<CartItem> getCartItemList(User user) {
        List<CartItem> cartItemList = cartItemDAO.getCartItemList(user);
        for(CartItem cartItem : cartItemList){
            cartItem.setBook(bookService.getBook(cartItem.getBook().getId()));
        }
        return cartItemList;
    }

    @Override
    public void delCartItem(CartItem cartItem) {
        cartItemDAO.delCartItem(cartItem);
    }
    
}
