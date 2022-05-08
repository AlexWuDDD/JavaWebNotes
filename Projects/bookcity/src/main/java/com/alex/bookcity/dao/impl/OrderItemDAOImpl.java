package com.alex.bookcity.dao.impl;

import com.alex.bookcity.dao.OrderItemDAO;
import com.alex.bookcity.myssm.basedao.BaseDAO;
import com.alex.bookcity.pojo.OrderItem;

public class OrderItemDAOImpl extends BaseDAO<OrderItem> implements OrderItemDAO {

    @Override
    public void addOrderItem(OrderItem orderItem) {
        String sql = "insert into t_order_item(book, buyCount, orderBean) values(?, ?, ?)";
        super.executeUpdate(sql, orderItem.getBook().getId(), orderItem.getBuyCount(), orderItem.getOrder().getId());
    }
    
}
