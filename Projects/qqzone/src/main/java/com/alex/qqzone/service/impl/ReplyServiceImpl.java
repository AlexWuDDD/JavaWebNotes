package com.alex.qqzone.service.impl;

import java.util.List;
import com.alex.qqzone.dao.ReplyDAO;
import com.alex.qqzone.pojo.HostReply;
import com.alex.qqzone.pojo.Reply;
import com.alex.qqzone.pojo.Topic;
import com.alex.qqzone.pojo.UserBasic;
import com.alex.qqzone.service.HostReplyService;
import com.alex.qqzone.service.ReplyService;
import com.alex.qqzone.service.UserBasicService;

public class ReplyServiceImpl implements ReplyService {

    private ReplyDAO replyDAO = null;
    //此处引入的是其他POJO对应的Service接口，而不是DAO接口
    //其他POJO对应的业务逻辑是封装在service层的
    //我需要调用别人的业务逻辑方法，而不是去深入考虑人家内部的细节
    private HostReplyService  hostReplyService = null;

    private UserBasicService userBasicService = null;

    @Override
    public List<Reply> getReplyListByTopicId(Integer topicId) {
        List<Reply> replyList =  replyDAO.getReplyList(new Topic(topicId));
        for(int i = 0; i < replyList.size(); ++i){
            Reply reply = replyList.get(i);
            UserBasic author = reply.getAuthor();
            author = userBasicService.getUserBasicById(author.getId());
            reply.setAuthor(author);
            HostReply hostReply = hostReplyService.getHostReplyByReplyId(reply.getId());
            reply.setHostReply(hostReply);
        }
        return replyList;
    }

    @Override
    public void addReply(Reply reply) {
        replyDAO.addReply(reply);
    }

    @Override
    public void delReply(Integer replyId) {
        //1. 根据id获取到reply
        Reply reply = replyDAO.getReplyById(replyId);

        if(reply != null){
            //2. 如果reply有关联的hostReply, 则先删除hostReply
            HostReply hostReply = hostReplyService.getHostReplyByReplyId(replyId);
            if(hostReply != null){
                hostReplyService.delHostReply(hostReply.getId());
            }
            //3. 删除reply
            replyDAO.delReply(replyId);
        }
    }

    @Override
    public void delReplyList(Topic topic) {
        List<Reply> replyList = replyDAO.getReplyList(topic);
        if(replyList != null){
            for(Reply reply : replyList){
                delReply(reply.getId());
            }
        }
    }
    
}
