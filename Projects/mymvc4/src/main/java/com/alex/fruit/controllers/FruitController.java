package com.alex.fruit.controllers;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.alex.fruit.dao.FruitDAO;
import com.alex.fruit.dao.impl.FruitDAOImpl;
import com.alex.fruit.pojo.Fruit;
import com.alex.utils.JDBCUtils;
import com.alex.utils.StringUtil;

public class FruitController {

    private FruitDAO fruitDAO = new FruitDAOImpl();

    private String index(String oper, String keyword, Integer pageNo, HttpServletRequest request) {

        HttpSession session = request.getSession();

        if(pageNo == null){
            pageNo = 1;
        }

        if (!StringUtil.isEmpty(oper) && "search".equals(oper)) {
            pageNo = 1;
            if (StringUtil.isEmpty(keyword)) {
                keyword = "";
            }
            session.setAttribute("keyword", keyword);
        } else {
            Object keywordObj = session.getAttribute("keyword");

            if (keywordObj != null) {
                keyword = (String) keywordObj;
            } else {
                keyword = "";
            }
        }

        session.setAttribute("pageNo", pageNo);

        FruitDAO fruitDAO = new FruitDAOImpl();
        try {
            Connection conn = JDBCUtils.getConnection();
            // List<Fruit> fruitList = fruitDAO.getFruitList(conn);
            List<Fruit> fruitList = fruitDAO.getFruitList(conn, 2, pageNo, keyword);

            // 保存到session作用域

            session.setAttribute("fruitList", fruitList);

            int fruitCount = fruitDAO.getFruitCount(JDBCUtils.getConnection(), keyword);
            int pageCount = (fruitCount + 2 - 1) / 2;
            session.setAttribute("pageCount", pageCount);

            return "fruit";
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    private String add(String fname, Integer price, Integer fcount, String remark) {

        try {
            Fruit fruit = new Fruit();
            fruit.setFname(fname);
            fruit.setPrice(price);
            fruit.setFcount(fcount);
            fruit.setRemark(remark);
            boolean ret = fruitDAO.addFruit(JDBCUtils.getConnection(), fruit);
            System.out.println(ret ? "添加成功" : "添加失败");
            // super.processTemplate("fruit", req, resp);
            // resp.sendRedirect("fruit.do");
            return "redirect:fruit.do";
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return null;
    }

    private String del(Integer fid) {
        try {
            boolean ret = fruitDAO.delFruit(JDBCUtils.getConnection(), fid);
            System.out.println(ret ? "删除成功" : "删除失败");

            // resp.sendRedirect("fruit.do");
            return "redirect:fruit.do";
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return null;
    }

    private String edit(Integer fid, HttpServletRequest req) {
        try {
            Fruit fruit = fruitDAO.getFruitByFid(JDBCUtils.getConnection(), fid);
            req.setAttribute("fruit", fruit);
            // super.processTemplate("edit", req, resp);
            return "edit";
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    private String update(Integer fid, String fname, Integer price, Integer fcount, String remark) {

        try {
            boolean ret = fruitDAO.updateFruit(JDBCUtils.getConnection(), new Fruit(fid, fname, price, fcount, remark));
            System.out.println(ret ? "修改成功" : "修改失败");
            // super.processTemplate("fruit", req, resp);
            return "redirect:fruit.do";
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return null;

    }
}