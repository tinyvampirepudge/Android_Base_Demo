package com.tiny.demo.firstlinecode.common.utils;

import android.app.ActivityManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Build;
import android.text.TextUtils;

import java.util.List;

/**
 * Created by wuhenzhizao on 2015/3/29.
 */
public class AppUtils {

    /**
     * 判断程序是否在运行
     *
     * @param context
     * @param packageName
     * @return 栈顶Activity
     */
    public static String isAppRunning(Context context, String packageName) {
        ActivityManager activityManager = (ActivityManager) context.getSystemService(context.ACTIVITY_SERVICE);
        List<ActivityManager.RunningTaskInfo> runningTaskInfos = activityManager.getRunningTasks(200);
        for (ActivityManager.RunningTaskInfo info : runningTaskInfos) {
            if (info.topActivity.getPackageName().equals(packageName)
                    && info.baseActivity.getPackageName().equals(packageName)) {
                return info.topActivity.getClassName();
            }
        }
        return null;
    }

    public static boolean isServiceRunning(Context context, String serviceName) {
        ActivityManager activityManager = (ActivityManager) context.getSystemService(context.ACTIVITY_SERVICE);
        List<ActivityManager.RunningServiceInfo> serviceInfos = activityManager.getRunningServices(1000);
        for (ActivityManager.RunningServiceInfo info : serviceInfos) {
            if (info.service.getClassName().equals(serviceName)) {
                return true;
            }
        }
        return false;
    }

    public static void startApp(Context context, String packageName) {
        try {
            PackageManager packageManager = context.getPackageManager();
            Intent intent = packageManager.getLaunchIntentForPackage(packageName);
            context.startActivity(intent);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void resumeActivityFromBackground(Context context, String className) {
        try {
            Intent intent = new Intent();
            intent.setAction(Intent.ACTION_MAIN);
            intent.addCategory(Intent.CATEGORY_LAUNCHER);
            intent.setComponent(new ComponentName(context, Class.forName(className)));
            intent.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT
                    | Intent.FLAG_ACTIVITY_NEW_TASK
                    | Intent.FLAG_ACTIVITY_RESET_TASK_IF_NEEDED);
            context.startActivity(intent);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    /**
     * 获取 app版本名称
     *
     * @param context
     * @return
     * @throws Exception
     */
    public static String getVersionName(Context context) throws Exception {
        // 获取packagemanager的实例
        PackageManager packageManager = context.getPackageManager();
        // getPackageName()是你当前类的包名，0代表是获取版本信息
        PackageInfo packInfo = packageManager.getPackageInfo(context.getPackageName(), 0);
        String version = packInfo.versionName;
        return version;
    }

    /**
     * 获取app版本号
     *
     * @param context
     * @return
     * @throws Exception
     */
    public static int getVersionCode(Context context) throws Exception {
        PackageManager packageManager = context.getPackageManager();
        PackageInfo packInfo = packageManager.getPackageInfo(context.getPackageName(), 0);
        return packInfo.versionCode;
    }


    /**
     * 是否在后台运行
     * @param context   上下文
     * @param pkgName   包名
     * @return
     */
    public static boolean isAppInBackgroundInternal(Context context, String pkgName) {
        if (TextUtils.isEmpty(pkgName)) {
            return false;
        }
        ActivityManager manager = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
        if (Build.VERSION.SDK_INT > Build.VERSION_CODES.LOLLIPOP) {
            List<ActivityManager.RunningAppProcessInfo> runningProcesses = manager.getRunningAppProcesses();
            if (!ListUtils.isEmpty(runningProcesses)) {
                for (ActivityManager.RunningAppProcessInfo runningProcess : runningProcesses) {
                    if (runningProcess.importance == ActivityManager.RunningAppProcessInfo.IMPORTANCE_FOREGROUND) {
                        return false;
                    }
                }
            }
        } else {
            List<ActivityManager.RunningTaskInfo> task = manager.getRunningTasks(1);
            if (!ListUtils.isEmpty(task)) {
                ComponentName info = task.get(0).topActivity;
                if (null != info) {
                    return !pkgName.equals(info.getPackageName());
                }
            }
        }
        return true;
    }

}
