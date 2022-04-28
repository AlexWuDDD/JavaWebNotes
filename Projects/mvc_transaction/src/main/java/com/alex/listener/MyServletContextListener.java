package com.alex.listener;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import com.alex.myspringmvc.BeanFactory;
import com.alex.myspringmvc.ClassPathXmlApplicationContext;


public class MyServletContextListener implements ServletContextListener {
    
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        System.out.println("Servlet上下文对象初始化被我监听到了......");
        BeanFactory beanFactory = new ClassPathXmlApplicationContext();
        ServletContext application = sce.getServletContext();
        application.setAttribute("beanFactory", beanFactory);

    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        System.out.println("Servlet上下文对象销毁的动作被我监听到了......");
    }
}
