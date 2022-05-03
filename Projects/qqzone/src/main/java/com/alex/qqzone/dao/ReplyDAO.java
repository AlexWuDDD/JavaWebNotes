package com.alex.qqzone.dao;

import java.util.List;

import com.alex.qqzone.pojo.Reply;
import com.alex.qqzone.pojo.Topic;

public interface ReplyDAO {
    //获取指定日志的回复列表
    public List<Reply> getReplyList(Topic topic); 
    //添加回复
    public void addReply(Reply reply);
    //删除回复
    public void delReply(Integer id);
    //获取reply
    public Reply getReplyById(Integer id);
}
