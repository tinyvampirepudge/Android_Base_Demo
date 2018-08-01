package com.tiny.demo.firstlinecode.common.utils;


import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.TimeZone;

/**
 * Created by tiny on 16/11/25.
 * 日期相关工具类
 */
public class DateUtils {
    /**
     * 取当前日期：2016-11-25
     *
     * @return
     */
    public static String getCurrentDate() {
        final Calendar c = Calendar.getInstance();
        c.setTimeZone(TimeZone.getTimeZone("GMT+8:00"));

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        return sdf.format(c.getTime());
    }

    /**
     * 获取当前的年份 2016
     *
     * @return
     */
    public static String getCurrentYear() {
        return getCurrentDate().substring(0, 4);
    }

    /**
     * 获取当前月份
     *
     * @return
     */
    public static String getCurrentMonth() {
        return getCurrentDate().substring(5, 7);
    }

    /**
     * 获取当前的日。
     *
     * @return
     */
    public static String getCurrentDay() {
        return getCurrentDate().substring(8);
    }

    /**
     * 取当前日期之前或者之后间隔几个月的日期
     *
     * @param gapMonths
     * @param isAhead
     * @return
     */
    public static String getDateGapMonths(int gapMonths, boolean isAhead) {
        Calendar c = Calendar.getInstance();
        c.setTimeZone(TimeZone.getTimeZone("GMT+8:00"));

        if (!isAhead) {
            c.add(Calendar.MONTH, gapMonths);
        } else {
            c.add(Calendar.MONTH, -gapMonths);
        }
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

        return sdf.format(c.getTime());
    }

    /**
     * 取当前日期之前或者之后间隔几天的日期
     *
     * @param gapDays
     * @param isAhead
     * @return
     */
    public static String getDateGapDays(int gapDays, boolean isAhead) {
        Calendar c = Calendar.getInstance();
        c.setTimeZone(TimeZone.getTimeZone("GMT+8:00"));

        if (!isAhead) {
            c.add(Calendar.DAY_OF_MONTH, gapDays);
        } else {
            c.add(Calendar.DAY_OF_MONTH, -gapDays);
        }
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

        return sdf.format(c.getTime());
    }

    /**
     * 取指定日期之前或者之后间隔几个月的日期
     *
     * @param year
     * @param month
     * @param day
     * @param gapMonths
     * @param isAhead
     * @return
     */
    public static String getSpecDateGapMonths(int year, int month, int day, int gapMonths, boolean isAhead) {
        Calendar c = Calendar.getInstance();
        c.setTimeZone(TimeZone.getTimeZone("GMT+8:00"));
        c.set(year, month - 1, day);

        if (!isAhead) {
            c.add(Calendar.MONTH, gapMonths);
        } else {
            c.add(Calendar.MONTH, -gapMonths);
        }
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

        return sdf.format(c.getTime());
    }

    /**
     * 获取某个月的最大天数
     *
     * @param year
     * @param month
     * @return
     */
    public static int getDaysOfMonth(int year, int month) {
        Calendar c = Calendar.getInstance();
        c.setTimeZone(TimeZone.getTimeZone("GMT+8:00"));
        c.set(Calendar.YEAR, year);
        c.set(Calendar.MONTH, month - 1);
        //最好设置日期，默认设置的是当前日期，不然2月份的日期会出错。
        c.set(Calendar.DAY_OF_MONTH, 1);
        int maxDays = c.getActualMaximum(c.DAY_OF_MONTH);    //获取本月最大天数
        return maxDays;
    }
}
