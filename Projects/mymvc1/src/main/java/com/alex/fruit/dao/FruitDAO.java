package com.alex.fruit.dao;


import java.util.List;
import java.sql.Connection;
import com.alex.fruit.pojo.Fruit;

public interface FruitDAO {
    // 查询库存列表
    List<Fruit> getFruitList(Connection conn);

    //获取指定页码上的库存列表信息
    List<Fruit> getFruitList(Connection conn, int pageSize, int pageNumber, String keyword);

    //根据fid获取特定的水果库存信息
    Fruit getFruitByFid(Connection conn, int fid);

    // 新增库存
    boolean addFruit(Connection conn, Fruit fruit);

    // 修改库存
    boolean updateFruit(Connection conn, Fruit fruit);

    // 根据名称查询特定库存
    Fruit getFruitByFname(Connection conn, String fname);

    // 删除特定库存记录
    boolean delFruit(Connection conn, String fname);
    boolean delFruit(Connection conn, int fid);

    //查询库存总记录条数
    int getFruitCount(Connection conn, String keyword);
}
