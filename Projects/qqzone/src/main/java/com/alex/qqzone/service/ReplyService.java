package com.alex.qqzone.service;

import java.util.List;

import com.alex.qqzone.pojo.Reply;

public interface ReplyService {
    
    //根据topic的id获取关联所有的的reply
    List<Reply> getReplyListByTopicId(Integer topicId);

    //添加回复
    void addReply(Reply reply);
}
