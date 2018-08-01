package com.tiny.demo.firstlinecode.stetho.httphelper;

/**
 * Created by tiny on 17/2/22.
 */

public class UrlConfig {

    private static boolean isOnline = true;

    public static void setIsOnline(boolean isOnline) {
        UrlConfig.isOnline = isOnline;
    }

    public static class Base {
        //线上，下线环境的base url
        public static final String BASE_URL_ONLINE = "http://api.quchaogu.com/";

        //设置给retrofit的base url.
        public static String getBaseUrl() {
            return BASE_URL_ONLINE;
        }

    }

    public static class Params {
        public static final String API_VERSION = "apiversion";
        public static final String API_VERSION_VALUE = "5.3";
        public static final String PAGECOUNT = "pagecount";
        public static final String PAGEINDEX = "page";
        public static final String DEVICE_ID = "device_id";
    }

    public static class Business {
        //首页
        public static final String URL_HOME_PAGE = "app/tape/index";
    }

    static final class Optional {
        //自选编辑页面，重新排序
        public static final String URL_OPTIONAL_RESORT = "dxwapp/zixuan/resort";
    }

}
