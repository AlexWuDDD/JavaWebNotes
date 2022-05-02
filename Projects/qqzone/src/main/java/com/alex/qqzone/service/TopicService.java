package com.alex.qqzone.service;

import java.util.List;

import com.alex.qqzone.pojo.Topic;
import com.alex.qqzone.pojo.UserBasic;

public interface TopicService {
    public List<Topic> getTopicList(UserBasic userBasic);

    public Topic getTopicById(Integer id);

    //根据id获取指定的topic信息，包含这个topic关联的作者信息
    public Topic getTopic(Integer id);
}
