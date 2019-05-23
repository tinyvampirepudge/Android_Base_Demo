package com.tiny.demo.firstlinecode.javareference.async2sync;

/**
 * @Description: 登录请求的数据
 * @Author wangjianzhou@qding.me
 * @Date 2019-05-23 09:23
 * @Version
 */
public class LoginReqBean {
    private String name;
    private String pwd;
    private String msg;

    public LoginReqBean() {
    }

    public LoginReqBean(String name, String pwd) {
        this.name = name;
        this.pwd = pwd;
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

    @Override
    public String toString() {
        return "LoginReqBean{" +
                "name='" + name + '\'' +
                ", pwd='" + pwd + '\'' +
                '}';
    }
}
