package com.alex.qqzone.dao;

import java.util.List;

import com.alex.qqzone.pojo.UserBasic;

public interface UserBasicDAO {
    //根据账号和密码获取特定用户信息
    public UserBasic getUserBasic(String loginId, String pwd);
    //获取指定用户的所有好友列表
    public List<UserBasic> getUserBasicsList(UserBasic userBasic);
    //根据id获取指定用户信息
    public UserBasic getUserBasicById(Integer id);
}
