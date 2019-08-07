package com.tiny.demo.firstlinecode.ui.bean;

import java.io.Serializable;

/**
 *  实体类
 */

public class CategoryBean implements Serializable {
    private static final long serialVersionUID = 5136218664701666396L;

    private String tag;//所属的分类
    private String categoryName;

    public CategoryBean(String tag, String categoryName) {
        this.tag = tag;
        this.categoryName = categoryName;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }
}
