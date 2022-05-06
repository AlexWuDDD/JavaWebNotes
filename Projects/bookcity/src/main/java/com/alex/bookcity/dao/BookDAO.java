package com.alex.bookcity.dao;

import java.util.List;

import com.alex.bookcity.pojo.Book;

public interface BookDAO {
    List<Book> getBookList();
}
