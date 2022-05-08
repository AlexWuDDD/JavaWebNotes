package com.alex.bookcity.pojo;

import java.time.LocalDateTime;
import java.util.List;

public class Order {
    private Integer id;
    private String orderNo;
    private LocalDateTime orderDate;
    private Double orderMoney;
    private Integer orderStatus;
    private User orderUser;

    private List<OrderItem> orderItemList;

    private Integer totalBookCount;

    public Integer getTotalBookCount() {
        return this.totalBookCount;
    }

    public void setTotalBookCount(Integer totalBookCount) {
        this.totalBookCount = totalBookCount;
    }

    public Order() {
    }

    public Order(Integer id) {
        this.id = id;
    }


    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getOrderNo() {
        return this.orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    public LocalDateTime getOrderDate() {
        return this.orderDate;
    }

    public void setOrderDate(LocalDateTime orderDate) {
        this.orderDate = orderDate;
    }

    public Double getOrderMoney() {
        return this.orderMoney;
    }

    public void setOrderMoney(Double orderMoney) {
        this.orderMoney = orderMoney;
    }


    public Integer getOrderStatus() {
        return this.orderStatus;
    }

    public void setOrderStatus(Integer orderStatus) {
        this.orderStatus = orderStatus;
    }

    public User getOrderUser() {
        return this.orderUser;
    }

    public void setOrderUser(User ordeUser) {
        this.orderUser = ordeUser;
    }

    public List<OrderItem> getOrderItemList() {
        return this.orderItemList;
    }

    public void setOrderItemList(List<OrderItem> orderItemList) {
        this.orderItemList = orderItemList;
    }

}
