package com.alex.qqzone.dao.impl;

import java.util.List;

import com.alex.qqzone.dao.ReplyDAO;
import com.alex.qqzone.myssm.basedao.BaseDAO;
import com.alex.qqzone.pojo.Reply;
import com.alex.qqzone.pojo.Topic;

public class ReplyDAOImpl extends BaseDAO<Reply> implements ReplyDAO {

    @Override
    public List<Reply> getReplyList(Topic topic) {
        String  sql = "select * from t_reply where topic = ?";
        return super.executeQuery(sql, topic.getId());
    }

    @Override
    public void addReply(Reply reply) {
        String sql = "insert into t_reply(content, replyDate, author, topic) values(?,?,?,?)";
        super.executeUpdate(sql, reply.getContent(), reply.getReplyDate(), reply.getAuthor().getId(), reply.getTopic().getId());
    }

    @Override
    public void delReply(Integer id) {
        String sql = "delete from t_reply where id = ?";
        super.executeUpdate(sql, id);
    }

}
