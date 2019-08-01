package com.tinytongtong.tinyutils;

import android.app.Activity;
import android.content.Context;
import android.util.DisplayMetrics;

import java.util.Locale;

/**
 * @Description: 屏幕工具类
 * @Author wangjianzhou
 * @Date 2019-08-01 11:39
 * @Version
 */
public class ScreenUtils {

    /**
     * 根据手机的分辨率从 dp 的单位 转成为 px(像素)
     */
    public static int dip2px(Context context, float dpValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }

    /**
     * 根据手机的分辨率从 px(像素) 的单位 转成为 dp
     */
    public static int px2dip(Context context, float pxValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (pxValue / scale + 0.5f);
    }

    public static float getDensity(Context context) {
        return context.getResources().getDisplayMetrics().density;
    }

    /**
     * 根据手机的分辨率从 px(像素) 的单位 转成为 sp
     */
    public static int px2sp(Context context, float pxValue) {
        float fontScale = context.getResources().getDisplayMetrics().scaledDensity;
        return (int) (pxValue / fontScale + 0.5f);
    }

    /**
     * 根据手机的分辨率从 sp 的单位 转成为 px
     */
    public static int sp2px(Context context, float spValue) {
        float fontScale = context.getResources().getDisplayMetrics().scaledDensity;
        return (int) (spValue * fontScale + 0.5f);
    }

    /**
     * 获取dialog宽度
     */
    public static int getDialogW(Context context) {
        DisplayMetrics dm = context.getResources().getDisplayMetrics();
        int w = dm.widthPixels - 100;
        return w;
    }

    /**
     * 获取屏幕宽度
     */
    public static int getScreenW(Context context) {
        DisplayMetrics dm = context.getResources().getDisplayMetrics();
        int w = dm.widthPixels;
        return w;
    }

    /**
     * 获取屏幕高度
     */
    public static int getScreenH(Context context) {
        DisplayMetrics dm = context.getResources().getDisplayMetrics();
        int h = dm.heightPixels;
        return h;
    }

    /**
     * 获取DisplayMetrics相关信息
     *
     * @param context
     * @return
     */
    public static String getDisplayMetricsInfo(Context context) {
        if (context == null || context.getResources() == null || context.getResources().getDisplayMetrics() == null) {
            return "无法获取";
        }

        StringBuffer sb = new StringBuffer("DisplayMetrics:\n");

        DisplayMetrics displayMetrics = context.getResources().getDisplayMetrics();
        float heightPixels = displayMetrics.heightPixels;
        float widthPixels = displayMetrics.widthPixels;
        float density = displayMetrics.density;
        int densityDpi = displayMetrics.densityDpi;
        float scaledDensity = displayMetrics.scaledDensity;
        // 屏幕尺寸，单位inch
        double screenSize = Math.sqrt((Math.pow(heightPixels, 2) + Math.pow(widthPixels, 2))) / densityDpi;

        sb.append(String.format(Locale.getDefault(), "heightPixels: %.2fpx", heightPixels));
        sb.append("\n");
        sb.append(String.format(Locale.getDefault(), "widthPixels: %.2fpx", widthPixels));
        sb.append("\n");
        sb.append(String.format(Locale.getDefault(), "density: %.2fpx", density));
        sb.append("\n");
        sb.append(String.format(Locale.getDefault(), "densityDpi: %dpx", densityDpi));
        sb.append("\n");
        sb.append(String.format(Locale.getDefault(), "scaledDensity: %.2fpx", scaledDensity));
        sb.append("\n");
        sb.append(String.format(Locale.getDefault(), "screenSize(计算得出的): %.2finch", screenSize));

        return sb.toString();
    }
}
