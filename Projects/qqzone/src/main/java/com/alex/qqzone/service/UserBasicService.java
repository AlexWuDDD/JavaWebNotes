package com.alex.qqzone.service;

import java.util.List;

import com.alex.qqzone.pojo.UserBasic;

public interface UserBasicService {
    
    UserBasic login(String loginId, String pwd);
    List<UserBasic> getFriendList(UserBasic userBasic);
    UserBasic getUserBasicById(Integer id);
}
