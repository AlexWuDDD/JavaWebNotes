package com.alex.bookcity.dao.impl;

import java.util.List;

import com.alex.bookcity.dao.OrderDAO;
import com.alex.bookcity.myssm.basedao.BaseDAO;
import com.alex.bookcity.pojo.Order;
import com.alex.bookcity.pojo.User;

public class OrderDAOImpl extends BaseDAO<Order> implements OrderDAO {

    @Override
    public void addOrder(Order order) {
        
        String sql = "insert into t_order(orderNo, orderDate, orderMoney, orderStatus, orderUser) values(?, ?, ?, ?, ?)";
        int orderID =  super.executeUpdate(sql, order.getOrderNo(), order.getOrderDate(), order.getOrderMoney(), order.getOrderStatus(), order.getOrderUser().getId());       
        order.setId(orderID);
        //思考：此处为什么需要接受executeUpdate返回的orderID, 然后设置到Order中的ID属性上？
        

    }

    @Override
    public List<Order> getOrderList(User user) {
        String sql = "select * from t_order where orderUser = ?";
        return super.executeQuery(sql, user.getId());
    }
    
}
