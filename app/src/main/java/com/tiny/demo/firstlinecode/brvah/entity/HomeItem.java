package com.tiny.demo.firstlinecode.brvah.entity;

/**
 * Desc:
 * Created by tiny on 2017/12/10.
 * Version:
 */

public class HomeItem {
    private String title;
    private Class<?> activity;
    private int imageResource;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Class<?> getActivity() {
        return activity;
    }

    public void setActivity(Class<?> activity) {
        this.activity = activity;
    }

    public int getImageResource() {
        return imageResource;
    }

    public void setImageResource(int imageResource) {
        this.imageResource = imageResource;
    }
}
