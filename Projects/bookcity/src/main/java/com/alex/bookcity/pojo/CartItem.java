package com.alex.bookcity.pojo;

public class CartItem {
    private Integer id;
    private Book book;
    private String bookName;
    private Double price;
    private Integer buyCount;
    private User USER;

    public CartItem(){}

    public CartItem(Integer id){
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

    public String getBookName() {
        return this.bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public Double getPrice() {
        return this.price;
    }

    public void setPrice(Double price) {
        this.price = price;
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



}
