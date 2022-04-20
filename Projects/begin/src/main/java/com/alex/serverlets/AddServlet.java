package com.alex.serverlets;

import java.io.IOException;
import java.sql.Connection;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alex.fruit.dao.FruitDao;
import com.alex.fruit.dao.impl.FruitDaoImpl;
import com.alex.fruit.pojo.Fruit;
import com.alex.util.JDBCUtils;

public class AddServlet extends HttpServlet{

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException{
        //post方式下，设置编码，防止中文乱码
        //get方式目前不需要设置编码（基于tomcat8)
        /*
        //如果是get请求发送的中文数据，转码有点麻烦（tomcat8z之前）
        String fanme = request.getParameter("fname");
        //1. 将字符串打散成字节数据
        byte[] bytes = fanme.getBytes("ISO-8859-1");
        //2. 将字节数据按照设定的编码重新组装成字符串
        fanme = new String(bytes,"UTF-8");
        */
        //必须放在第一行
        request.setCharacterEncoding("UTF-8");

        String fname = request.getParameter("fname");
        String priceStr = request.getParameter("price");
        Integer price = Integer.parseInt(priceStr);
        String fcountStr = request.getParameter("fcount");
        Integer fcount = Integer.parseInt(fcountStr);
        String remark = request.getParameter("remark");

        FruitDao fruitDao = new FruitDaoImpl();
        try {
            Connection conn = JDBCUtils.getConnection();
            boolean flag = fruitDao.addFruit(conn, new Fruit(0, fname, price, fcount, remark));

            System.out.println(flag ? "添加成功" : "添加失败");
        } catch (IOException e) {
            throw e;
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

}