package com.alex.demofilter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;

// @WebFilter("/demo01.do")
// @WebFilter("*.do") //通配符
public class Demo01Filter implements Filter{

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        
        System.out.println("hello A");
        //放行
        chain.doFilter(request, response);

        System.out.println("hello B");
        
    }

    @Override
    public void destroy() {

    }
    
}
