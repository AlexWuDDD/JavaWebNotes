package com.alex.qqzone.pojo;

import java.sql.Date;

public class UserDetail {
    
    private Integer id;
    private String realName;
    private String tel;
    private String email;
    private Date birth;
    private String star;

    public UserDetail(){}

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getRealName() {
        return this.realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public String getTel() {
        return this.tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Date getBirth() {
        return this.birth;
    }

    public void setBirth(Date birth) {
        this.birth = birth;
    }

    public String getStar() {
        return this.star;
    }

    public void setStar(String star) {
        this.star = star;
    }
}


//父类 java.util.Date 年月日时分秒毫秒
//子类 java.sql.Date 年月日
//子类 java.sql.Time 时分秒

