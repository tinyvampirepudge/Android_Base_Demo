package com.tiny.demo.firstlinecode.launcherbadge;

import android.content.Context;
import android.content.Intent;

/**
 * vivo机型的桌面角标设置管理类
 *
 * @author wangjianzhou@qding.me
 * @version v4.5.0
 * @date 2018/8/1 15:56
 * modify by:
 * modify date:
 * modify content:
 */
public class BadgeNumberManagerVivo {

    public static void setBadgeNumber(Context context, int number) {
        try {
            Intent intent = new Intent("launcher.action.CHANGE_APPLICATION_NOTIFICATION_NUM");
            intent.putExtra("packageName", context.getPackageName());
            String launchClassName = context.getPackageManager().getLaunchIntentForPackage(context.getPackageName()).getComponent().getClassName();
            intent.putExtra("className", launchClassName);
            intent.putExtra("notificationNum", number);
            context.sendBroadcast(intent);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
