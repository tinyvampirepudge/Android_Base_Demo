package com.tiny.demo.firstlinecode.launcherbadge;

import android.content.Context;

/**
 * 桌面角标的管理类
 * 现在适配的是华为和小米手机，其他类型暂不处理。
 *
 * @author wangjianzhou@qding.me
 * @version APP版本号（以修改为准）$version$
 * @date 2018/7/31 10:02
 * modify by:
 * modify date:
 * modify content:
 */
public class BadgeNumberManager {
    private static final BadgeNumberManager.Impl IMPL;
    private Context mContext;


    private BadgeNumberManager(Context context) {
        mContext = context;
    }

    public static BadgeNumberManager from(Context context) {
        return new BadgeNumberManager(context);
    }

    static {
        if (MobileBrand.isHuawei()) {
            IMPL = new ImplHuaWei();
        }
//        else if (MobileBrand.isXiaoMi()) {
//            IMPL = new ImplXiaoMi();
//        }
        else if (MobileBrand.isVivo()) {
            IMPL = new ImplVivo();
        } else {
            IMPL = new ImplOther();
        }
    }


    /**
     * 设置应用在桌面上显示的角标数字
     *
     * @param number 显示的数字
     */
    public void setBadgeNumber(int number) {
        if (number <= 0) {
            number = 0;
        }
        IMPL.setBadgeNumber(mContext, number);
    }

    interface Impl {

        /**
         * 设置桌面icon的消息树，大于0的数字可显示，0表示不显示。
         *
         * @param context
         * @param number
         */
        void setBadgeNumber(Context context, int number);

    }

    static class ImplHuaWei implements Impl {

        @Override
        public void setBadgeNumber(Context context, int number) {
            BadgeNumberManagerHuaWei.setBadgeNumber(context, number);
        }
    }

    static class ImplXiaoMi implements Impl {

        @Override
        public void setBadgeNumber(Context context, int number) {
            //小米机型的桌面应用角标API跟通知绑定在一起了，所以单独做处理
            BadgeNumberManagerXiaoMi.setBadgeNumber(context, number);
        }
    }

    static class ImplVivo implements Impl {

        @Override
        public void setBadgeNumber(Context context, int number) {
            BadgeNumberManagerVivo.setBadgeNumber(context, number);
        }
    }

    static class ImplOther implements Impl {

        @Override
        public void setBadgeNumber(Context context, int number) {
            //do nothing
        }
    }

}
