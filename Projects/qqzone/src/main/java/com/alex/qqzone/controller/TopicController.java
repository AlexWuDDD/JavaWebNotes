package com.alex.qqzone.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import com.alex.qqzone.pojo.Topic;
import com.alex.qqzone.pojo.UserBasic;
import com.alex.qqzone.service.TopicService;

public class TopicController {

    private TopicService topicService = null;
    
    public String topicDetail(Integer id, HttpSession session){
        Topic topic = topicService.getTopicById(id);
        session.setAttribute("topic", topic);
        return "frames/detail";
    }

    public String delTopic(Integer topicId){
        topicService.delTopic(topicId);
        return "redirect:topic.do?operate=getTopicList";
    }

    public String getTopicList(HttpSession session){
        UserBasic userBasic = (UserBasic)session.getAttribute("userBasic");
        List<Topic> topicList =  topicService.getTopicList(userBasic);
        userBasic.setTopicList(topicList);

        //因为main.html页面迭代的是friend这个key的数据
        // session.setAttribute("userBasic", userBasic);
        session.setAttribute("friend", userBasic);

        return "frames/main";
    }
}
