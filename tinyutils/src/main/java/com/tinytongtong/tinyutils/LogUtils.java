package com.tinytongtong.tinyutils;

import android.util.Log;


/**
 * @Description: 日志管理类
 *
 * @Author wangjianzhou
 * @Date 2019-08-01 11:37
 * @Version
 */
public class LogUtils {
    private static final String TAG = "tiny_module";
    // 是否打印日志标志
    private static boolean isOpenLog = true;

    public static void openLog(boolean flag) {
        isOpenLog = flag;
    }

    public static boolean isOpenLog() {
        return isOpenLog;
    }

    // 打印调试信息
    public static void d(String log) {
        if (isOpenLog) Log.d(TAG, log);
    }

    // 打印调试信息
    public static void d(String tag, String log) {
        if (isOpenLog) Log.d(tag, log);
    }

    // 打印调试信息
    public static void i(String tag, String log) {
        if (isOpenLog) {
            log = log == null ? "" : log;
            Log.i(tag, log);
        }
    }

    // 打印错误信息
    public static void e(String log) {
        if (isOpenLog) Log.e(TAG, log);
    }

    public static void e(String tag, String log) {
        if (isOpenLog) Log.e(tag, log);
    }

    public static void e(String tag, String msg, Throwable tr) {
        if (isOpenLog) Log.e(tag, msg, tr);
    }

    public static void i(String log) {
        if (isOpenLog) Log.i(TAG, log);
    }

    public static void w(String log) {
        if (isOpenLog) Log.w(TAG, log);
    }
}
