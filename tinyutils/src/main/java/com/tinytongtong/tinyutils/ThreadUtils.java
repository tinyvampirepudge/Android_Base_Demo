package com.tinytongtong.tinyutils;

import android.os.Looper;

/**
 * @Description: Thread相关的工具类
 *
 * @Author wangjianzhou
 * @Date 2019-08-01 11:39
 * @Version
 */
public class ThreadUtils {

    /**
     * 验证是否是主线程
     */
    public static void validateMainThread() {
        if (Looper.getMainLooper() != Looper.myLooper()) {
            throw new IllegalStateException("Must be called from the main thread.");
        }
    }

    /**
     * 打印当前Thread的名称
     */
    public static void logCurrThreadName() {
        logCurrThreadName("");
    }

    public static void logCurrThreadName(String name) {
        if (Thread.currentThread() == Looper.getMainLooper().getThread()) {
            LogUtils.e(name + ": main Thread,name --> " + Thread.currentThread().getName());
        } else {
            LogUtils.e(name + ": sub Thread,name --> " + Thread.currentThread().getName());
        }
    }

    /**
     * 当前线程是否是主线程
     *
     * @return
     */
    public static boolean isMainThread() {
        return Thread.currentThread() == Looper.getMainLooper().getThread();
    }
}
