package com.tiny.demo.firstlinecode.common.bean;

/**
 * HTTP请求返回对象, 列表
 *
 * Created by wuhenzhizao on 2015/3/15.
 * ydl rename
 */
public class ResBean<T> extends ResBaseBean {
    private T data;

    public T getData() {
        return data;
    }

    public void setData(T t) {
        this.data = t;
    }
}
