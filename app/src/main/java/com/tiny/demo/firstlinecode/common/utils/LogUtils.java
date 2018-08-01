package com.tiny.demo.firstlinecode.common.utils;

import android.util.Log;


/**
 * 类描述：日志管理类
 *
 * @author tiny
 * @desc 2017-03-01
 */
public class LogUtils {
    private static final String TAG = "FLC";
    // 是否打印日志标志
    private static boolean isOpenLog = true;

    public static void openLog(boolean flag) {
        isOpenLog = flag;
    }

    // 打印调试信息
    public static void d(String log) {
        if (isOpenLog) Log.d(TAG, log);
    }

    // 打印调试信息
    public static void d(String tag, String log) {
        if (isOpenLog) Log.d(tag, log);
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

    public static void i(String tag, String log) {
        if (isOpenLog) Log.i(tag, log);
    }

    public static void i(String log) {
        if (isOpenLog) Log.i(TAG, log);
    }

    public static void w(String tag, String log) {
        if (isOpenLog) Log.w(tag, log);
    }

    public static void w(String log) {
        if (isOpenLog) Log.w(TAG, log);
    }

    /**
     * 打印佛祖头像
     */
    public static void buddha() {
        i("                            _ooOoo_");
        i("                           o8888888o");
        i("                          88\" . \"88");
        i("                           (| -_- |)");
        i("                            O\\ = /O");
        i("                        ____/`---'\\____");
        i("                      .   ' \\| |// `.");
        i("                       / \\\\||| : |||// \\");
        i("                     / _||||| -:- |||||- \\");
        i("                       | | \\\\\\ - /// | |");
        i("                     | \\_| ''\\---/'' | |");
        i("                      \\ .-\\__ `-` ___/-. /");
        i("                   ___`. .' /--.--\\ `. . __");
        i("                .\"\" '< `.___\\_<|>_/___.' >'\"\".");
        i("               | | : `- \\`.;`\\ _ /`;.`/ - ` : | |");
        i("                 \\ \\ `-. \\_ __\\ /__ _/ .-` / /");
        i("         ======`-.____`-.___\\_____/___.-`____.-'======");
        i("                            `=---='");
        i("         .............................................");
        i("                            信佛祖，无bug");
        i("                           delete bug ...");
    }
}
