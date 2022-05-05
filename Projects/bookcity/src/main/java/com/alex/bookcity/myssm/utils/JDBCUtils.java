package com.alex.bookcity.myssm.utils;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

import javax.sql.DataSource;

import com.alibaba.druid.pool.DruidDataSourceFactory;

public class JDBCUtils {

    private static ThreadLocal<Connection> threadLocal = new ThreadLocal<>();

    // 使用Druid
    private static DataSource source;
    static {
        try {
            Properties pros = new Properties();

            InputStream is = JDBCUtils.class.getClassLoader().getResourceAsStream("druid.properties");

            pros.load(is);
            source = DruidDataSourceFactory.createDataSource(pros);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static Connection creatConnection() throws SQLException{
        return source.getConnection();
    }

    public static Connection getConnection() throws SQLException {
        
        Connection conn = threadLocal.get();
        if(null == conn){
            conn = creatConnection();
            threadLocal.set(conn);
        }
        return threadLocal.get();
    }

    public static void CloseConnection() throws SQLException {
        Connection conn = threadLocal.get();
        if(null != conn && !conn.isClosed()){
            conn.close();
            threadLocal.remove();
        }
    }

    /**
     * @Description:关闭数据库的连接
     * @param conn
     * @param ps
     */

    public static void closeResource(Connection conn, Statement ps) {
        try {
            if (ps != null) {
                ps.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            if (conn != null) {
                conn.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void closeResource(Connection conn, Statement ps, ResultSet rs) {

        try {
            if (rs != null) {
                rs.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            if (ps != null) {
                ps.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            if (conn != null) {
                conn.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void closeResource(Statement ps, ResultSet rs) {

        try {
            if (rs != null) {
                rs.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            if (ps != null) {
                ps.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
