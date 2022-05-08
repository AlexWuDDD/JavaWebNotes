package com.alex.z_bookcity.filiters;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebFilter(urlPatterns = { "*.do", "*.html"},
    initParams = {
        @WebInitParam(name = "bypass", 
            value = "/bookcity/page.do?operate=page&page=/user/login,/bookcity/user.do?null")
    }
)
public class SessionFilter implements Filter{

    private List<String> bypassList = null;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        String bypass = filterConfig.getInitParameter("bypass");
        String[] bypassArr = bypass.split(",");
        bypassList = Arrays.asList(bypassArr);

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse resp = (HttpServletResponse) response;

        System.out.println("SessionFilter.doFilter() - req.getRequestURL():" + req.getRequestURL());
        System.out.println("SessionFilter.doFilter() - req.getRequestURI():" + req.getRequestURI());
        System.out.println("SessionFilter.doFilter() - req.getQueryString():" + req.getQueryString());

        String uri = req.getRequestURI();
        String queryString = req.getQueryString();
        String str = uri + "?" + queryString;
        if(bypassList.contains(str)){
            chain.doFilter(req, resp);
        }
        else{
            HttpSession session = req.getSession();
            Object currUserObj = session.getAttribute("currUser");
            if(currUserObj == null){
                resp.sendRedirect("page.do?operate=page&page=/user/login");
            }
            else{
                chain.doFilter(req, resp);
            }
        }
    }

    @Override
    public void destroy() {

    }
    
}
