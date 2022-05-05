package com.alex.bookcity.myssm.basedao;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import com.alex.bookcity.myssm.utils.JDBCUtils;

public abstract class BaseDAO<T> {

    private PreparedStatement psmt = null ;
    private ResultSet rs = null;
    private Connection conn = null;

    //T的Class对象
    private Class<T> clazz = null ;
    {
        // 获取当前的BaseDAO的子类中的泛型
        Type genericSuperclass = this.getClass().getGenericSuperclass();
        ParameterizedType paremType = (ParameterizedType) genericSuperclass;
        Type[] actualTypeArguments = paremType.getActualTypeArguments();
        clazz = (Class<T>)actualTypeArguments[0];
    }

    protected Connection getConn(){
        try {
            return JDBCUtils.getConnection();
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return null;
    }

    protected void close(ResultSet rs , PreparedStatement psmt , Connection conn){
        JDBCUtils.closeResource(psmt, rs);
    }

    //给预处理命令对象设置参数
    private void setParams(PreparedStatement psmt , Object... params) throws SQLException {
        if(params!=null && params.length>0){
            for (int i = 0; i < params.length; i++) {
                psmt.setObject(i+1,params[i]);
            }
        }
    }

    //执行更新，返回影响行数
    public int executeUpdate(String sql , Object... params){
        boolean insertFlag = false ;
        insertFlag = sql.trim().toUpperCase().startsWith("INSERT");
        conn = getConn();
        try {
            if(insertFlag){
                psmt = getConn().prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);
            }else {
                psmt = getConn().prepareStatement(sql);
            }
            setParams(psmt,params);
            int count = psmt.executeUpdate() ;

            if(insertFlag){
                rs = psmt.getGeneratedKeys();
                if(rs.next()){
                    return ((Long)rs.getLong(1)).intValue();
                }
            }   
            return count ;
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            close(rs,psmt,conn);
        }
        return 0;
    }

    //通过反射技术给obj对象的property属性赋propertyValue值
    private void setValue(Object obj ,  String property , Object propertyValue) throws ClassNotFoundException, NoSuchMethodException, SecurityException, InstantiationException, IllegalArgumentException, InvocationTargetException{
        Class<?> clazz = obj.getClass();
        try {
            //获取property这个字符串对应的属性名 ， 比如 "fid"  去找 obj对象中的 fid 属性
            Field field = clazz.getDeclaredField(property);
            if(field!=null){
                //获取当前字段的类型名称
                String typeName = field.getType().getName();
                //判断如果是自定义类型，则需要调用这个自定义类型的第一个参数的构造方法，创建出这个自定义的实例对象，然后将实例对象赋值给这个属性
                if(isMyType(typeName)){
                    //假设typeName是com.alex.qqzone.pojo.UserBasic
                    Class<?> typeNameClass = Class.forName(typeName);
                    Constructor<?> constructor = typeNameClass.getDeclaredConstructor(java.lang.Integer.class);
                    propertyValue =  constructor.newInstance(propertyValue);
                }
                field.setAccessible(true);
                field.set(obj,propertyValue);
            }
        } catch (NoSuchFieldException | IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    private static boolean isNotMyType(String typename){
        return "java.lang.Integer".equals(typename) 
            || "java.lang.String".equals(typename)
            || "java.util.Date".equals(typename)
            || "java.sql.Date".equals(typename)
            || "java.time.LocalDateTime".equals(typename);
    }

    private static boolean isMyType(String typeName){
        return !isNotMyType(typeName);
    }

    //执行复杂查询，返回例如统计结果
    public Object[] executeComplexQuery(String sql , Object... params){
        conn = getConn();
        try {
            psmt = conn.prepareStatement(sql);
            setParams(psmt,params);
            rs = psmt.executeQuery();

            //通过rs可以获取结果集的元数据
            //元数据：描述结果集数据的数据 , 简单讲，就是这个结果集有哪些列，什么类型等等

            ResultSetMetaData rsmd = rs.getMetaData();
            //获取结果集的列数
            int columnCount = rsmd.getColumnCount();
            Object[] columnValueArr = new Object[columnCount];
            //6.解析rs
            if(rs.next()){
                for(int i = 0 ; i<columnCount;i++){
                    Object columnValue = rs.getObject(i+1);     //33    苹果      5
                    columnValueArr[i]=columnValue;
                }
                return columnValueArr ;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            close(rs,psmt,conn);
        }
        return null ;
    }

    //执行查询，返回单个实体对象
    public T load(String sql , Object... params){
        conn = getConn();
        try {
            psmt = conn.prepareStatement(sql);
            setParams(psmt,params);
            rs = psmt.executeQuery();

            //通过rs可以获取结果集的元数据
            //元数据：描述结果集数据的数据 , 简单讲，就是这个结果集有哪些列，什么类型等等

            ResultSetMetaData rsmd = rs.getMetaData();
            //获取结果集的列数
            int columnCount = rsmd.getColumnCount();
            //6.解析rs
            if(rs.next()){
                T entity = (T)clazz.getDeclaredConstructor().newInstance();

                for(int i = 0 ; i<columnCount;i++){
                    String columnName = rsmd.getColumnName(i+1);            //fid   fname   price
                    Object columnValue = rs.getObject(i+1);     //33    苹果      5
                    setValue(entity,columnName,columnValue);
                }
                return entity ;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch(Exception e){
            e.printStackTrace();
        } 
        finally {
            close(rs,psmt,conn);
        }
        return null ;
    }


    //执行查询，返回List
    public List<T> executeQuery(String sql , Object... params){
        List<T> list = new ArrayList<>();
        conn = getConn();
        try {
            psmt = conn.prepareStatement(sql);
            setParams(psmt,params);
            rs = psmt.executeQuery();

            //通过rs可以获取结果集的元数据
            //元数据：描述结果集数据的数据 , 简单讲，就是这个结果集有哪些列，什么类型等等

            ResultSetMetaData rsmd = rs.getMetaData();
            //获取结果集的列数
            int columnCount = rsmd.getColumnCount();
            //6.解析rs
            while(rs.next()){
                T entity = (T)clazz.getDeclaredConstructor().newInstance();

                for(int i = 0 ; i<columnCount;i++){
                    String columnName = rsmd.getColumnName(i+1);            //fid   fname   price
                    Object columnValue = rs.getObject(i+1);     //33    苹果      5
                    setValue(entity,columnName,columnValue);
                }
                list.add(entity);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (Exception e){
            e.printStackTrace();
        } 
        finally {
            close(rs,psmt,conn);
        }
        return list ;
    }
}
