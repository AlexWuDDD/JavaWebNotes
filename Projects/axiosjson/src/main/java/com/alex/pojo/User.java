package com.alex.pojo;

public class User {
  private String uname;
  private String pwd;

  public User(){}

  public User(String uname, String pwd) {
    this.uname = uname;
    this.pwd = pwd;
  }

  public String getUname() {
    return this.uname;
  }

  public void setUname(String uname) {
    this.uname = uname;
  }

  public String getPwd() {
    return this.pwd;
  }

  public void setPwd(String pwd) {
    this.pwd = pwd;
  }

  @Override
  public String toString() {
    return "{" +
      " uname='" + getUname() + "'" +
      ", pwd='" + getPwd() + "'" +
      "}";
  }
  

}
