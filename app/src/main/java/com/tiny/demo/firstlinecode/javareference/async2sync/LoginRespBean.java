package com.tiny.demo.firstlinecode.javareference.async2sync;

/**
 * @Description: 登录响应的数据
 * @Author wangjianzhou@qding.me
 * @Date 2019-05-23 09:26
 * @Version
 */
public class LoginRespBean {
    private String name;
    private String pwd;
    private String msg;

    public LoginRespBean() {
    }

    public LoginRespBean(String name, String pwd, String msg) {
        this.name = name;
        this.pwd = pwd;
        this.msg = msg;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    @Override
    public String toString() {
        return "LoginRespBean{" +
                "name='" + name + '\'' +
                ", pwd='" + pwd + '\'' +
                ", msg='" + msg + '\'' +
                '}';
    }
}
