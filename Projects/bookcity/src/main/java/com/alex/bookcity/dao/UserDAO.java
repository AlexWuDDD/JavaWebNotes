package com.alex.bookcity.dao;

import com.alex.bookcity.pojo.User;

public interface UserDAO {
    
    User getUser(String uname, String pwd);

    void addUser(User user);
}
