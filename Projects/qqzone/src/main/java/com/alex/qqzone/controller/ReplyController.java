package com.alex.qqzone.controller;

import java.time.LocalDateTime;

import javax.servlet.http.HttpSession;

import com.alex.qqzone.pojo.Reply;
import com.alex.qqzone.pojo.Topic;
import com.alex.qqzone.pojo.UserBasic;
import com.alex.qqzone.service.ReplyService;

public class ReplyController {

    private ReplyService replyService = null;

    public String addReply(String content, Integer topicId, HttpSession session){
        
        UserBasic author = (UserBasic)session.getAttribute("userBasic");

        Reply reply = new Reply(content, LocalDateTime.now(), author, new Topic(topicId));

        replyService.addReply(reply);

        //detail.html
        return "redirect:topic.do?operate=topicDetail&id=" + topicId;
    }
    
}
