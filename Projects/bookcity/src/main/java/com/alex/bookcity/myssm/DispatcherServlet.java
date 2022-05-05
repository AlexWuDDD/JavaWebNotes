package com.alex.bookcity.myssm;

import java.io.IOException;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alex.bookcity.myssm.ioc.BeanFactory;
import com.alex.bookcity.myssm.utils.StringUtil;


@WebServlet("*.do")
public class DispatcherServlet extends ViewBaseServlet{

    private BeanFactory beanFactory = null;
    public void init() throws ServletException {
        super.init();
        // beanFactory = new ClassPathXmlApplicationContext();
        ServletContext application = getServletContext();
        Object beanFactoryObj = application.getAttribute("beanFactory");
        if(beanFactoryObj != null){
            beanFactory = (BeanFactory)beanFactoryObj;
        }
        else{
            throw new RuntimeException("IOC容器创建失败");
        }
    }

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{


        // /hello.do -> hello -> HelloController
        String servletPath = request.getServletPath();
        // System.out.println("servletPath = " + servletPath);

       // 1. hello.do -> hello
        servletPath =  servletPath.substring(1);
        int lastDotIndex = servletPath.lastIndexOf(".do");
        servletPath = servletPath.substring(0, lastDotIndex);

        System.out.println("servletPath = " + servletPath);

        // 2. hello -> HelloController
        Object controllerBeanObj = beanFactory.getBean(servletPath);

        String operate = request.getParameter("operate");
        if(StringUtil.isEmpty(operate)){
            operate = "index";
        }

        try {
            Method[] methods = controllerBeanObj.getClass().getDeclaredMethods();
            for(Method method : methods){
                if(operate.equals(method.getName())){
                    //1. 统一获取请求参数

                    //获取当前方法的参数，返回参数数组
                    Parameter[] paremeters = method.getParameters();
                    //parameterValues 用来承载参数的值
                    Object[] parameterValues = new Object[paremeters.length];
                    for(int i = 0; i < paremeters.length; ++i){
                        Parameter parameter = paremeters[i];
                        String parameterName = parameter.getName();
                        if("request".equals(parameterName)){
                            parameterValues[i] = request;
                        }
                        else if("response".equals(parameterName)){
                            parameterValues[i] = response;
                        }
                        else if("session".equals(parameterName)){
                            parameterValues[i] = request.getSession();
                        }
                        else{
                            String parameterValue = request.getParameter(parameter.getName());
                            String typeName = parameter.getType().getName();
                            Object parameterObj = parameterValue;
                            if(parameterValue != null){
                                if("java.lang.Integer".equals(typeName)){
                                    parameterObj = Integer.parseInt(parameterValue);
                                }
                            }
                            parameterValues[i] = parameterObj;
                        }

                    }

                    //2.controller组件中的方法调用
                    method.setAccessible(true);
                    Object returnObj = method.invoke(controllerBeanObj, parameterValues);
                    if(returnObj == null){
                        System.out.println("returnObj is null");
                    }
                    else{
                        //3.视图处理
                        String methodReturnStr = (String)returnObj;
                        if(methodReturnStr.startsWith("redirect:")){ //比如 redirect:fruit.do
                            String redirectStr = methodReturnStr.substring("redirect:".length());
                            response.sendRedirect(redirectStr);
                        }
                        else{
                            super.processTemplate(methodReturnStr, request, response); //edit
                        }
                    }
                }
            }
        } catch (Exception e){
            e.printStackTrace();
            throw new DispatcherServletException("DispatcherServlet出错了...");
        }
    }
}
