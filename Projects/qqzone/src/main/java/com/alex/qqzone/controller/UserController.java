package com.alex.qqzone.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import com.alex.qqzone.pojo.Topic;
import com.alex.qqzone.pojo.UserBasic;
import com.alex.qqzone.service.TopicService;
import com.alex.qqzone.service.UserBasicService;

public class UserController {

    private UserBasicService userBasicService = null;
    private TopicService topicService = null;
    
    public String login(String loginId, String pwd, HttpSession session){
        //1. 登录验证
        UserBasic userBasic = userBasicService.login(loginId, pwd);
        if(userBasic != null){
            List<UserBasic> friendList = userBasicService.getFriendList(userBasic);
            List<Topic> topicList = topicService.getTopicList(userBasic);

            userBasic.setFriendList(friendList);
            userBasic.setTopicList(topicList);
            session.setAttribute("userBasic", userBasic);
            return "index";
        }
        else{
            return "login";
        }
    }
}
