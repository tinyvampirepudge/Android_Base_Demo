package com.tiny.demo.firstlinecode.common.bean;

/**
 * HTTP请求返回对象
 *
 * Created by wuhenzhizao on 2015/3/15.
 * ydl rename
 */
public class ResBaseBean {
    private int code;

    private String msg;

    public boolean isSuccess() {
        return (code == 10000);
    }

    public void setCode(int c) { code = c;}

    public int getCode() { return code; }

    public void setMsg(String m) { msg = m; }

    public String getMsg() {
        return msg;
    }

    public void setException() {
        code = 10001;
        msg = "系统繁忙，请稍后再试1";
    }

    public void setSuccess() {
        code = 10000;
        msg = "success";
    }
}
