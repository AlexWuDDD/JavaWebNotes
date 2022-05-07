package com.alex.bookcity.service.impl;

import java.util.List;

import com.alex.bookcity.dao.BookDAO;
import com.alex.bookcity.pojo.Book;
import com.alex.bookcity.service.BookService;

public class BookServiceImpl implements BookService{

    private BookDAO bookDAO = null;

    @Override
    public List<Book> getBookList() {
        return bookDAO.getBookList();
    }

    @Override
    public Book getBook(Integer id) {
        return bookDAO.getBook(id);
    }
    
}
