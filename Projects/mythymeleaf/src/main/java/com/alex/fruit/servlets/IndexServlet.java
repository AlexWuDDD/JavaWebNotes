package com.alex.fruit.servlets;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.alex.fruit.dao.FruitDAO;
import com.alex.fruit.dao.impl.FruitDAOImpl;
import com.alex.fruit.pojo.Fruit;
import com.alex.myspringmvc.ViewBaseServlet;
import com.alex.utils.JDBCUtils;
import com.alex.utils.StringUtil;

//Servlet从3.0版本开始支持注解方式的注册
@WebServlet("/fruit")
public class IndexServlet extends ViewBaseServlet{

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

        request.setCharacterEncoding("UTF-8");
        HttpSession session = request.getSession();

        Integer pageNo = 1;
        String oper = request.getParameter("oper");

        //如果oper!=null 说明 通过表单的查询按钮点击过来的
        //如果oper!=null 说明 不是通过表单的查询按钮点击过来的

        String keyword = null;

        if(!StringUtil.isEmpty(oper) && "search".equals(oper)){
            //说明是点击表单查询发送过来的的请求
            //此时，pageNo应该还原为1， keyword应该从请求参数中获取
            pageNo = 1;
            keyword = request.getParameter("keyword");
            if(StringUtil.isEmpty(keyword)){
                keyword = "";
            }
            session.setAttribute("keyword", keyword);
        }
        else{
            //说明此处不是点击表单查询发送过来的
            //此时keyword应该从session作用域获取
            String pageNoStr = request.getParameter("pageNo");
            if(!StringUtil.isEmpty(pageNoStr)){
                pageNo = Integer.parseInt(pageNoStr);
            }
            Object keywordObj = session.getAttribute("keyword");

            if(keywordObj != null){
                keyword = (String) keywordObj;
            }
            else{
                keyword = "";
            }
        }


        
        session.setAttribute("pageNo", pageNo);


        FruitDAO fruitDAO = new FruitDAOImpl();
        try{
            Connection conn = JDBCUtils.getConnection();
            // List<Fruit> fruitList = fruitDAO.getFruitList(conn);
            List<Fruit> fruitList = fruitDAO.getFruitList(conn, 2, pageNo, keyword);

            //保存到session作用域

            session.setAttribute("fruitList", fruitList);

            int fruitCount = fruitDAO.getFruitCount(JDBCUtils.getConnection(), keyword);
            int pageCount = (fruitCount+2-1) / 2;
            session.setAttribute("pageCount", pageCount);
        
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
