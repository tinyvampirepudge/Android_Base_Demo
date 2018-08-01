package com.tiny.demo.firstlinecode.staticmanager;

/**
 * Created by 87959 on 2017/3/15.
 */

public class UrlManager {
    public static boolean isOnline = true;

    public static void setIsOnline(boolean isOnline) {
        UrlManager.isOnline = isOnline;
    }

    public static String getBaseUrl(){
        if (isOnline){
            return "www.baidu.com";
        }else{
            return "www.baidutest.com";
        }
    }

    public static String base_url = isOnline ? "www.baidu.com":"www.baidutest.com";

    public static final String login_url = getBaseUrl() + "/login";

    public String baseUrlField = isOnline?"base url field online":"base url field offline";

    public String feedUrl = baseUrlField + " --> feedback";
}
