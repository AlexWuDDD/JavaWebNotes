package com.alex.qqzone.pojo;

import java.time.LocalDateTime;

public class HostReply {
    
    private Integer id;
    private String content;
    private LocalDateTime hostReplyDate;
    private UserBasic author; //N:1
    private Reply reply; //1:1

    public HostReply(){}

    public HostReply(Integer id){
        this.id = id;
    }

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

    public LocalDateTime getHostReplyDate() {
        return this.hostReplyDate;
    }

    public void setHostReplyDate(LocalDateTime hostReplyDate) {
        this.hostReplyDate = hostReplyDate;
    }

    public UserBasic getAuthor() {
        return this.author;
    }

    public void setAuthor(UserBasic author) {
        this.author = author;
    }

    public Reply getReply() {
        return this.reply;
    }

    public void setReply(Reply reply) {
        this.reply = reply;
    }
}
