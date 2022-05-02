package com.alex.qqzone.pojo;

import java.time.LocalDateTime;
import java.util.List;

public class Topic {
    private Integer id;
    private String title;
    private String content;
    private LocalDateTime topicDate;
    private UserBasic author; //N:1

    private List<Reply> replyList; //1:N

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return this.title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return this.content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public LocalDateTime getTopicDate() {
        return this.topicDate;
    }

    public void setTopicDate(LocalDateTime topicDate) {
        this.topicDate = topicDate;
    }

    public UserBasic getAuthor() {
        return this.author;
    }

    public void setAuthor(UserBasic author) {
        this.author = author;
    }

    public List<Reply> getReplyList() {
        return this.replyList;
    }

    public void setReplyList(List<Reply> replyList) {
        this.replyList = replyList;
    }

    public Topic(){}

    public Topic(Integer id){
        this.id = id;
    }
}
