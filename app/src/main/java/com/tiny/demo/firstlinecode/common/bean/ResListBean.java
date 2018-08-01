package com.tiny.demo.firstlinecode.common.bean;

import java.util.ArrayList;
import java.util.List;

/**
 * HTTP请求返回对象, 列表
 *
 * Created by wuhenzhizao on 2015/3/15.
 * ydl rename
 */
public class ResListBean<T> extends ResBaseBean {
    private List<T> tList;

    public List<T> getTList() {
        if (tList == null){
            tList = new ArrayList<>();
        }
        return tList;
    }

    public void setTList(List<T> tList) {
        this.tList = tList;
    }
}
