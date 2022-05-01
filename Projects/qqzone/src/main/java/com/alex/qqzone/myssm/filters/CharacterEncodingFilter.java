package com.alex.qqzone.myssm.filters;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.http.HttpServletRequest;

import com.alex.qqzone.myssm.utils.StringUtil;

@WebFilter(urlPatterns = {"*.do"},initParams = {@WebInitParam(name = "encoding",value = "UTF-8")})
public class CharacterEncodingFilter implements Filter{

    private String encoding = "UTF-8";

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        String encodingStr =  filterConfig.getInitParameter("encoding");
        if(!StringUtil.isEmpty(encodingStr)){
            encoding = encodingStr;
        }

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        
        ((HttpServletRequest)request).setCharacterEncoding(encoding);      
        chain.doFilter(request, response);       
        
    }

    @Override
    public void destroy() {

    }

}