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
        String fname = request.getParameter("fname");
        String priceStr = request.getParameter("price");
        Integer price = Integer.parseInt(priceStr);
        String fcountStr = request.getParameter("fcount");
        Integer fcount = Integer.parseInt(fcountStr);
        String remark = request.getParameter("remark");

        FruitDao fruitDao = new FruitDaoImpl();
        try (Connection conn = JDBCUtils.getConnection()) {
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