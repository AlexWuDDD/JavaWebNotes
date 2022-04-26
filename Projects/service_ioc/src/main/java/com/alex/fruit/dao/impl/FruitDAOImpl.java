package com.alex.fruit.dao.impl;

import java.sql.Connection;
import java.util.List;

import com.alex.fruit.dao.FruitDAO;
import com.alex.fruit.dao.base.BaseDAO;
import com.alex.fruit.pojo.Fruit;

public class FruitDAOImpl extends BaseDAO<Fruit> implements FruitDAO{
    @Override
    public List<Fruit> getFruitList(Connection conn) {
        String sql = "select * from t_fruit";
        return executeQuery(conn, sql);
        
    }

    @Override
    public List<Fruit> getFruitList(Connection conn, int pageSize, int pageNumber, String keyword) {
        String sql = "select * from t_fruit where fname like ? or remark like ? limit ?,?";
        return executeQuery(conn, sql, "%" + keyword + "%", "%"+keyword + "%", (pageNumber-1)*pageSize, pageSize);
    }

    @Override
    public Fruit getFruitByFid(Connection conn, int fid) {
        String sql = "select * from t_fruit where fid = ? " ;
        return executeQuery(conn, sql, fid).get(0);
    }

    @Override
    public boolean addFruit(Connection conn, Fruit fruit) {
        String sql = "insert into t_fruit(fname, price, fcount, remark) values(?,?,?,?)";
        int count = executeUpdate(conn, sql,fruit.getFname(),fruit.getPrice(),fruit.getFcount(),fruit.getRemark()) ;
        //insert语句返回的是自增列的值，而不是影响行数
        //System.out.println(count);
        return count>0;
    }

    @Override
    public boolean updateFruit(Connection conn, Fruit fruit) {
        String sql = "update t_fruit set fname=?, price=?, fcount=?, remark=? where fid = ? " ;
        return executeUpdate(conn, sql,fruit.getFname(), fruit.getPrice(), fruit.getFcount(), fruit.getRemark(), fruit.getFid())>0;
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

    @Override
    public boolean delFruit(Connection conn, int fid) {
        String sql = "delete from t_fruit where fid = ? " ;
        return executeUpdate(conn, sql, fid)>0;
    }

    @Override
    public int getFruitCount(Connection conn, String keyword) {
        String sql = "select count(*) from t_fruit where fname like ? or remark like ?";
        return ((Long)(executeComplexQuery(conn, sql, "%"+keyword+"%", "%"+keyword+"%")[0])).intValue();
    }

}
