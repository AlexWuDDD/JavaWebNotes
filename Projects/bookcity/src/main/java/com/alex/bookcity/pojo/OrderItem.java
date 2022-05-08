package com.alex.bookcity.pojo;

public class OrderItem {
    private Integer id;
    private Book book;
    private Integer buyCount;
    private Order orderBean;

    public OrderItem() {}

    public OrderItem(Integer id){
        this.id = id;
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

    public Order getOrder() {
        return this.orderBean;
    }

    public void setOrder(Order order) {
        this.orderBean= order;
    }

}
