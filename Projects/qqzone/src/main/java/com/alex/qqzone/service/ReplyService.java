package com.alex.qqzone.service;

import java.util.List;

import com.alex.qqzone.pojo.Reply;
import com.alex.qqzone.pojo.Topic;

public interface ReplyService {
    
    //根据topic的id获取关联所有的的reply
    List<Reply> getReplyListByTopicId(Integer topicId);

    //添加回复
    void addReply(Reply reply);

    void delReply(Integer replyId);

    //删除指定的日志关联的所有回复
    void delReplyList(Topic topic);
}
