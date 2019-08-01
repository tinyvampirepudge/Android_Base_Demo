package com.tinytongtong.tinyutils;

import android.os.Bundle;
import android.text.TextUtils;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;


/**
 * @Description: Map工具类
 *
 * @Author wangjianzhou
 * @Date 2019-08-01 11:38
 * @Version
 */
public class MapUtils {
    //界面之间传递的hashMap参数的key
    public static final String MAP_PARAMS = "MAP_PARAMS";

    /**
     * 将第二个map的数据添加进第一个map里面。
     *
     * @param dstMap
     * @param srcMap
     * @return
     */
    public static Map<String, String> addMapToMap(Map<String, String> dstMap, Map<String, String> srcMap) {
        if (srcMap == null || srcMap.size() == 0) {
            return dstMap;
        }
        if (dstMap == null) {
            dstMap = new HashMap<>();
        }
        Iterator iter = srcMap.entrySet().iterator();
        while (iter.hasNext()) {
            Map.Entry entry = (Map.Entry) iter.next();
            String key = (String) entry.getKey();
            String value = (String) entry.getValue();
            if (!TextUtils.isEmpty(key) && !TextUtils.isEmpty(key.trim()) && !TextUtils.isEmpty(value) && !TextUtils.isEmpty(value.trim())) {
                dstMap.put(key, value);
            }
        }
        return dstMap;
    }

    public static String getUrlWithParaBundle(String url, Bundle para) {
        if (url == null) {
            return "unknown";
        } else {
            if (para == null || para.size() == 0) {
                return url;
            } else {
                for (String key : para.keySet()) {
                    String val = para.get(key) + "";
                    if (url.contains("?")) {
                        url += ("&" + key + "=" + val);
                    } else {
                        url += ("?" + key + "=" + val);
                    }
                }

                return url;
            }
        }
    }

    public static String getUrlWithParaMap(String url, Map<String, String> para) {
        if (url == null) {
            return "unknown";
        } else {
            if (para == null || para.size() == 0) {
                return url;
            } else {
                Iterator iter = para.entrySet().iterator();
                while (iter.hasNext()) {
                    Map.Entry entry = (Map.Entry) iter.next();
                    String key = (String) entry.getKey();
                    String val = (String) entry.getValue();
                    if (url.contains("?")) {
                        url += ("&" + key + "=" + val);
                    } else {
                        url += ("?" + key + "=" + val);
                    }
                }
                return url;
            }
        }
    }

    /**
     * 将url中后边的参数拼接到map中。
     *
     * @param url
     * @return
     */
    public static Map<String, String> getMapParamsFromUrl(String url) {
        if (TextUtils.isEmpty(url)) {
            return new HashMap<>();
        }
        String startFlag = "?";
        String split = "&";
        String anchor = "#";
        Map<String, String> mapPara = new HashMap<>();
        int start = url.indexOf(startFlag);
        if (start != -1) {
            int anchorIndex = -1;
            anchorIndex = url.indexOf(anchor);
            if (anchorIndex != -1 && anchorIndex > start) {
                url = url.substring(start + startFlag.length(), anchorIndex);
            } else {
                url = url.substring(start + startFlag.length());
            }
        }
        if (!TextUtils.isEmpty(url)) {
            String[] strs = url.split(split);
            if (strs != null && strs.length > 0) {
                for (int i = 0; i < strs.length; i++) {
                    if (!TextUtils.isEmpty(strs[i])) {
                        String[] strsTemp = strs[i].split("=");
                        if (strsTemp != null && strsTemp.length > 1) {  //大于1防止数组越界异常
                            if (!TextUtils.isEmpty(strsTemp[0]) && !TextUtils.isEmpty(strsTemp[1])) {
                                mapPara.put(strsTemp[0], strsTemp[1]);
                            }
                        }
                    }
                }
            }
        }
        return mapPara;
    }

    /**
     * 将map封装进bundle
     *
     * @param mapParams
     * @return
     */
    public static Bundle transMapToBundle(Map<String, String> mapParams) {
        Bundle params = new Bundle();
        ParcelableMap mMap = new ParcelableMap();
        mMap.setMap(mapParams);
        params.putParcelable(MAP_PARAMS, mMap);
        return params;
    }

    /**
     * @param bundle
     * @return map
     */
    public static Map<String, String> getMapFromBundle(Bundle bundle) {
        if (bundle == null) {
            return null;
        } else {
            Map map = new HashMap<String, String>();
            for (String key : bundle.keySet()) {
                if (bundle.get(key) instanceof String) {
                    map.put(key, bundle.get(key));
                }
            }
            return map;
        }
    }


    public static Bundle getBundleFromMap(Map<String, String> mapParams) {
        Bundle params = new Bundle();

        if (mapParams != null) {
            Iterator iter = mapParams.entrySet().iterator();
            while (iter.hasNext()) {
                Map.Entry entry = (Map.Entry) iter.next();
                String key = (String) entry.getKey();
                String val = (String) entry.getValue();
                params.putString(key, val);
            }
        }
        return params;
    }

    /**
     * 在hashmap中，通过key查找value.
     *
     * @param map
     * @param key
     * @return
     */
    public static String getValueByKey(Map<String, String> map, String key) {
        if (map == null) {
            return null;
        }
        Iterator iter = map.entrySet().iterator();
        while (iter.hasNext()) {
            Map.Entry entry = (Map.Entry) iter.next();
            if (key.equals((String) entry.getKey())) {
                return (String) entry.getValue();
            }
        }
        return null;
    }

    /**
     * log打印测试
     *
     * @param map
     */
    public static void logMapParams(Map<String, String> map) {
        if (map != null) {
            Iterator iter = map.entrySet().iterator();
            while (iter.hasNext()) {
                Map.Entry entry = (Map.Entry) iter.next();
                String key = (String) entry.getKey();
                String val = (String) entry.getValue();
                LogUtils.d("key --> " + key + ",value --> " + val);
            }
        }
    }

    public static void logMapParams(Bundle para) {
        if (para != null) {

            for (String key : para.keySet()) {
                String val = para.get(key) + "";
                LogUtils.d("key --> " + key + ",value --> " + val);
            }
        }
    }


    /**
     * 将Map<String,String>类型的参数拼接到GET请求的URL后面。
     *
     * @param url
     * @param map
     * @return
     */
    public static String addParamToUrl(String url, Map<String, String> map) {
        if (TextUtils.isEmpty(url) || map == null || map.size() == 0) {
            return null;
        }
        StringBuilder sb = new StringBuilder(url);
        if (map != null) {
            Iterator iter = map.entrySet().iterator();
            while (iter.hasNext()) {
                Map.Entry entry = (Map.Entry) iter.next();
                String key = (String) entry.getKey();
                String val = (String) entry.getValue();
                LogUtils.d("key --> " + key + ",value --> " + val);
                if (!TextUtils.isEmpty(key) && !TextUtils.isEmpty(val)) {
                    if (sb.toString().contains("?")) {
                        sb.append("&" + key + "=" + val);
                    } else {
                        sb.append("?" + key + "=" + val);
                    }
                }

            }
        }
        return sb.toString();
    }

    public static void addDataToMap(String key, String value, Map<String, String> map) {
        if (!TextUtils.isEmpty(key) && !TextUtils.isEmpty(value) && map != null) {
            map.put(key, value);
        }
    }

    public static void removeNullValue(Map<String, String> map) {
        if (map != null) {
            Iterator iter = map.entrySet().iterator();
            while (iter.hasNext()) {
                Map.Entry entry = (Map.Entry) iter.next();
                String key = (String) entry.getKey();
                String val = (String) entry.getValue();
                if (TextUtils.isEmpty(key) || TextUtils.isEmpty(val)) {
                    iter.remove();
                }
            }
        }
    }
}
