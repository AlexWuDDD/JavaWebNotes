package com.alex.trans;

import java.sql.Connection;
import java.sql.SQLException;

import com.alex.utils.JDBCUtils;

public class TransactionManager {


    //开启事务
    public static void beginTrans() throws SQLException{
        
        Connection conn = JDBCUtils.getConnection();
        conn.setAutoCommit(false);
        System.out.println("开启事务");
    }

    //提交事务
    public static void commit() throws SQLException{
       
        Connection conn = JDBCUtils.getConnection();
        conn.commit();
        JDBCUtils.CloseConnection();
        System.out.println("提交事务");
        
    }    

    //回滚事务
    public static void rollback() throws SQLException{
        Connection conn = JDBCUtils.getConnection();
        conn.rollback();
        JDBCUtils.CloseConnection();
        System.out.println("回滚事务");
    }

}
