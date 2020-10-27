package com.tiny.demo.firstlinecode.designpattern.talkbig.chapter21;

import com.tinytongtong.tinyutils.LogUtils;

/**
 * Desc:    饿汉式单例
 * Created by tiny on 2017/10/19.
 * Version:
 */

public class SingletonEhs {
    private static final SingletonEhs instance = new SingletonEhs();

    private SingletonEhs() {
    }

    public static SingletonEhs getInstance() {
        return instance;
    }

    public void showInfo() {
        LogUtils.INSTANCE.e("instance.toString() --> " + instance.toString());
    }
}
