package com.tiny.demo.firstlinecode.notification;

import android.app.Notification;
import android.service.notification.NotificationListenerService;
import android.service.notification.StatusBarNotification;

import com.tinytongtong.tinyutils.LogUtils;

/**
 * @Description: 监听通知消息
 * https://www.jianshu.com/p/981e7de2c7be
 * @Author wangjianzhou@qding.me
 * @Date 2019-08-27 17:47
 * @Version
 */
public class CustomNotificationListenerService extends NotificationListenerService {
    public static final String TAG = CustomNotificationListenerService.class.getSimpleName();

    /**
     * 当有新通知到来时会回调
     *
     * @param sbn
     */
    @Override
    public void onNotificationPosted(StatusBarNotification sbn) {
        super.onNotificationPosted(sbn);
        LogUtils.INSTANCE.e(TAG, "onNotificationPosted");
        String sbnPkgName = sbn.getPackageName();
        LogUtils.INSTANCE.e(TAG, sbnPkgName);
        // 如果该通知的包名不是微信，那么 pass 掉
        if (!"com.tencent.mm".equals(sbn.getPackageName())) {
            return;
        }
        Notification notification = sbn.getNotification();
        if (notification == null) {
            return;
        }

    }

    /**
     * 当有通知移除时会回调
     *
     * @param sbn
     */
    @Override
    public void onNotificationRemoved(StatusBarNotification sbn) {
        super.onNotificationRemoved(sbn);
        LogUtils.INSTANCE.e(TAG, "onNotificationRemoved");
    }

    /**
     * 当 NotificationListenerService 是可用的并且和通知管理器连接成功时回调
     */
    @Override
    public void onListenerConnected() {
        super.onListenerConnected();
        LogUtils.INSTANCE.e(TAG, "onListenerConnected");
    }
}
