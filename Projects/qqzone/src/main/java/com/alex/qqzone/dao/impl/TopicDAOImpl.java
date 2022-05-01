package com.alex.qqzone.dao.impl;

import java.util.List;

import com.alex.qqzone.dao.TopicDAO;
import com.alex.qqzone.myssm.basedao.BaseDAO;
import com.alex.qqzone.pojo.Topic;
import com.alex.qqzone.pojo.UserBasic;

public class TopicDAOImpl extends BaseDAO<Topic> implements TopicDAO {

    @Override
    public List<Topic> getTopicList(UserBasic userBasic) {
        String sql = "select * from t_topic where author = ?";
        return super.executeQuery(sql, userBasic.getId());
    }

    @Override
    public void addTopic(Topic topic) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void delTopic(Topic topic) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public Topic getTopic(Integer id) {
        // TODO Auto-generated method stub
        return null;
    }
    
}