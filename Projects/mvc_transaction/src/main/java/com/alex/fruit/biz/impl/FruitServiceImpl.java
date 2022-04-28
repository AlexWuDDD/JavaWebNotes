package com.alex.fruit.biz.impl;

import java.sql.SQLException;
import java.util.List;

import com.alex.fruit.biz.FruitService;
import com.alex.fruit.dao.FruitDAO;
import com.alex.fruit.pojo.Fruit;
import com.alex.utils.JDBCUtils;

public class FruitServiceImpl implements FruitService{

    private FruitDAO fruitDAO = null;

    @Override
    public List<Fruit> getFruitList(String keyword, Integer pageNo) {
        try {
            return fruitDAO.getFruitList(JDBCUtils.getConnection(), 2, pageNo, keyword);
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void addFruit(Fruit fruit) {
        try {
            fruitDAO.addFruit(JDBCUtils.getConnection(), fruit);
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        
    }

    @Override
    public Fruit getFruitByFid(Integer fid) {
        try {
            return fruitDAO.getFruitByFid(JDBCUtils.getConnection(), fid);
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void deleteFruitByFid(Integer fid) {
        try {
            fruitDAO.delFruit(JDBCUtils.getConnection(), fid);
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        
    }

    @Override
    public Integer getTotalPage(String keyword) {
        try {
            int fruitCount = fruitDAO.getFruitCount(JDBCUtils.getConnection(), keyword);
            return (fruitCount + 2 - 1) / 2;
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void updateFruit(Fruit fruit) {
       try {
            fruitDAO.updateFruit(JDBCUtils.getConnection(), fruit);
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
    
}
