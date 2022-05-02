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

            //userBasic 这个key保存的是登录者的信息
            //friend这个key保存的是当前进入的是谁的空间
            session.setAttribute("userBasic", userBasic);
            session.setAttribute("friend", userBasic);
            return "index";
        }
        else{
            return "login";
        }
    }

    public String friend(Integer id, HttpSession session){
        //1.根据id获取指定的用户信息
        UserBasic currFriend =  userBasicService.getUserBasicById(id);
        List<Topic> topicList = topicService.getTopicList(currFriend);

        currFriend.setTopicList(topicList);

        session.setAttribute("friend", currFriend);

        return "index";
    }
}
