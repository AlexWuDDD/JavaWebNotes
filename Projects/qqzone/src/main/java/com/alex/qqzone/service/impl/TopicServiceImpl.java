package com.alex.qqzone.service.impl;

import java.util.List;

import com.alex.qqzone.dao.TopicDAO;
import com.alex.qqzone.pojo.Topic;
import com.alex.qqzone.pojo.UserBasic;
import com.alex.qqzone.service.TopicService;

public class TopicServiceImpl implements TopicService {

    private TopicDAO topicDAO = null;

    @Override
    public List<Topic> getTopicList(UserBasic userBasic) {
        return topicDAO.getTopicList(userBasic);
    }
    
}
