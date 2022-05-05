package com.alex.bookcity.myssm.utils;

public class StringUtil {

    //判断字符串是否为null
    public static boolean isEmpty(String str){
        if(str == null || "".equals(str.trim())){
            return true;
        }
        return false;
    }

    
}
