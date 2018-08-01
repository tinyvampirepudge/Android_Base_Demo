package com.tiny.demo.firstlinecode.common.utils;

import android.content.SharedPreferences;

import com.tiny.demo.firstlinecode.app.FLCApplication;

/**
 * Created by wuhenzhizao on 2014/12/8.
 */
public class PreferencesUtils {
    private static final String NAME_SPACE = "T9";

    private static final String CHART_TYPE_KEY = "chartTypeKey";
    private static final String RESOLUTION_KEY = "resolutionKey";
    /**
     * charttype - 类型
     * 取值 realtime/5D/kline    分时、5日、K线三种
     * resolution - 分辨率
     * 取值1/5/15/30/60/D/W/M 1分钟、5分钟、30分钟、60分
     */
    private static final String DEFAULT_CHART_TYPE_VALUE = "realtime";
    private static final String DEFALULT_RESOLUTION_VALUE = "5";

    private static SharedPreferences preferences = FLCApplication.instance().getSharedPreferences(NAME_SPACE, FLCApplication.instance().MODE_PRIVATE);

    public static String getString(String key, String defaultValue) {
        return preferences.getString(key, defaultValue);
    }

    public static String getString(String key) {
        return getString(key, "");
    }

    public static boolean getBoolean(String key, boolean defaultValue) {
        return preferences.getBoolean(key, defaultValue);
    }

    public static boolean getBoolean(String key) {
        return getBoolean(key, false);
    }

    public static int getInt(String key, int defaultValue) {
        return preferences.getInt(key, defaultValue);
    }

    public static int getInt(String key) {
        return getInt(key, 0);
    }

    public static float getFloat(String key, float defaultValue) {
        return preferences.getFloat(key, defaultValue);
    }

    public static float getFloat(String key) {
        return getFloat(key, 0.0f);
    }

    public static long getLong(String key, long defaultValue) {
        return preferences.getLong(key, defaultValue);
    }

    public static long getLong(String key) {
        return getLong(key, 0L);
    }

    public static void setString(String key, String value) {
        preferences.edit().putString(key, value).commit();
    }

    public static void setBoolean(String key, boolean value) {
        preferences.edit().putBoolean(key, value).commit();
    }

    public static void setInt(String key, int value) {
        preferences.edit().putInt(key, value).commit();
    }

    public static void setFloat(String key, float value) {
        preferences.edit().putFloat(key, value).commit();
    }

    public static void setLong(String key, long value) {
        preferences.edit().putLong(key, value).commit();
    }


    //region chart记忆功能
    public static void setChartType(String value) {
        preferences.edit().putString(CHART_TYPE_KEY, value).apply();
        ;
    }

    public static String getChartType() {
        return getString(CHART_TYPE_KEY, DEFAULT_CHART_TYPE_VALUE);
    }

    public static void setResolution(String value) {
        preferences.edit().putString(RESOLUTION_KEY, value).apply();
        ;
    }

    public static String getResolution() {
        return getString(RESOLUTION_KEY, DEFALULT_RESOLUTION_VALUE);
    }

    //endregion


}
