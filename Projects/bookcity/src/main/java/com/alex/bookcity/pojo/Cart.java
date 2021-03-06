package com.alex.bookcity.pojo;

import java.math.BigDecimal;
import java.util.Map;
import java.util.Set;

public class Cart {
    private Map<Integer, CartItem> cartItemMap; //这个Map集合中的key是Book的id
    private Double totalMoney;
    private Integer totalCount;
    private Integer totalBookCount;

    public Cart(){}

    public Map<Integer,CartItem> getCartItemMap() {
        return this.cartItemMap;
    }

    public void setCartItemMap(Map<Integer,CartItem> cartItemMap) {
        this.cartItemMap = cartItemMap;
    }

    public Double getTotalMoney() {
        BigDecimal total = new BigDecimal(0.0);
        if(cartItemMap != null && cartItemMap.size() > 0){
            Set<Map.Entry<Integer,CartItem>> entires = cartItemMap.entrySet();
            for(Map.Entry<Integer, CartItem> cartItemEntry: entires){
                CartItem cartItem =  cartItemEntry.getValue();
                BigDecimal price = new BigDecimal(""+cartItem.getBook().getPrice());
                BigDecimal count = new BigDecimal(""+cartItem.getBuyCount());
                total = total.add(price.multiply(count));
            }
        }
        this.totalMoney = total.doubleValue();
        return this.totalMoney;
    }

    public Integer getTotalCount() {
        this.totalCount = 0;
        if(cartItemMap != null && cartItemMap.size()>0){
            this.totalCount = cartItemMap.size();
        }

        return this.totalCount;
    }

    public Integer getTotalBookCount(){
        this.totalBookCount = 0;
        if(cartItemMap != null && cartItemMap.size()>0){
            for(CartItem cartItem : cartItemMap.values()){
                this.totalBookCount += cartItem.getBuyCount();
            }
        }

        return this.totalBookCount;
    }

}
