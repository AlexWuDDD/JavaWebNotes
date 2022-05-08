package com.alex.bookcity.service;

import java.util.List;

import com.alex.bookcity.pojo.Order;
import com.alex.bookcity.pojo.User;

public interface OrderService {
    void addOrder(Order order);

    List<Order> getOrderList(User user);
}
