package com.alex.bookcity.pojo;

import java.math.BigDecimal;

public class CartItem {
    private Integer id;
    private Book book;
    private Integer buyCount;
    private User USER;

    private Double xj;

    public CartItem(){}

    public CartItem(Integer id){
        this.id = id;
    }

    public CartItem(Integer id, Integer buyCount){
        this.id = id;
        this.buyCount = buyCount;
    }

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Book getBook() {
        return this.book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public Integer getBuyCount() {
        return this.buyCount;
    }

    public void setBuyCount(Integer buyCount) {
        this.buyCount = buyCount;
    }

    public User getUSER() {
        return this.USER;
    }

    public void setUSER(User USER) {
        this.USER = USER;
    }

    public Double getxj(){
        BigDecimal bigDecimalPrice = new BigDecimal(""+this.book.getPrice());
        BigDecimal bigDecimalBuyCount = new BigDecimal(""+this.buyCount);
        BigDecimal bigDecimalxj = bigDecimalPrice.multiply(bigDecimalBuyCount);
        xj = bigDecimalxj.doubleValue();
        return xj;
    }

}
