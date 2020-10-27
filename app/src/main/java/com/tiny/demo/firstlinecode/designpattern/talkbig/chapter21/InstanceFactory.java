package com.tiny.demo.firstlinecode.designpattern.talkbig.chapter21;

import com.tinytongtong.tinyutils.LogUtils;

/**
 * @Description: 基于静态内部类的延迟初始化方案
 * @Author wangjianzhou@qding.me
 * @Date 2019-08-23 11:55
 * @Version
 */
public class InstanceFactory {
    public static final String TAG = InstanceFactory.class.getSimpleName();

    private InstanceFactory() {
        LogUtils.INSTANCE.e(TAG, "InstanceFactory constructor");
    }

    private static class InstanceHolder {
        public static InstanceFactory instance = new InstanceFactory();
    }

    public static InstanceFactory getInstance() {
        return InstanceHolder.instance;
    }

    private int value = 0;

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public void addValue() {
        this.value++;
    }
}
