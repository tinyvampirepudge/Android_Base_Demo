package com.tiny.demo.firstlinecode.launcherbadge;

import android.app.Notification;
import android.app.NotificationManager;
import android.content.Context;
import android.support.v4.app.NotificationCompat;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 * 小米机型的桌面角标设置管理类
 *
 * @author wangjianzhou@qding.me
 * @version v4.5.0
 * @date 2018/7/31 12:55
 * modify by:
 * modify date:
 * modify content:
 */

public class BadgeNumberManagerXiaoMi {

    /**
     * 相邻的两次角标设置如果数字相同的话，好像下一次会不生效
     *
     * @param context
     * @param number
     */
    public static void setBadgeNumber(Context context, int number) {
        try {
            NotificationManager notificationManager = (NotificationManager) context.
                    getSystemService(Context.NOTIFICATION_SERVICE);
            Notification notification = new NotificationCompat.Builder(context)
                    .setSmallIcon(context.getApplicationInfo().icon)
                    .setWhen(System.currentTimeMillis())
                    .setContentTitle("推送标题")
                    .setContentText("我是推送内容")
                    .setTicker("ticker")
                    .setAutoCancel(true)
                    .build();

            /**
             * 反射修改
             */
            Field field = notification.getClass().getDeclaredField("extraNotification");
            Object extraNotification = field.get(notification);
            Method method = extraNotification.getClass().getDeclaredMethod("setMessageCount", int.class);
            method.invoke(extraNotification, number);

            notificationManager.notify(1000, notification);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
