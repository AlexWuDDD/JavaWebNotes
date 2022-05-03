package com.alex.qqzone.dao;

import com.alex.qqzone.pojo.HostReply;

public interface HostReplyDAO {

    HostReply getHostReplyByReplyId(Integer replyId);

    void delHostReply(Integer id);
}
