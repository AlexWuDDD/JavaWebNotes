package com.alex.bookcity.pojo;

public class Book {
    private Integer id;
    private String bookName;
    private String author;
    private Double price;
    private Integer salCount;
    private Integer bookCount;
    private String bookImg;
    private Integer bookStatus = 0; //0: 正常 ；-1：无效

    public Book(){}

    public Book(Integer id){
        this.id = id;
    }

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getBookName() {
        return this.bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public String getAuthor() {
        return this.author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public Double getPrice() {
        return this.price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Integer getSalCount() {
        return this.salCount;
    }

    public void setSalCount(Integer salCount) {
        this.salCount = salCount;
    }

    public Integer getBookCount() {
        return this.bookCount;
    }

    public void setBookCount(Integer bookCount) {
        this.bookCount = bookCount;
    }

    public String getBookImg() {
        return this.bookImg;
    }

    public void setBookImg(String bookImg) {
        this.bookImg = bookImg;
    }

    public Integer getBookStatus() {
        return this.bookStatus;
    }

    public void setBookStatus(Integer bookStatus) {
        this.bookStatus = bookStatus;
    }
}
