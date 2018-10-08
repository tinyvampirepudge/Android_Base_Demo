package com.tiny.demo.firstlinecode.launcherbadge;

import android.os.Build;
import android.text.TextUtils;

/**
 * 手机型号
 * 现在只支持小米、华为，后期可扩展。
 *
 * @author wangjianzhou@qding.me
 * @version v4.5.0
 * @date 2018/7/31 9:53
 * modify by:
 * modify date:
 * modify content:
 */
public class MobileBrand {

    //MIUI标识
    public static final String BRAND_MIUI = "xiaomi";

    //EMUI标识
    public static final String BRAND_EMUI1 = "huawei";
    public static final String BRAND_EMUI2 = "honor";

    //oppo标识
    public static final String BRAND_OPPO = "oppo";

    //vivo标识
    public static final String BRAND_VIVO = "vivo";

    //魅族标识
    public static final String BRAND_FLYME = "meizu";

    //1+手机表示
    public static final String BRAND_ONEPLUS = "oneplus";

    /**
     * 获取手机的brand
     *
     * @return
     */
    public static String getBrand() {
        return Build.BRAND;
    }

    /**
     * 是否是小米手机
     *
     * @return
     */
    public static boolean isXiaoMi() {
        if (!TextUtils.isEmpty(getBrand())) {
            if (getBrand().toLowerCase().contains(BRAND_MIUI)) {
                return true;
            }
        }
        return false;
    }

    /**
     * 是否是华为手机
     *
     * @return
     */
    public static boolean isHuawei() {
        if (!TextUtils.isEmpty(getBrand())) {
            if (getBrand().toLowerCase().contains(BRAND_EMUI1) || getBrand().toLowerCase().contains(BRAND_EMUI2)) {
                return true;
            }
        }
        return false;
    }

    /**
     * 是否是vivo手机
     *
     * @return
     */
    public static boolean isVivo() {
        if (!TextUtils.isEmpty(getBrand())) {
            if (getBrand().toLowerCase().contains(BRAND_VIVO)) {
                return true;
            }
        }
        return false;
    }

    /**
     * 是否是oppo手机
     *
     * @return
     */
    public static boolean isOppo() {
        if (!TextUtils.isEmpty(getBrand())) {
            if (getBrand().toLowerCase().contains(BRAND_OPPO)) {
                return true;
            }
        }
        return false;
    }
}
