package com.alex.bookcity.service;

import com.alex.bookcity.pojo.User;

public interface UserService {
    
    User login(String uname, String pwd);

    void addUser(User user);

    User getUser(String uname);
}
