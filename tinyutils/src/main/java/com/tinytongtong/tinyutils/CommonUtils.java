package com.tinytongtong.tinyutils;



import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.os.Environment;
import android.preference.PreferenceManager;
import android.telephony.TelephonyManager;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.AbsoluteSizeSpan;
import android.text.style.RelativeSizeSpan;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.WindowManager;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.net.URLConnection;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Random;
import java.util.regex.Pattern;

/**
 * @Description: 字符串操作工具包
 *
 * @Author wangjianzhou
 * @Date 2019-08-01 11:38
 * @Version
 */
public class CommonUtils {
    private final static Pattern emailer = Pattern
            .compile("\\w+([-+.]\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*");
    //国内手机号：0?(13|14|15|18)[0-9]{9}
    private final static Pattern phone = Pattern
            .compile("^((14[0-9])|(13[0-9])|(17[0-9])|(15[^4,\\D])|(18[0,1-9]))\\d{8}$");
    public final static int MOBILE = 0;
    public final static int EMAIL = 1;
    public final static int ID_CARD = 2;
    public final static int NAME = 3;
    public final static int ADDR = 4;

    private final static ThreadLocal<SimpleDateFormat> dateFormater = new ThreadLocal<SimpleDateFormat>() {
        @Override
        protected SimpleDateFormat initialValue() {
            return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.CHINA);
        }
    };

    private final static ThreadLocal<SimpleDateFormat> dateFormater2 = new ThreadLocal<SimpleDateFormat>() {
        @Override
        protected SimpleDateFormat initialValue() {
            return new SimpleDateFormat("yyyy-MM-dd", Locale.CHINA);
        }
    };

    /**
     * 指定时间之前的所有日期
     * @param beginDate
     * @return
     */
    public static List<Long> getDatesBetweenTwoDate(Date beginDate) {
        Date endDate = new Date();
        List<Date> lDate = new ArrayList<Date>();
        List<Long> returnlist = new ArrayList<Long>();
        lDate.add(beginDate);// 把开始时间加入集合
        Calendar cal = Calendar.getInstance();
        // 使用给定的 Date 设置此 Calendar 的时间
        cal.setTime(beginDate);
        boolean bContinue = true;
        while (bContinue) {
            // 根据日历的规则，为给定的日历字段添加或减去指定的时间量
            cal.add(Calendar.DAY_OF_MONTH, 1);
            // 测试此日期是否在指定日期之后
            if (endDate.after(cal.getTime())) {
                lDate.add(cal.getTime());
            } else {
                break;
            }
        }

        for (int i = 0; i < lDate.size(); i++) {
            returnlist.add(lDate.get(i).getTime() / 1000L);
        }
        return returnlist;
    }


    /*
       * 检查是否存在sd卡
       */
    public static boolean hasSdcard() {
        String state = Environment.getExternalStorageState();
        return state.equals(Environment.MEDIA_MOUNTED);
    }

    /**
     * 获取当前时间的时间戳
     *
     * @return
     */
    public static String getCurrentTime() {
        return System.currentTimeMillis() + "";
    }

    public static long getLongCurrentTime() {
        return System.currentTimeMillis();
    }

    /**
     * 获取当前时间的Unix时间戳
     *
     * @return
     */
    public static String getCurrentTimeMillis() {
        return String.valueOf(System.currentTimeMillis() / 1000L);
    }


    /**
     * 根据时间戳转换日期
     *
     * @param mTimeStamp 时间戳
     * @return
     */
    public static String getLongTimeStamp(long mTimeStamp) {
        String strTimeDesc = getDate(mTimeStamp + "", "MM-dd HH:mm");
        return strTimeDesc;
    }

    public static String getLongTimeStamp2(long mTimeStamp) {
        String strTimeDesc = getDate(mTimeStamp + "", "MM月dd日 HH:mm");
        return strTimeDesc;
    }


    public static String getLongTimeStamp3(long mTimeStamp) {
        String strTimeDesc = getDate(mTimeStamp + "", "MM-dd HH:mm:ss");
        return strTimeDesc;
    }

    /**
     * 根据时间戳转换日期
     *
     * @param mTimeStamp 十位时间戳
     * @return
     */
    public static String getLongTimeDate(long mTimeStamp) {
        String strTimeDesc = getDate(mTimeStamp + "", "yyyy-MM-dd");
        return strTimeDesc;
    }

    /**
     * 返回当前系统时间
     */
    public static String getDataTime(String format) {
        SimpleDateFormat df = new SimpleDateFormat(format, Locale.CHINA);
        return df.format(new Date());
    }


    /**
     * 时间戳获取日期格式
     *
     * @param mTimeStamp 时间戳(以秒为单位的十位字符串)
     * @param pattern    格式化条件
     * @return
     */
    public static String getDate(String mTimeStamp, String pattern) {
        String strTimeDesc;
        SimpleDateFormat format = null;
//        int iTimeStamp = Integer.parseInt(mTimeStamp);
        long changeTime = Long.parseLong(mTimeStamp);
        long messageTimeStamp = changeTime * 1000;
        try {
            format = new SimpleDateFormat(pattern);
            strTimeDesc = format.format(messageTimeStamp);
        } catch (IllegalArgumentException exception) {
            strTimeDesc = "格式有误";
        }
        return strTimeDesc;
    }


    /**
     * 时间戳获取上下午时段
     *
     * @param mTimeStamp 时间戳( 以秒为单位的十位字符串)
     * @return
     */
    public static String getCourseNoon(String mTimeStamp) {
        String strTimeDesc = getDate(mTimeStamp, "HH:mm");
        if (Integer.parseInt(strTimeDesc.substring(0, 2)) < 12) {
            strTimeDesc = "上午";
        } else {
            strTimeDesc = "下午";
        }
        return strTimeDesc;
    }


    /**
     * 判断给定字符串时间是否为今日
     *
     * @param mTime 十位数字的字符串型时间戳
     * @return boolean
     */
    public static boolean isToday(String mTime) {
        boolean b = false;
        String nowDate = getDate(getCurrentTime().substring(0, 10), "yyyy-MM-dd");
        String timeDate = getDate(mTime, "yyyy-MM-dd");
        if (nowDate.equals(timeDate)) {
            b = true;
        }
        return b;
    }

    //导致TextView异常换行的原因：安卓默认半角字符不能为第一行以后每行的开头字符，因为数字、字母为半角字符
    //所以我们只需要将半角字符转换为全角字符即可，方法如下
    public static String ToSBC(String input) {
        char c[] = input.toCharArray();
        for (int i = 0; i < c.length; i++) {
            if (c[i] == ' ') {
                c[i] = '\u3000';
            } else if (c[i] < '\177') {
                c[i] = (char) (c[i] + 65248);
            }
        }
        return new String(c);
    }


    /**
     * 返回当前系统时间
     */
    public static String getDataTime() {
        return getDataTime("HH:mm");
    }

    /**
     * 毫秒值转换为mm:ss
     *
     * @param ms
     * @author kymjs
     */
    public static String timeFormat(int ms) {
        StringBuilder time = new StringBuilder();
        time.delete(0, time.length());
        ms /= 1000;
        int s = ms % 60;
        int min = ms / 60;
        if (min < 10) {
            time.append(0);
        }
        time.append(min).append(":");
        if (s < 10) {
            time.append(0);
        }
        time.append(s);
        return time.toString();
    }

    /**
     * 将字符串转位日期类型
     *
     * @return
     */
    public static Date toDate(String sdate) {
        try {
            return dateFormater.get().parse(sdate);
        } catch (ParseException e) {
            return null;
        }
    }


    /**
     * 判断给定字符串是否空白串 空白串是指由空格、制表符、回车符、换行符组成的字符串 若输入字符串为null或空字符串，返回true
     */
    public static boolean isEmpty(String input) {
        if (input == null || "".equals(input))
            return true;

        for (int i = 0; i < input.length(); i++) {
            char c = input.charAt(i);
            if (c != ' ' && c != '\t' && c != '\r' && c != '\n') {
                return false;
            }
        }
        return true;
    }

    /**
     * 判断是不是一个合法的电子邮件地址
     */
    public static boolean isEmail(String email) {
        if (email == null || email.trim().length() == 0)
            return false;
        return emailer.matcher(email).matches();
    }

    /**
     * 判断是不是一个合法的手机号码
     */
    public static boolean isPhone(String phoneNum) {
        if (phoneNum == null || phoneNum.trim().length() == 0)
            return false;
        return phone.matcher(phoneNum).matches();
    }

    /**
     * 检查字符串是否匹配对应规则
     *
     * @param str      待匹配的字符串
     * @param typeCode 规则类型
     * @return boolean
     * @author cxy
     */
    public static boolean isMatch(String str, int typeCode) {
        boolean isMatch = false;
        switch (typeCode) {
            case MOBILE://是否符合手机号格式
                isMatch = Pattern.compile("^((13[0-9])|(17[0-9])|(15[^4,\\D])|(18[0,5-9]))\\d{8}$").matcher(str).matches();
                break;
            case EMAIL://是否符合Email格式
                isMatch = Pattern.compile("\\w+([-+.]\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*").matcher(str).matches();
                break;
            case ID_CARD://是否符合身份证号码格式（15或18位）
                isMatch = Pattern.compile("^(\\d{6})(((1[8,9])|(20))\\d{2})(\\d{2})(\\d{2})(\\d{3})([0-9]|x|X)$").matcher(str).matches()
                        || Pattern.compile("^(\\d{6})(\\d{2})((0[1,2,3,4,5,7,8,9])|(1[0,1,2]))(([0][1,2,3,4,5,7,8,9])|([1,2]/d)|(3[0,1]))(\\d{3})$").matcher(str).matches();
                break;
            case NAME://是否符合姓名格式（字母或汉字）
                isMatch = Pattern.compile("^[\\u4e00-\\u9fa5_a-zA-Z]+$").matcher(str).matches();
                break;
            case ADDR://是否符合地址格式（字母、汉字、数字、-）
                isMatch = Pattern.compile("^[\\u4e00-\\u9fa5_a-zA-Z0-9\\-]+$").matcher(str).matches();
                break;
            default:
                break;
        }
        return isMatch;
    }

    /**
     * 字符串转整数
     *
     * @param str
     * @param defValue
     * @return
     */
    public static int toInt(String str, int defValue) {
        try {
            return Integer.parseInt(str);
        } catch (Exception e) {
        }
        return defValue;
    }

    /**
     * 对象转整
     *
     * @param obj
     * @return 转换异常返回 0
     */
    public static int toInt(Object obj) {
        if (obj == null)
            return 0;
        return toInt(obj.toString(), 0);
    }

    /**
     * String转long
     *
     * @param obj
     * @return 转换异常返回 0
     */
    public static long toLong(String obj) {
        try {
            return Long.parseLong(obj);
        } catch (Exception e) {
        }
        return 0;
    }

    /**
     * String转double
     *
     * @param obj
     * @return 转换异常返回 0
     */
    public static double toDouble(String obj) {
        try {
            return Double.parseDouble(obj);
        } catch (Exception e) {
        }
        return 0D;
    }

    /**
     * 字符串转布尔
     *
     * @param b
     * @return 转换异常返回 false
     */
    public static boolean toBool(String b) {
        try {
            return Boolean.parseBoolean(b);
        } catch (Exception e) {
        }
        return false;
    }

    /**
     * 判断一个字符串是不是数字
     */
    public static boolean isNumber(String str) {
        try {
            Integer.parseInt(str);
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    /**
     * 获取AppKey
     */
    public static String getMetaValue(Context context, String metaKey) {
        Bundle metaData = null;
        String apiKey = null;
        if (context == null || metaKey == null) {
            return null;
        }
        try {
            ApplicationInfo ai = context.getPackageManager()
                    .getApplicationInfo(context.getPackageName(),
                            PackageManager.GET_META_DATA);
            if (null != ai) {
                metaData = ai.metaData;
            }
            if (null != metaData) {
                apiKey = metaData.getString(metaKey);
            }
        } catch (NameNotFoundException e) {

        }
        return apiKey;
    }

    /**
     * 获取手机IMEI码
     */
    public static String getPhoneIMEI(Activity aty) {
        TelephonyManager tm = (TelephonyManager) aty
                .getSystemService(Activity.TELEPHONY_SERVICE);
        return tm.getDeviceId();
    }

    /**
     * MD5加密
     */
    public static String md5(String string) {
        byte[] hash;
        try {
            hash = MessageDigest.getInstance("MD5").digest(
                    string.getBytes("UTF-8"));
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("Huh, MD5 should be supported?", e);
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException("Huh, UTF-8 should be supported?", e);
        }

        StringBuilder hex = new StringBuilder(hash.length * 2);
        for (byte b : hash) {
            if ((b & 0xFF) < 0x10)
                hex.append("0");
            hex.append(Integer.toHexString(b & 0xFF));
        }
        return hex.toString();
    }

    /**
     * KJ加密
     */
    public static String KJencrypt(String str) {
        char[] cstr = str.toCharArray();
        StringBuilder hex = new StringBuilder();
        for (char c : cstr) {
            hex.append((char) (c + 5));
        }
        return hex.toString();
    }

    /**
     * KJ解密
     */
    public static String KJdecipher(String str) {
        char[] cstr = str.toCharArray();
        StringBuilder hex = new StringBuilder();
        for (char c : cstr) {
            hex.append((char) (c - 5));
        }
        return hex.toString();
    }

    /**
     * 对网络连接状态进行判断
     *
     * @return true, 可用； false， 不可用
     */
    public static boolean isOpenNetwork(Context context) {
        ConnectivityManager connManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        if (connManager.getActiveNetworkInfo() != null) {
            return connManager.getActiveNetworkInfo().isAvailable();
        }
        return false;
    }

    /**
     * 随机生成长度为8-16个字符
     *
     * @return
     */
    public static String getRandomString() { //length表示生成字符串的长度
        String base = "abcdefghijklmnopqrstuvwxyz0123456789";
        Random random = new Random();
        int length = random.nextInt(8) + 8;
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < length; i++) {
            int number = random.nextInt(base.length());
            sb.append(base.charAt(number));
        }
        return sb.toString();
    }

    /**
     * 移除SharedPreference
     *
     * @param context
     * @param key
     */
    public static final void RemoveValue(Context context, String key) {
        SharedPreferences.Editor editor = getSharedPreference(context).edit();
        editor.remove(key);
        boolean result = editor.commit();
        if (!result) {
            Log.e("移除Shared", "save " + key + " failed");
        }
    }

    private static final SharedPreferences getSharedPreference(Context context) {
        return PreferenceManager.getDefaultSharedPreferences(context);
    }

    /**
     * 获取SharedPreference 值
     *
     * @param context
     * @param key
     * @return
     */
    public static final String getValue(Context context, String key) {
        return getSharedPreference(context).getString(key, "");
    }

    public static final Boolean getBooleanValue(Context context, String key) {
        return getSharedPreference(context).getBoolean(key, false);
    }

    public static final void putBooleanValue(Context context, String key,
                                             boolean bl) {
        SharedPreferences.Editor edit = getSharedPreference(context).edit();
        edit.putBoolean(key, bl);
        edit.commit();
    }

    public static final int getIntValue(Context context, String key) {
        return getSharedPreference(context).getInt(key, 0);
    }

    public static final long getLongValue(Context context, String key,
                                          long default_data) {
        return getSharedPreference(context).getLong(key, default_data);
    }

    public static final boolean putLongValue(Context context, String key,
                                             Long value) {
        SharedPreferences.Editor editor = getSharedPreference(context).edit();
        editor.putLong(key, value);
        return editor.commit();
    }

    public static final Boolean hasValue(Context context, String key) {
        return getSharedPreference(context).contains(key);
    }

    /**
     * 设置SharedPreference 值
     *
     * @param context
     * @param key
     * @param value
     */
    public static final boolean putValue(Context context, String key,
                                         String value) {
        value = value == null ? "" : value;
        SharedPreferences.Editor editor = getSharedPreference(context).edit();
        editor.putString(key, value);
        boolean result = editor.commit();
        return result;
    }

    /**
     * 设置SharedPreference 值
     *
     * @param context
     * @param key
     * @param value
     */
    public static final boolean putIntValue(Context context, String key,
                                            int value) {
        SharedPreferences.Editor editor = getSharedPreference(context).edit();
        editor.putInt(key, value);
        boolean result = editor.commit();
        return result;
    }

    /**
     * 获取版本号
     *
     * @return 当前应用的版本号
     */
    public static String getVersion(Context context) {
        try {
            PackageManager manager = context.getPackageManager();
            PackageInfo info = manager.getPackageInfo(context.getPackageName(),
                    0);
            String version = info.versionName;
            return version;
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }

    public static int getVersionCode(Context context) {
        try {
            PackageInfo info = context.getPackageManager().getPackageInfo(context.getPackageName(), 0);
            String versionName = info.versionName;
            int versionCode = Integer.parseInt(String.valueOf(info.versionCode));
            return versionCode;
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    private static float sDensity = 0;

    /**
     * DP转换为像素
     *
     * @param context
     * @param nDip
     * @return
     */
    public static int dipToPixel(Context context, int nDip) {
        if (sDensity == 0) {
            final WindowManager wm = (WindowManager) context
                    .getSystemService(Context.WINDOW_SERVICE);
            DisplayMetrics dm = new DisplayMetrics();
            wm.getDefaultDisplay().getMetrics(dm);
            sDensity = dm.density;
        }
        return (int) (sDensity * nDip);
    }

    public static int Dp2Px(Context context, float dp) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dp * scale + 0.5f);
    }

    public static int Px2Dp(Context context, float px) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (px / scale + 0.5f);
    }

    /**
     * 获取时间
     *
     * @param lasttime 比如 0 就是今天 -1 就是昨天
     * @return
     */
    public static String getTime(int lasttime) {
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DATE, -lasttime);
        DateFormat mediumDateFormat = DateFormat.getDateTimeInstance
                (DateFormat.MEDIUM, DateFormat.MEDIUM);
        String data = new SimpleDateFormat("M/d", Locale.CHINA).format(cal.getTime());
        return data;
    }


    public static String getTimestamp(int ss) {
        DateFormat format = new SimpleDateFormat("HH:mm");
        return format.format(ss);
    }

    /**
     * 判断是否为汉字
     *
     * @param str
     * @return
     */
    public static boolean vd(String str) {

        char[] chars = str.toCharArray();
        boolean isGB2312 = false;
        for (int i = 0; i < chars.length; i++) {
            byte[] bytes = ("" + chars[i]).getBytes();
            if (bytes.length == 2) {
                int[] ints = new int[2];
                ints[0] = bytes[0] & 0xff;
                ints[1] = bytes[1] & 0xff;

                if (ints[0] >= 0x81 && ints[0] <= 0xFE &&
                        ints[1] >= 0x40 && ints[1] <= 0xFE) {
                    isGB2312 = true;
                    break;
                }
            }
        }
        return isGB2312;
    }

    /**
     * 加减按钮计算方法
     *
     * @param max   最大值
     * @param isAdd true为+，false为-
     */
    public static int setCountResult(TextView add, TextView sub, TextView numTv, int max, boolean isAdd) {
        int count = Integer.parseInt(numTv.getText().toString());
        int newCount = isAdd ? count + 1 : count - 1;
        numTv.setText(newCount + "");
        if (newCount == 1) {
            sub.setEnabled(false);
        } else {
            sub.setEnabled(true);
        }
        if (newCount == max) {
            add.setEnabled(false);
        } else {
            add.setEnabled(true);
        }
        return newCount;
    }


    /**
     * 保留两位小数的格式化方法
     *
     * @param num
     */
    public static String format(double num) {
        DecimalFormat df = new DecimalFormat("0.00");
        return df.format(num);
    }

    /**
     * 有小数保留两位，没有取整
     *
     * @param num
     */
    public static String formatInt(double num) {
        DecimalFormat df = new DecimalFormat("#0.00");
        String format = df.format(num);
        String[] split = format.split("\\.");
        if (split[1].equals("00")) {
            return split[0];
        } else if (split[1].endsWith("0")) {
            return split[0] + "." + split[1].charAt(0);
        } else {
            return format;
        }
    }


    /**
     * 获取第一个小号的SpannableString对象
     *
     * @param str 缩小的
     * @return
     */
    //*****该方法不能用 需修改*******//
    public static SpannableString getFirstOneSpannableText(String str) {

        SpannableString spanStr = new SpannableString(str);

        spanStr.setSpan(new AbsoluteSizeSpan(48, true), 0, 1,
                Spannable.SPAN_INCLUSIVE_EXCLUSIVE);
        return spanStr;
    }

    /**
     * 获取末尾小号的SpannableString对象
     *
     * @param str 缩小的
     * @return
     */
    public static SpannableString getSpannableText(String str) {
        SpannableString spanStr = new SpannableString(str);
        spanStr.setSpan(new RelativeSizeSpan(0.7f), str.length() - 1, str.length(),
                Spannable.SPAN_INCLUSIVE_EXCLUSIVE);
        return spanStr;
    }
    public static SpannableString getSpannableText2(String str) {
        SpannableString spanStr = new SpannableString(str);
        spanStr.setSpan(new RelativeSizeSpan(0.7f), str.length() - 2, str.length(),
                Spannable.SPAN_INCLUSIVE_EXCLUSIVE);
        return spanStr;
    }

    /**
     * 获取末尾小号的SpannableString对象
     * 缩小两位
     *
     * @param str 缩小的
     * @return
     */
    public static SpannableString getTwoSpannableText(String str) {
        SpannableString spanStr = new SpannableString(str);
        spanStr.setSpan(new RelativeSizeSpan(0.7f), str.length() - 1, str.length(),
                Spannable.SPAN_INCLUSIVE_EXCLUSIVE);
        spanStr.setSpan(new RelativeSizeSpan(0.7f), str.length() - 2, str.length(),
                Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        return spanStr;
    }


    /**
     * 18581879487 --> 185****8187
     *
     * @param mobile
     * @return encryptMobile
     */
    public static String getEncryptMobile(String mobile) {
        String encryptMobile;
        if (mobile.length() > 10) {
            encryptMobile = new String(mobile.substring(0, 3) + "****" + mobile.substring(7, 11));
        } else {
            //没拿到手机号，退出登录
            encryptMobile = "";
        }
        return encryptMobile;
    }

    /**
     * 除过末尾字符其他全部被*代替
     *
     * @param str
     * @return
     */
    public static StringBuilder getHideString(String str) {
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < str.length(); i++) {
            if (i < str.length() - 1) {
                stringBuilder.append("*");
            } else {
                stringBuilder.append(str.charAt(str.length() - 1));
            }
        }
        return stringBuilder;
    }

    /**
     * 首位和末尾不隐藏其他全部隐藏
     *
     * @param str
     * @return
     */
    public static StringBuilder getHideTwoString(String str) {
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < str.length(); i++) {
            if (i == 0) {
                stringBuilder.append(str.charAt(0));
            } else if (i < str.length() - 1 && str.length() > 0) {
                stringBuilder.append("*");
            } else {
                stringBuilder.append(str.charAt(str.length() - 1));
            }
        }
        return stringBuilder;
    }

    /**
     * 隐藏中间的位数
     *
     * @param str
     * @return
     */
    public static StringBuilder getHideCenterString(String str) {
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < str.length(); i++) {
            if (i <= 3) {
                stringBuilder.append(str.charAt(i));
            } else if (i < str.length() - 4 && str.length() > 3) {
                stringBuilder.append("*");
            } else {
                stringBuilder.append(str.charAt(i));
            }
        }
        return stringBuilder;
    }

    /**
     * 隐藏中间的位数
     *
     * @param str
     * @return
     */
    public static StringBuilder getHideCardString(String str) {
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < str.length(); i++) {
            if (i <= 3) {
                stringBuilder.append(str.charAt(i));
            } else if (i < str.length() - 8 && str.length() > 3) {
                stringBuilder.append("*");
            } else {
                stringBuilder.append(str.charAt(i));
            }
        }
        return stringBuilder;
    }

    /**
     * 隐藏中间的位数
     *
     * @param str
     * @return
     */
    public static StringBuilder getHideCentersString(String str) {
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < str.length(); i++) {
            if (i <= 3) {
                stringBuilder.append(str.charAt(i));
            } else if (i < str.length() - 4 && str.length() > 3) {
                stringBuilder.append("*");
            } else {
                stringBuilder.append(str.charAt(i));
            }
        }
        return stringBuilder;
    }

    /**
     * 隐藏全部的字符
     * @param str
     * @return
     */
    public static StringBuilder getHideAllString(String str) {
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < str.length(); i++) {
            stringBuilder.append("*");
        }
        return stringBuilder;
    }
    /**
     * 时间转成正常
     * 如20161128175545 转成2016-11-28 17:55：45
     */
    public static String getConverterTime(String dateString) {
        String newStr = "";
        SimpleDateFormat df = new SimpleDateFormat("yyyyMMddhhmmss");
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            Date date = df.parse(dateString);
            newStr = sdf.format(date);
            return newStr;
        } catch (Exception ex) {

        }
        return null;
    }


    /**
     * 去除字符串数组中的重复数据
     *
     */
    public static List<String> resetPhone(List<String> lists) {
        if (lists == null) {
            return null;
        }
        List<String> list = new ArrayList<String>();
        for (Iterator<String> iterator = lists.iterator(); iterator.hasNext();) {
            String str = iterator.next();
            // 去重
            if (!list.contains(str)) {
                list.add(str);
            }
        }
        return list;
    }

    public static String getCurrentDate(){
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
        return df.format(new Date());
    }

    /**
     * 节假日查询
     * @des 用作查询指定时间是否是节假日
     * @param time 要查询的时间，格式：20161104，若为null则是当前时间
     * */
    public static String queryHoliday(String time){
        String appid = "26649";
        String secret = "87b15420189441dfb06d14635c268a0d";
        /*return new ShowApiRequest( "http://route.showapi.com/894-1",appid,secret)
                .addTextPara("day",time)
                .post();*/
        return sendPost("http://route.showapi.com/894-2", "showapi_appid=26649&showapi_sign=87b15420189441dfb06d14635c268a0d&day=");
    }

    /**
     * 向指定 URL 发送POST方法的请求
     *
     * @param url
     *            发送请求的 URL
     * @param param
     *            请求参数，请求参数应该是 name1=value1&name2=value2 的形式。
     * @return 所代表远程资源的响应结果
     */
    public static String sendPost(String url, String param) {
        PrintWriter out = null;
        BufferedReader in = null;
        String result = "";
        try {
            URL realUrl = new URL(url);
            // 打开和URL之间的连接
            URLConnection conn = realUrl.openConnection();
            // 设置通用的请求属性
            conn.setRequestProperty("accept", "*/*");
            conn.setRequestProperty("connection", "Keep-Alive");
            conn.setRequestProperty("user-agent",
                    "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
            // 发送POST请求必须设置如下两行
            conn.setDoOutput(true);
            conn.setDoInput(true);
            // 获取URLConnection对象对应的输出流
            out = new PrintWriter(conn.getOutputStream());
            // 发送请求参数
            out.print(param);
            // flush输出流的缓冲
            out.flush();
            // 定义BufferedReader输入流来读取URL的响应
            in = new BufferedReader(
                    new InputStreamReader(conn.getInputStream()));
            String line;
            while ((line = in.readLine()) != null) {
                result += line;
            }
        } catch (Exception e) {
            System.out.println("发送 POST 请求出现异常！"+e);
            e.printStackTrace();
        }
        //使用finally块来关闭输出流、输入流
        finally{
            try{
                if(out!=null){
                    out.close();
                }
                if(in!=null){
                    in.close();
                }
            }
            catch(IOException ex){
                ex.printStackTrace();
            }
        }
        return result;
    }


    /**
     * @des 判断当前时间是否在某个时间段
     * @param startHour 起始时间点
     * @param endHour 截止时间点
     * */
    public static boolean isCurrentBetweenTime(int startHour,int endHour){
        Calendar now= Calendar.getInstance();
        int currentHour = now.get(Calendar.HOUR_OF_DAY);
        Log.i("dayType","hour:"+currentHour);
        return startHour <=currentHour & endHour >= currentHour;
    }

}
