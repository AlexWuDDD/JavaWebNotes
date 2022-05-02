package com.alex.qqzone.dao.impl;

import java.util.List;

import com.alex.qqzone.dao.UserBasicDAO;
import com.alex.qqzone.myssm.basedao.BaseDAO;
import com.alex.qqzone.pojo.UserBasic;

public class UserBasicDAOImpl extends BaseDAO<UserBasic> implements UserBasicDAO{

    @Override
    public UserBasic getUserBasic(String loginId, String pwd) {
        String sql = "select * from t_user_basic where loginId = ? and pwd = ?";
        return super.load(sql, loginId, pwd);
    }

    @Override
    public List<UserBasic> getUserBasicsList(UserBasic userBasic) {
        String sql = "SELECT * FROM  t_user_basic WHERE id IN (SELECT fid FROM t_friend WHERE uid = ?)";
        return super.executeQuery(sql, userBasic.getId());
    }

    @Override
    public UserBasic getUserBasicById(Integer id) {
        String sql = "select * from t_user_basic where id = ?";
        return super.load(sql, id);
    }
}
