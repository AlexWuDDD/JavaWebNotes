package com.alex.bookcity.pojo;

import java.time.LocalDateTime;
import java.util.List;

public class OrderBean {
    private Integer id;
    private String orderNo;
    private LocalDateTime orderDate;
    private Double orderMoney;
    private Integer orderCount;
    private Integer orderStatus;
    private User USER;

    private List<OrderItem> orderItemList;

    public OrderBean() {
    }

    public OrderBean(Integer id) {
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

    public Integer getOrderCount() {
        return this.orderCount;
    }

    public void setOrderCount(Integer orderCount) {
        this.orderCount = orderCount;
    }

    public Integer getOrderStatus() {
        return this.orderStatus;
    }

    public void setOrderStatus(Integer orderStatus) {
        this.orderStatus = orderStatus;
    }

    public User getUSER() {
        return this.USER;
    }

    public void setUSER(User USER) {
        this.USER = USER;
    }

    public List<OrderItem> getOrderItemList() {
        return this.orderItemList;
    }

    public void setOrderItemList(List<OrderItem> orderItemList) {
        this.orderItemList = orderItemList;
    }

}
