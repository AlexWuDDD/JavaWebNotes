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

    @Override
    public void addUser(User user) {
        String sql = "insert into t_user(uname, pwd, email, role) values(?,?,?,?)";
        super.executeUpdate(sql, user.getUname(), user.getPwd(), user.getEmail(), user.getRole());
    }

    @Override
    public User getUser(String uname) {
        String sql = "select * from t_user where uname like ?";
        return super.load(sql, uname);
    }
    
}
