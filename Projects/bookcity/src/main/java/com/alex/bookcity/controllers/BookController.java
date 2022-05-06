package com.alex.bookcity.controllers;

import java.util.List;

import javax.servlet.http.HttpSession;

import com.alex.bookcity.pojo.Book;
import com.alex.bookcity.service.BookService;

public class BookController {

    private BookService bookService;

    public String index(HttpSession session){
        List<Book> bookList = bookService.getBookList();
        session.setAttribute("bookList", bookList);
        return "index";
    }
    
}
