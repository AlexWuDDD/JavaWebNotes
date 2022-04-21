package com.alex.fruit.servlets;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.alex.fruit.dao.FruitDAO;
import com.alex.fruit.dao.impl.FruitDAOImpl;
import com.alex.fruit.pojo.Fruit;
import com.alex.myspringmvc.ViewBaseServlet;
import com.alex.utils.JDBCUtils;

//Servlet从3.0版本开始支持注解方式的注册
@WebServlet("/fruit")
public class IndexServlet extends ViewBaseServlet{


    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

        FruitDAO fruitDAO = new FruitDAOImpl();
        try{
            Connection conn = JDBCUtils.getConnection();
            List<Fruit> fruitList = fruitDAO.getFruitList(conn);
            //保存到session作用域
            HttpSession session = request.getSession();
            session.setAttribute("fruitList", fruitList);
        
            //此处的视图名称是fruit
            //那么thymeleaf会将这个 逻辑视图名称对应到 物理视图名称上去
            //逻辑视图名称：fruit
            //物理视图名称：view-prefix + 逻辑视图名称 + viw-suffix
            //所以真实的视图名称是  /fruit.html
            super.processTemplate("fruit", request, response);
        }
        catch(SQLException e){
            e.printStackTrace();
        }
        
    }
}
