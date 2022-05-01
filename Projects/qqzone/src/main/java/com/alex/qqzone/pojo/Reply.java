package com.alex.qqzone.pojo;

import java.time.LocalDateTime;

public class Reply {
    
    private Integer id;
    private String content;
    private LocalDateTime replyDate;
    private UserBasic author; //N:1
    private Topic topic; //N:1

    private HostReply hostReply; //1:1

    public Reply(){}

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getContent() {
        return this.content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public LocalDateTime getReplyDate() {
        return this.replyDate;
    }

    public void setReplyDate(LocalDateTime replyDate) {
        this.replyDate = replyDate;
    }

    public UserBasic getAuthor() {
        return this.author;
    }

    public void setAuthor(UserBasic author) {
        this.author = author;
    }

    public Topic getTopic() {
        return this.topic;
    }

    public void setTopic(Topic topic) {
        this.topic = topic;
    }

    public HostReply getHostReply() {
        return this.hostReply;
    }

    public void setHostReply(HostReply hostReply) {
        this.hostReply = hostReply;
    }
}