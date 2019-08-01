package com.tinytongtong.tinyutils;

import android.text.TextUtils;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * @Description: 日期相关工具类
 *
 * @Author wangjianzhou
 * @Date 2019-08-01 11:38
 * @Version
 */
public class DateUtils {

    /**
     * 英文简写（默认）如：2010-12-01
     */
    public static String FORMAT_SHORT = "yyyy-MM-dd";

    /**
     * 英文全称  如：2010-12-01 23:15:06
     */
    public static String FORMAT_LONG = "yyyy-MM-dd HH:mm:ss";

    /**
     * 精确到毫秒的完整时间    如：yyyy-MM-dd HH:mm:ss.S
     */
    public static String FORMAT_FULL = "yyyy-MM-dd HH:mm:ss.S";

    /**
     * 中文简写  如：2010年12月01日
     */
    public static String FORMAT_SHORT_CN = "yyyy年MM月dd";

    /**
     * 中文全称  如：2010年12月01日  23时15分06秒
     */
    public static String FORMAT_LONG_CN = "yyyy年MM月dd日  HH时mm分ss秒";

    /**
     * 精确到毫秒的完整中文时间
     */
    public static String FORMAT_FULL_CN = "yyyy年MM月dd日  HH时mm分ss秒SSS毫秒";


    /**
     * 获取当前时间
     *
     * @param format 时间格式   yyyy-MM-dd HH:mm:ss
     * @return 2018-01-05 23:27:51
     */
    public static String getCurrDateStr(String format) {
        SimpleDateFormat df = new SimpleDateFormat(format);
        Calendar calendar = Calendar.getInstance();
        return df.format(calendar.getTime());
    }

    /**
     * 时间戳转换成日期格式字符串
     *
     * @param format      格式
     * @param millSeconds 毫秒级别的时间戳
     * @return
     */
    public static String millSeconds2DateStr(String format, String millSeconds) {
        if (TextUtils.isEmpty(format) || TextUtils.isEmpty(millSeconds)) {
            return "";
        }
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        return sdf.format(new Date(Long.valueOf(millSeconds)));
    }
}
