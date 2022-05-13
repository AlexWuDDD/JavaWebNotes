package com.alex.axiosjson;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alex.pojo.User;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

@WebServlet("/axios02.do")
public class Axios02Servlet extends HttpServlet{

  @Override
  public void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    req.setCharacterEncoding("utf-8");
    StringBuffer stringBuffer = new StringBuffer("");
    BufferedReader bufferedReader =  req.getReader();
    String str = null;
    while((str=bufferedReader.readLine())!=null){
      stringBuffer.append(str);
    }
    str = stringBuffer.toString();
    System.out.println(str);

    //已知String, 需要转换成Java Object
    Gson gson = new GsonBuilder().create();
    User user = gson.fromJson(str, User.class);
    System.out.println(user);

    //假设user是从数据库查询出来的，或在需要将其转换成json格式的字符串，然后响应给客户端
    String json = gson.toJson(user);
    resp.setCharacterEncoding("UTF-8");
    //MIME
    resp.setContentType("application/json;charset=utf-8");
    PrintWriter out = resp.getWriter();
    out.print(json);
  
  }

}