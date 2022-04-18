package com.alex.fruit.dao.impl;

import java.sql.Connection;
import java.util.List;

import com.alex.fruit.dao.FruitDao;
import com.alex.fruit.dao.base.BaseDAO;
import com.alex.fruit.pojo.Fruit;

public class FruitDaoImpl extends BaseDAO<Fruit> implements FruitDao{
    @Override
    public List<Fruit> getFruitList(Connection conn) {
        String sql = "select * from fruit";
        return executeQuery(conn, sql);
        
    }

    @Override
    public boolean addFruit(Connection conn, Fruit fruit) {
        String sql = "insert into t_fruit values(?,?,?,?,?)";
        int count = executeUpdate(conn, sql,fruit.getFid(), fruit.getFname(),fruit.getPrice(),fruit.getFcount(),fruit.getRemark()) ;
        //insert语句返回的是自增列的值，而不是影响行数
        //System.out.println(count);
        return count>0;
    }

    @Override
    public boolean updateFruit(Connection conn, Fruit fruit) {
        String sql = "update t_fruit set fcount = ? where fid = ? " ;
        return executeUpdate(conn, sql,fruit.getFcount(),fruit.getFid())>0;
    }

    @Override
    public Fruit getFruitByFname(Connection conn, String fname) {
        return load(conn, "select * from t_fruit where fname like ? ",fname);
    }

    @Override
    public boolean delFruit(Connection conn, String fname) {
        String sql = "delete from t_fruit where fname like ? " ;
        return executeUpdate(conn, sql,fname)>0;
    }
}
