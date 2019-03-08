package com.tiny.demo.firstlinecode.filter;

import com.tiny.demo.firstlinecode.common.bean.ResBaseBean;

/**
 * @Description: Filter
 * @Author wangjianzhou@qding.me
 * @Date 2019/3/8 5:40 PM
 * @Version
 */
public class FilterBean {
    private String value;

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public FilterBean(String value) {

        this.value = value;
    }
}
