package com.alex.qqzone.service.impl;

import com.alex.qqzone.dao.HostReplyDAO;
import com.alex.qqzone.pojo.HostReply;
import com.alex.qqzone.service.HostReplyService;

public class HostReplyServiceImpl implements HostReplyService {

    private HostReplyDAO hostReplyDAO = null;

    @Override
    public HostReply getHostReplyByReplyId(Integer replyId) {
        return hostReplyDAO.getHostReplyByReplyId(replyId);
    }

    @Override
    public void delHostReply(Integer id) {
        hostReplyDAO.delHostReply(id);
    }
    
}
