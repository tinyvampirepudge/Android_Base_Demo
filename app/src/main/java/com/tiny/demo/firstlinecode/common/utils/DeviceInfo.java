package com.tiny.demo.firstlinecode.common.utils;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.provider.Settings;
import android.telephony.TelephonyManager;

import com.google.gson.Gson;
import com.tiny.demo.firstlinecode.installation.Installation;

/**
 * Created by wenli on 15/4/1.
 */
public class DeviceInfo {
//    static String APP_VERSION = "";
//    static String APP_PACKAGE = "";
//    static String ANDROID_VERSION = "";
//    static String PHONE_MODEL = "";
//    static String PHONE_MANUFACTURER = "";

//    public static void loadBaseInfo(Context context) {
//
//        DeviceInfo.ANDROID_VERSION = android.os.Build.VERSION.RELEASE;
//        DeviceInfo.PHONE_MODEL = android.os.Build.MODEL;
//        DeviceInfo.PHONE_MANUFACTURER = android.os.Build.MANUFACTURER;
//
//        PackageManager packageManager = context.getPackageManager();
//        try {
//            PackageInfo packageInfo = packageManager.getPackageInfo(context.getPackageName(), 0);
//            DeviceInfo.APP_VERSION = "" + packageInfo.versionCode;
//            DeviceInfo.APP_PACKAGE = packageInfo.packageName;
//        } catch (PackageManager.NameNotFoundException e) {
//            e.printStackTrace();
//        }
//    }

    public static String getAndroidVersion() {
        return android.os.Build.VERSION.RELEASE;
    }

    public static String getDeviceModel() {
        return android.os.Build.MODEL;
    }

    public static String getManufacturer() {
        return android.os.Build.MANUFACTURER;
    }

    public static String getBrand() {
        return Build.BRAND;
    }

    public static int getAppVersionCode(Context context) {
        PackageManager packageManager = context.getPackageManager();
        try {
            PackageInfo packageInfo = packageManager.getPackageInfo(context.getPackageName(), 0);
            return packageInfo.versionCode;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return 1;
    }


    public static String getAppPackage(Context context) {
        PackageManager packageManager = context.getPackageManager();
        try {
            PackageInfo packageInfo = packageManager.getPackageInfo(context.getPackageName(), 0);
            return packageInfo.packageName;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return "";
    }

    public static String getTelephoneDeviceId(Context context) {
        try {
            TelephonyManager tm = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
            String did = tm.getDeviceId();
            return (did == null) ? "" : did;
        } catch (Exception e) {

        }
        return "";
    }

    public static String getSimSerialNumber(Context context) {
        try {
            TelephonyManager tm = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
            String serial = tm.getSimSerialNumber();
            return (serial == null) ? "" : serial;
        } catch (Exception e) {

        }
        return "";
    }

    public static String getBuildSerial() {
        return Build.SERIAL;
    }

    public static String getAndroidId(Context context) {
        try {
            String androidId = Settings.Secure.getString(context.getContentResolver(), Settings.Secure.ANDROID_ID);
            return (androidId == null) ? "" : androidId;
        } catch (Exception e) {

        }
        return "";
    }

    public static String getWIFIMacAddress(Context context) {
        try {
            WifiManager wm = (WifiManager)context.getSystemService(Context.WIFI_SERVICE);
            String m_szWLANMAC = wm.getConnectionInfo().getMacAddress();
            return (m_szWLANMAC == null) ? "" : m_szWLANMAC;
        } catch (Exception e) {

        }
        return "";
    }

    public static String getInstallId(Context context) {
        return Installation.id(context);
    }

    private static boolean isValidDeviceId(String s) {
        do {
            if (s == null)
                break;
            if (s.length() == 0)
                break;
            boolean allZero = true;
            for (int i = 0; i < s.length(); ++i) {
                if (s.charAt(i) != '0') {
                    allZero = false;
                    break;
                }
            }
            if (allZero)
                break;
            return true;
        } while (false);
        return false;
    }

    /**
     * +----+----------+------------------+-----------------+--------------+------------+-------------+-----+------------+------------+----------+------------+
     | id | app_type | device_id        | device_version  | device_brand | os_version | app_version | ext | ctime      | updtime    | user_id  | login_time |
     +----+----------+------------------+-----------------+--------------+------------+-------------+-----+------------+------------+----------+------------+
     | 69 |        1 | a000000000000000 | HUAWEI G700-U00 | HUAWEI       | 4.2.2      | 1.06        |     | 1428921106 | 1429504956 | 80002823 | 1429287446 |
     +----+----------+------------------+-----------------+--------------+------------+-------------+-----+------------+------------+----------+------------+
     */
    public static String getDeviceId(Context context) {
        String id = "";
        id = getTelephoneDeviceId(context);
        if (isValidDeviceId(id))
            return "a" + id;
        id = getWIFIMacAddress(context);
        if ((id != null) && (id.length() > 0))
            return "b" + id;
        id = getAndroidId(context);
        if ((id != null) && (id.length() > 0)) {
            if (!id.equals("9774d56d682e549c")) {
                return "c" + id;
            }
        }
        id = getBuildSerial();
        if ((id != null) && (id.length() > 0)) {
            return "d" + id;
        }
        id = getInstallId(context);
        return "e" + id;
    }

    public static String getExtInfo(Context context) {
        DeviceExtInfo ex = new DeviceExtInfo();
        String s = getTelephoneDeviceId(context);
        if (isValidDeviceId(s))
            ex.DEVICE_ID = s;
        s = getWIFIMacAddress(context);
        if ((s != null) && (s.length() > 0))
            ex.WIFI_MAC = s;
        s = getAndroidId(context);
        if ((s != null) && (s.length() > 0))
            ex.ANDROID_ID = s;
        s = getBuildSerial();
        if ((s != null) && (s.length() > 0))
            ex.BUILD_SERIAL = s;
        s = getInstallId(context);
        if ((s != null) && (s.length() > 0))
            ex.INSTALL_ID = s;
        String sExt = "";
        try {
            Gson gson = new Gson();
            sExt = gson.toJson(ex);
        } catch (Exception e) {
            sExt = "";
        }
        return sExt;
    }

}