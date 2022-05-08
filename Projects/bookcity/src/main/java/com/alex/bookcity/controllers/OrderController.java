package com.alex.bookcity.controllers;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpSession;

import com.alex.bookcity.pojo.Order;
import com.alex.bookcity.pojo.User;
import com.alex.bookcity.service.OrderService;

public class OrderController {
    
    private OrderService orderService = null;;

    //结账
    public String checkout(HttpSession session){

        Order order = new Order();

        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String nowStr = now.format(formatter);
        order.setOrderNo(UUID.randomUUID().toString() + "-" + nowStr);
        order.setOrderDate(now);
        
        User user = (User) session.getAttribute("currUser");
        order.setOrderUser(user);
        order.setOrderMoney(user.getCart().getTotalMoney());
        order.setOrderStatus(0);
        order.setTotalBookCount(user.getCart().getTotalBookCount());

        orderService.addOrder(order);
        
        return "index";
    }

    //查看订单列表
    public String getOrderList(HttpSession session){

        User user = (User)session.getAttribute("currUser");
        List<Order> orderList = orderService.getOrderList(user);

        user.setOrderList(orderList);

        session.setAttribute("currUser", user);

        return "order/order";
    }
}
