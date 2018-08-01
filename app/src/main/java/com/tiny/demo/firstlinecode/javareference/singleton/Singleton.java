package com.tiny.demo.firstlinecode.javareference.singleton;

import com.coder.zzq.smartshow.toast.SmartToast;

/**
 * Desc:
 * Created by tiny on 2017/12/18.
 * Version:
 */

public class Singleton {
    //私有化构造方法，保证外部的类不能通过构造器来实例化
    private Singleton() {
    }

    private static class SingletonHolder {
        //单例变量
        private static Singleton instance = new Singleton();
    }

    public static Singleton getInstance() {
        return SingletonHolder.instance;
    }

    public void showToast() {
        SmartToast.show("猫了个咪");
    }
}
