package com.alex.bookcity.service.impl;

import java.util.List;
import java.util.Map;

import com.alex.bookcity.dao.OrderDAO;
import com.alex.bookcity.dao.OrderItemDAO;
import com.alex.bookcity.pojo.CartItem;
import com.alex.bookcity.pojo.Order;
import com.alex.bookcity.pojo.OrderItem;
import com.alex.bookcity.pojo.User;
import com.alex.bookcity.service.CartItemService;
import com.alex.bookcity.service.OrderService;

public class OrderServiceImpl implements OrderService{

    private OrderDAO orderDAO = null;
    private OrderItemDAO orderItemDAO = null;
    private CartItemService cartItemService = null;

    @Override
    public void addOrder(Order order) {
     
        //1. 订单表添加一条记录
        orderDAO.addOrder(order); //执行完这一步， order中的id属性就被赋值了
        //2. 订单表添加7条记录
        User user = order.getOrderUser();
        Map<Integer, CartItem> cartItemMap =  user.getCart().getCartItemMap();
        for(CartItem cartItem : cartItemMap.values()){
            OrderItem orderItem = new OrderItem();
            orderItem.setBook(cartItem.getBook());
            orderItem.setBuyCount(cartItem.getBuyCount());
            orderItem.setOrder(order);

            orderItemDAO.addOrderItem(orderItem);
        }

        //3. 购物车表删除记录
        for(CartItem cartItem : cartItemMap.values()){
            cartItemService.delCartItem(cartItem);
        }
    }

    @Override
    public List<Order> getOrderList(User user) {
        return orderDAO.getOrderList(user);
    }
    
}
