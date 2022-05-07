package com.alex.bookcity.pojo;

import java.util.Map;
import java.util.Set;

public class Cart {
    private Map<Integer, CartItem> cartItemMap; //这个Map集合中的key是Book的id
    private Double totalMoney;
    private Integer totalCount;

    public Cart(){}

    public Map<Integer,CartItem> getCartItemMap() {
        return this.cartItemMap;
    }

    public void setCartItemMap(Map<Integer,CartItem> cartItemMap) {
        this.cartItemMap = cartItemMap;
    }

    public Double getTotalMoney() {
        this.totalMoney = 0.0;
        if(cartItemMap != null && cartItemMap.size() > 0){
            Set<Map.Entry<Integer,CartItem>> entires = cartItemMap.entrySet();
            for(Map.Entry<Integer, CartItem> cartItemEntry: entires){
                CartItem cartItem =  cartItemEntry.getValue();
                this.totalMoney = cartItem.getBook().getPrice() * cartItem.getBuyCount();
            }
        }
        return this.totalMoney;
    }

    public Integer getTotalCount() {
        this.totalCount = 0;
        if(cartItemMap != null && cartItemMap.size()>0){
            this.totalCount = cartItemMap.size();
        }

        return this.totalCount;
    }

}
