package com.alex.bookcity.myssm.ioc;

import java.io.InputStream;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;


import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import com.alex.bookcity.myssm.utils.StringUtil;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class ClassPathXmlApplicationContext implements BeanFactory{

    private Map<String, Object> beanMap = new HashMap<>();
    // private String path = "applicationContect.xml";
    public ClassPathXmlApplicationContext(){
        this("applicationContext.xml");
    }

    public ClassPathXmlApplicationContext(String path){

        if(StringUtil.isEmpty(path)){
            throw new RuntimeException("IOC容器的配置文件没有指定...");
        }

        try {

            InputStream inputStream = this.getClass().getClassLoader().getResourceAsStream(path);
            //1. 创建DocumentBuilderFactory对象
            DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
        
            //2. 创建DocumentBuilder对象
            DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();

            //3. 创建Document对象
            Document document = documentBuilder.parse(inputStream);

            //4.获取所有的bean节点
            NodeList beanNodeList = document.getElementsByTagName("bean");

            for(int i = 0; i < beanNodeList.getLength(); ++i){
                Node beanNode =  beanNodeList.item(i);
                if(beanNode.getNodeType() == Node.ELEMENT_NODE){
                    Element beanElement = (Element)beanNode;
                    String beanId = beanElement.getAttribute("id");
                    String className = beanElement.getAttribute("class");
                    
                    Class<?> beanClass = Class.forName(className);
                    //创建bean实例
                    Object beanObj = beanClass.getDeclaredConstructor().newInstance();
                    //将bean实例对象保存到map容易中
                    beanMap.put(beanId, beanObj);
                    //到目前为止，此处需要注意的是， bean和bean之间的配置关系
                }
            }

            //5. 组装bean之间的依赖关系
            for(int i = 0; i< beanNodeList.getLength(); ++i){
                Node beanNode =  beanNodeList.item(i);
                if(beanNode.getNodeType() == Node.ELEMENT_NODE){
                    Element beanElement = (Element)beanNode;
                    String beanId = beanElement.getAttribute("id");
                    
                    NodeList beanChildNodeList = beanElement.getChildNodes();
                    for(int j = 0; j< beanChildNodeList.getLength(); ++j){
                        Node beanChildNode = beanChildNodeList.item(j);
                        if(beanChildNode.getNodeType() == Node.ELEMENT_NODE && "property".equals(beanChildNode.getNodeName())){
                            Element propertyElement = (Element)beanChildNode;
                            String propertyName =  propertyElement.getAttribute("name");
                            String propertyRef = propertyElement.getAttribute("ref");
                            //1 找到propertyRef对应的实例
                            Object refObj = beanMap.get(propertyRef);
                            //2. 将refObj设置到当前bean对应的property属性上去
                            Object beanObj = beanMap.get(beanId);

                            Class<?> beanClass = beanObj.getClass();
                            Field propertyField = beanClass.getDeclaredField(propertyName);
                            propertyField.setAccessible(true);
                            propertyField.set(beanObj, refObj);

                        }
                    }
                }
            }
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public Object getBean(String id) { 
        return beanMap.get(id);
    }
    
}
