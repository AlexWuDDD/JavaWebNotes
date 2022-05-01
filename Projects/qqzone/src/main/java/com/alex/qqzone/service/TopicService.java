package com.alex.qqzone.service;

import java.util.List;

import com.alex.qqzone.pojo.Topic;
import com.alex.qqzone.pojo.UserBasic;

public interface TopicService {
    List<Topic> getTopicList(UserBasic userBasic);
}
