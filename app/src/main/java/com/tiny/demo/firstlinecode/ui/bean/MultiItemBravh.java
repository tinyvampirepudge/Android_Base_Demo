package com.tiny.demo.firstlinecode.ui.bean;

import com.chad.library.adapter.base.entity.MultiItemEntity;

/**
 * Desc:
 * Created by tiny on 2017/12/13.
 * Version:
 */

public class MultiItemBravh implements MultiItemEntity {

    public static final int TYPE1 = 1;
    public static final int TYPE2 = 2;
    public static final int TYPE3 = 3;

    public static final int SPAN_1 = 1;
    public static final int SPAN_2 = 2;
    public static final int SPAN_3 = 3;

    private String name;
    private int type;
    private int spanType;

    public MultiItemBravh(String name, int type, int spanType) {
        this.name = name;
        this.type = type;
        this.spanType = spanType;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getSpanType() {
        return spanType;
    }

    public void setSpanType(int spanType) {
        this.spanType = spanType;
    }

    @Override
    public int getItemType() {
        return getType();
    }
}
