package com.alex.bookcity.dao.impl;

import com.alex.bookcity.dao.UserDAO;
import com.alex.bookcity.myssm.basedao.BaseDAO;
import com.alex.bookcity.pojo.User;

public class UserDAOImpl extends BaseDAO<User> implements UserDAO {

    @Override
    public User getUser(String uname, String pwd) {
        String sql = "select * from t_user where uname like ? and pwd like ?";
        return super.load(sql, uname, pwd);
    }
    
}
