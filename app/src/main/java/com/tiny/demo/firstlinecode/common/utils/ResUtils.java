package com.tiny.demo.firstlinecode.common.utils;


import com.tiny.demo.firstlinecode.app.FLCApplication;

/**
 * Created by focus on 2017/2/13.
 * 读取资源文件中的数据
 */

public class ResUtils {
    public static String getStringResource(int id) {
        return FLCApplication.instance().getResources().getString(id);
    }
}
