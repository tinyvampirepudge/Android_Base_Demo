package com.tiny.demo.firstlinecode.designpattern.talkbig.chapter21;

import com.tinytongtong.tinyutils.LogUtils;

/**
 * Desc:    懒汉式单例
 * Created by tiny on 2017/10/19.
 * Version:
 */

public class SingletonLhs {
    private static SingletonLhs instance;

    private SingletonLhs() {
        //init
    }

    public static SingletonLhs getInstance() {
        if (instance == null) {
            synchronized (SingletonLhs.class) {
                if (instance == null) {
                    instance = new SingletonLhs();
                }
            }
        }
        return instance;
    }

    public void showInfo() {
        LogUtils.INSTANCE.e("instance.toString() --> " + instance.toString());
    }
}
