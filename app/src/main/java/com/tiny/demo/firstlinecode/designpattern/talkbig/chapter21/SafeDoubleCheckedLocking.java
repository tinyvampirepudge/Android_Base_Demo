package com.tiny.demo.firstlinecode.designpattern.talkbig.chapter21;

/**
 * @Description: 基于双重检查锁定来实现延迟初始化
 * @Author wangjianzhou@qding.me
 * @Date 2019-08-23 11:52
 * @Version
 */
public class SafeDoubleCheckedLocking {
    private volatile static SafeDoubleCheckedLocking instance;

    private SafeDoubleCheckedLocking() {
    }

    public static SafeDoubleCheckedLocking getInstance() {
        if (instance == null) {
            synchronized (SafeDoubleCheckedLocking.class) {
                if (instance == null) {
                    instance = new SafeDoubleCheckedLocking();
                }
            }
        }
        return instance;
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
