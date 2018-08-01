package com.tiny.demo.firstlinecode.sourcecode.chapter1.imageloader.imageloader3.version2;

import android.graphics.Bitmap;

/**
 * Desc:    双缓存DoubleCache类
 * Created by tiny on 2017/10/4.
 * Version:
 */

public class DoubleCache implements ImageCache {
    ImageCache mMemoryCache = new MemoryCache();
    ImageCache mDiskCache = new DiskCache();

    //将图片缓存到内存和SD卡中
    @Override
    public void put(String url, Bitmap bitmap) {
        mMemoryCache.put(url, bitmap);
        mDiskCache.put(url, bitmap);
    }

    //先从内存缓存中获取图片，如果没有，再从SD卡中获取
    @Override
    public Bitmap get(String url) {
        Bitmap bitmap = mMemoryCache.get(url);
        if (bitmap == null) {
            bitmap = mDiskCache.get(url);
        }
        return bitmap;
    }
}
