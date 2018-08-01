package com.tiny.demo.firstlinecode.sourcecode.chapter1.imageloader.imageloader3.version2;

import android.graphics.Bitmap;

/**
 * Desc:    SD卡缓存DiskCache类
 * Created by tiny on 2017/10/4.
 * Version:
 */

public class DiskCache implements ImageCache {
    @Override
    public void put(String url, Bitmap bitmap) {
        //将Bitmap写入文件中
    }

    @Override
    public Bitmap get(String url) {
        return null;/* 从本地文件中获取该图片 */
    }
}
