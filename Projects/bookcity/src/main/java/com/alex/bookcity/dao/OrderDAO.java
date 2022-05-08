package com.alex.bookcity.dao;

import java.util.List;

import com.alex.bookcity.pojo.Order;
import com.alex.bookcity.pojo.User;

public interface OrderDAO {
    void addOrder(Order order);

    List<Order> getOrderList(User user);
}
