package com.alex.fruit.servlets;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alex.fruit.dao.FruitDAO;
import com.alex.fruit.dao.impl.FruitDAOImpl;
import com.alex.myspringmvc.ViewBaseServlet;
import com.alex.utils.JDBCUtils;
import com.alex.utils.StringUtil;

@WebServlet("/del.do")
public class DelServlet extends ViewBaseServlet{

    private FruitDAO fruitDAO = new FruitDAOImpl();
    
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        
        String fidStr = req.getParameter("fid");
        if(!StringUtil.isEmpty(fidStr)){
            int fid = Integer.parseInt(fidStr);
            try {
                boolean ret = fruitDAO.delFruit(JDBCUtils.getConnection(), fid);
                System.out.println(ret?"删除成功":"删除失败");

                resp.sendRedirect("fruit");
            } catch (SQLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }

        }
    }
}
