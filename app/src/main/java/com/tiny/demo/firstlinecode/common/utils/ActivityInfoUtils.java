package com.tiny.demo.firstlinecode.common.utils;

import android.app.ActivityManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;

import java.util.List;

/**
 * Desc: Activity的工具类
 *
 * @author tiny
 * @date 2018/6/1 下午4:40
 */
public class ActivityInfoUtils {
    public static final String TAG = ActivityInfoUtils.class.getSimpleName();

    /**
     * 判断Activity是否启动过。
     *
     * @param context
     * @param clazz
     * @return
     */
    public static boolean activityOnceCreated(Context context, Class clazz) {
        boolean result = false;
        Intent intent = new Intent(context, clazz);
        ComponentName cmpName = intent.resolveActivity(context.getPackageManager());
        if (cmpName != null) { // 说明系统中存在这个activity
            ActivityManager am = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
            List<ActivityManager.RunningTaskInfo> taskInfoList = am.getRunningTasks(20);
            LogUtils.e(TAG, "taskInfoList.size:" + taskInfoList.size());
            for (ActivityManager.RunningTaskInfo taskInfo : taskInfoList) {
                LogUtils.e(TAG, "taskInfo:" + taskInfo.baseActivity);
                if (taskInfo.baseActivity.equals(cmpName)) { // 说明它已经启动了
                    result = true;
                    break;
                }
            }
        }
        return result;
    }
}
