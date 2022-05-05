package com.alex.bookcity.myssm.filters;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;

import com.alex.bookcity.myssm.trans.TransactionManager;

@WebFilter("*.do")
public class OpenSessionViewFilter implements Filter{


    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        
        try{
            TransactionManager.beginTrans();
            chain.doFilter(request, response);
            TransactionManager.commit();
        }
        catch(Exception e){
            e.printStackTrace();
            try {
                TransactionManager.rollback();
            } catch (SQLException e1) {
                // TODO Auto-generated catch block
                e1.printStackTrace();
            }
        }
             
        
    }

    @Override
    public void destroy() {

    }

}
