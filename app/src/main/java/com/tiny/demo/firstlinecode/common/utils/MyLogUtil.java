package com.tiny.demo.firstlinecode.common.utils;

import android.util.Log;

/**
 * Created by 87959 on 2017/5/29.
 */

public class MyLogUtil {
    public static final int VERBOSE = 1;
    public static final int DEBUG = 2;
    public static final int INFO = 3;
    public static final int ERROR = 4;
    public static final int WARN = 5;
    public static int level = 6;

    private static final String TAG = "First_Line_Code";
    // 是否打印日志标志
    private static boolean isOpenLog = true;

    public static void openLog(boolean flag) {
        isOpenLog = flag;
    }

    //携带tag的方法
    public static void v(String tag, String msg) {
        if (isOpenLog && level <= VERBOSE) {
            Log.v(tag, msg);
        }
    }

    public static void d(String tag, String msg) {
        if (isOpenLog && level <= DEBUG) {
            Log.d(tag, msg);
        }
    }

    public static void i(String tag, String msg) {
        if (isOpenLog && level <= INFO) {
            Log.i(tag, msg);
        }
    }

    public static void w(String tag, String msg) {
        if (isOpenLog && level <= WARN) {
            Log.w(tag, msg);
        }
    }

    public static void e(String tag, String msg) {
        if (isOpenLog && level <= ERROR) {
            Log.e(tag, msg);
        }
    }

    //不携带tag,使用这里的默认tag
    public static void v(String msg) {
        if (isOpenLog && level <= VERBOSE) {
            Log.v(TAG, msg);
        }
    }

    public static void d(String msg) {
        if (isOpenLog && level <= DEBUG) {
            Log.d(TAG, msg);
        }
    }

    public static void i(String msg) {
        if (isOpenLog && level <= INFO) {
            Log.i(TAG, msg);
        }
    }

    public static void w(String msg) {
        if (isOpenLog && level <= WARN) {
            Log.w(TAG, msg);
        }
    }

    public static void e(String msg) {
        if (isOpenLog && level <= ERROR) {
            Log.e(TAG, msg);
        }
    }
}
