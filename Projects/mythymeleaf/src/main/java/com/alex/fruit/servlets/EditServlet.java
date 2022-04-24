package com.alex.fruit.servlets;

import java.io.FileFilter;
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
import com.alex.utils.StringUtil;


@WebServlet("/edit.do")
public class EditServlet extends ViewBaseServlet{

    private FruitDAO fruitDAO = new FruitDAOImpl();
    
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String  fidStr = req.getParameter("fid");
        if(!StringUtil.isEmpty(fidStr)){
            int fid = Integer.parseInt(fidStr);
            try {
                Fruit fruit = fruitDAO.getFruitByFid(JDBCUtils.getConnection(), fid);
                req.setAttribute("fruit", fruit);
                super.processTemplate("edit", req, resp);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

}
