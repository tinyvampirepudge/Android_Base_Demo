package com.tiny.demo.firstlinecode.common.utils;

import com.tinytongtong.tinyutils.LogUtils;

import java.util.Iterator;
import java.util.Map;

/**
 * Created by tiny on 16/7/28.
 * hashMap工具类
 */
public class MapUtils {
//    /**
//     * 将HashMap<String, String> map参数添加进RequestParams中
//     *
//     * @param requestParams
//     * @param map
//     * @return
//     */
//    public static RequestParams addMapParams(RequestParams requestParams, Map<String, String> map) {
//        Iterator iter = map.entrySet().iterator();
//        while (iter.hasNext()) {
//            Map.Entry entry = (Map.Entry) iter.next();
//            String key = (String) entry.getKey();
//            String val = (String) entry.getValue();
//            requestParams.add(key, val);
//        }
//        return requestParams;
//    }

    /**
     * 将hashmap封装进bundle
     *
     * @param mapParams
     * @return
     */
//    public static Bundle transMapToBundle(Map<String, String> mapParams) {
//        Bundle params = new Bundle();
//        ParcelableMap mMap = new ParcelableMap();
//        mMap.setMap(mapParams);
//        params.putParcelable(Const.MAP_PARAMS, mMap);
//        return params;
//    }

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
                LogUtils.INSTANCE.d("key --> " + key + ",value --> " + val);
            }
        }
    }

    /**
     * 比较两个Map<String, String>的内容是否相等。
     *
     * @param map1
     * @param map2
     * @return
     */
    public static boolean compareMapsEqual(Map<String, String> map1,
                                           Map<String, String> map2) {
        if (map1 == null || map2 == null) {//为空，false.
            return false;
        }
        if (map1.size() != map2.size()) {//size不相等，false.
            return false;
        }
        Iterator iter = map1.entrySet().iterator();
        while (iter.hasNext()) {
            Map.Entry entry = (Map.Entry) iter.next();
            String key = (String) entry.getKey();
            String val = (String) entry.getValue();
            //比较key和value
            if (map2.containsKey(key)) {
                String value2 = getValueByKey(map2, key);
                if (value2.equals(val)) {
                    //equal
                } else {
                    return false;
                }
            } else {
                return false;
            }
        }
        return true;
    }
}
