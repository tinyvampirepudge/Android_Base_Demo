package com.tiny.demo.firstlinecode.launcherbadge;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;

/**
 * 华为机型的桌面角标设置管理类
 *
 * @author wangjianzhou@qding.me
 * @version v4.5.0
 * @date 2018/7/31 14:50
 * modify by:
 * modify date:
 * modify content:
 */

public class BadgeNumberManagerHuaWei {

    /**
     * 设置应用的桌面角标，已在一些华为手机上测试通过,但是无法保证在所有华为手机上都生效
     *
     * @param context context
     * @param number  角标显示的数字
     */
    public static void setBadgeNumber(Context context, int number) {
        try {
            if (number < 0) number = 0;
            Bundle bundle = new Bundle();
            bundle.putString("package", context.getPackageName());
            String launchClassName = context.getPackageManager().getLaunchIntentForPackage(context.getPackageName()).getComponent().getClassName();
            bundle.putString("class", launchClassName);
            bundle.putInt("badgenumber", number);
            context.getContentResolver().call(Uri.parse("content://com.huawei.android.launcher.settings/badge/"), "change_badge", null, bundle);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
