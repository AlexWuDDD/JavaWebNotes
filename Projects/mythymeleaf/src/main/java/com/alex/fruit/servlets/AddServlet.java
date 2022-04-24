package com.alex.fruit.servlets;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alex.fruit.dao.FruitDAO;
import com.alex.fruit.dao.impl.FruitDAOImpl;
import com.alex.fruit.pojo.Fruit;
import com.alex.myspringmvc.ViewBaseServlet;
import com.alex.utils.JDBCUtils;

@WebServlet("/add.do")
public class AddServlet extends ViewBaseServlet{

    private FruitDAO fruitDAO = new FruitDAOImpl(); 
    
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //1.设置编码
        req.setCharacterEncoding("utf-8");

        //2.获取参数
        
        String fname = req.getParameter("fname");
        
        String priceStr = req.getParameter("price");
        int price = Integer.parseInt(priceStr);
        
        String fcountStr = req.getParameter("fcount"); 
        int fcount = Integer.parseInt(fcountStr);

        String remark = req.getParameter("remark");

        try {
            Fruit fruit = new Fruit();
            fruit.setFname(fname);
            fruit.setPrice(price);
            fruit.setFcount(fcount);
            fruit.setRemark(remark);
            boolean ret = fruitDAO.addFruit(JDBCUtils.getConnection(), fruit);
            System.out.println(ret?"添加成功":"添加失败");
            // super.processTemplate("fruit", req, resp);
            resp.sendRedirect("fruit");
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
