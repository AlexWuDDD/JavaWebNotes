package com.alex.bookcity.service.impl;

import com.alex.bookcity.dao.UserDAO;
import com.alex.bookcity.pojo.User;
import com.alex.bookcity.service.UserService;

public class UserServiceImpl implements UserService {
    
    private UserDAO userDAO = null;

    @Override
    public User login(String uname, String pwd) {
        return userDAO.getUser(uname, pwd);
    }

    @Override
    public void addUser(User user) {
        // TODO Auto-generated method stub
        userDAO.addUser(user);
    }

    @Override
    public User getUser(String uname) {
        return userDAO.getUser(uname);
    }
}
