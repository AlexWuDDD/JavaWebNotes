package com.alex.qqzone.service.impl;

import java.util.List;

import com.alex.qqzone.dao.UserBasicDAO;
import com.alex.qqzone.pojo.UserBasic;
import com.alex.qqzone.service.UserBasicService;

public class UserBasicServiceImpl implements UserBasicService{


    private UserBasicDAO userBasicDAO = null;

    @Override
    public UserBasic login(String loginId, String pwd) {
        return userBasicDAO.getUserBasic(loginId, pwd);
    }

    @Override
    public List<UserBasic> getFriendList(UserBasic userBasic) {
        return userBasicDAO.getUserBasicsList(userBasic);
    }

    @Override
    public UserBasic getUserBasicById(Integer id) {
        return userBasicDAO.getUserBasicById(id);
    }
    
}
