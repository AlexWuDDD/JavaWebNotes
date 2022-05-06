package com.alex.bookcity.dao.impl;

import java.util.List;

import com.alex.bookcity.dao.BookDAO;
import com.alex.bookcity.myssm.basedao.BaseDAO;
import com.alex.bookcity.pojo.Book;

public class BookDAOImpl extends BaseDAO<Book> implements BookDAO{

    @Override
    public List<Book> getBookList() {
        String sql = "select * from t_book where bookStatus=0";
        return super.executeQuery(sql);
    }
    
}
