package com.alex.qqzone.dao.impl;

import com.alex.qqzone.dao.HostReplyDAO;
import com.alex.qqzone.myssm.basedao.BaseDAO;
import com.alex.qqzone.pojo.HostReply;

public class HostReplyDAOImpl extends BaseDAO<HostReply> implements HostReplyDAO {

    @Override
    public HostReply getHostReplyByReplyId(Integer replyId) {
        String sql = "select * from t_host_reply where reply = ?";
        return super.load(sql, replyId);
    }
    
}
