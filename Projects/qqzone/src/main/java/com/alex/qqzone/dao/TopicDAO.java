package com.alex.qqzone.dao;

import java.util.List;

import com.alex.qqzone.pojo.Topic;
import com.alex.qqzone.pojo.UserBasic;

public interface TopicDAO {
    
    //获取指定用户的日志列表
    public List<Topic> getTopicList(UserBasic userBasic);
    //添加日志
    public void addTopic(Topic topic);
    //删除日志
    public void delTopic(Topic topic);
    //获取特定日志信息
    public Topic getTopic(Integer id);
}
