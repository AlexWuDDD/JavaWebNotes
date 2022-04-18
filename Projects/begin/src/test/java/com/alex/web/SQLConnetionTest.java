package com.alex.web;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.util.Properties;

import javax.sql.DataSource;

import com.alibaba.druid.pool.DruidDataSourceFactory;

import org.junit.Test;

public class SQLConnetionTest {
    
    @Test
    public void test(){
        InputStream is = null;
        try{
            Properties pros = new Properties();

            is = ClassLoader.getSystemClassLoader().getResource("druid.properties").openStream();
            
            pros.load(is);
            DataSource source =  DruidDataSourceFactory.createDataSource(pros);
            Connection conn = source.getConnection();
            assertNotEquals(null, conn);
            System.err.println(conn);
        }
        catch(Exception e){
            e.printStackTrace();
            // assertNotEquals(null, is);
            try {
                is.close();
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        }
    }
}
