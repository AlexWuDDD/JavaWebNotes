package com.alex.fruit.dao;

import java.util.List;
import java.sql.Connection;
import com.alex.fruit.pojo.Fruit;

public interface FruitDao {
    // 查询库存列表
    List<Fruit> getFruitList(Connection conn);

    // 新增库存
    boolean addFruit(Connection conn, Fruit fruit);

    // 修改库存
    boolean updateFruit(Connection conn, Fruit fruit);

    // 根据名称查询特定库存
    Fruit getFruitByFname(Connection conn, String fname);

    // 删除特定库存记录
    boolean delFruit(Connection conn, String fname);
}
