package com.alex.qqzone.controller;

import javax.servlet.http.HttpSession;

import com.alex.qqzone.pojo.Topic;
import com.alex.qqzone.service.TopicService;

public class TopicController {

    private TopicService topicService = null;
    
    public String topicDetail(Integer id, HttpSession session){
        Topic topic = topicService.getTopicById(id);
        session.setAttribute("topic", topic);
        return "frames/detail";
    }
}
