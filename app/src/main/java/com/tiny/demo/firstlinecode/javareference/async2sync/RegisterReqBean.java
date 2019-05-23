package com.tiny.demo.firstlinecode.javareference.async2sync;

/**
 * @Description: 注册请求的bean
 * @Author wangjianzhou@qding.me
 * @Date 2019-05-23 09:08
 * @Version
 */
public class RegisterReqBean {
    private String name;
    private String phone;
    private String pwd;

    public RegisterReqBean() {
    }

    public RegisterReqBean(String name, String phone, String pwd) {
        this.name = name;
        this.phone = phone;
        this.pwd = pwd;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    @Override
    public String toString() {
        return "RegisterReqBean{" +
                "name='" + name + '\'' +
                ", phone='" + phone + '\'' +
                ", pwd='" + pwd + '\'' +
                '}';
    }
}
