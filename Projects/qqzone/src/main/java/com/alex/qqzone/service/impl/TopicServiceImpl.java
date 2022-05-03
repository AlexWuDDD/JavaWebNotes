package com.alex.qqzone.service.impl;

import java.util.List;

import com.alex.qqzone.dao.TopicDAO;
import com.alex.qqzone.pojo.Reply;
import com.alex.qqzone.pojo.Topic;
import com.alex.qqzone.pojo.UserBasic;
import com.alex.qqzone.service.ReplyService;
import com.alex.qqzone.service.TopicService;
import com.alex.qqzone.service.UserBasicService;

public class TopicServiceImpl implements TopicService {

    private TopicDAO topicDAO = null;
    private ReplyService replyService = null;
    private UserBasicService userBasicService = null;

    @Override
    public List<Topic> getTopicList(UserBasic userBasic) {
        return topicDAO.getTopicList(userBasic);
    }

    @Override
    public Topic getTopic(Integer id){
        Topic topic = topicDAO.getTopic(id);
        UserBasic author = topic.getAuthor();
        author = userBasicService.getUserBasicById(author.getId());
        topic.setAuthor(author);
        return topic;
    }

    @Override
    public Topic getTopicById(Integer id) {
        Topic topic = getTopic(id);
        List<Reply> replyList =  replyService.getReplyListByTopicId(topic.getId());
        topic.setReplyList(replyList);
        return topic;
    }

    @Override
    public void delTopic(Integer id) {
        Topic topic = topicDAO.getTopic(id);
        if(topic != null){
            replyService.delReplyList(topic);
            topicDAO.delTopic(topic);
        }
    }   
}
