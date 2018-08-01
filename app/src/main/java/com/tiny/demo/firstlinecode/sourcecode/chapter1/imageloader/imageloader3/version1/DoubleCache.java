package com.tiny.demo.firstlinecode.sourcecode.chapter1.imageloader.imageloader3.version1;

import android.graphics.Bitmap;

/**
 * Desc: 在缓存逻辑中，如果用户使用内存缓存时就不能使用SD卡缓存。
 * 类似的，使用SD卡缓存是用户就不能使用内存缓存。用户需要这两种策略的综合，
 * 首先缓存优先使用内存缓存，如果内存缓存没有图片再使用SD卡缓存，
 * 如果SD卡中也没有图片最后从网络上获取，这才是最好的缓存策略。
 * Created by tiny on 2017/10/3.
 * Version:
 */

public class DoubleCache {
    /**
     * 双缓存。获取图片时先从内存缓存中获取，如果内存中没有缓存改图片，再从SD卡中获取。
     * 缓存图片也是内存和SD卡中都缓存一份
     */

    ImageCache3 mMemoryCache = new ImageCache3();
    DiskCache mDiskCache = new DiskCache();

    //先从内存缓存中获取图片，如果没有，再从SD卡中获取
    public Bitmap get(String url) {
        Bitmap bitmap = mMemoryCache.get(url);
        if (null == bitmap) {
            bitmap = mDiskCache.get(url);
        }
        return bitmap;
    }

    //将图片缓存到内存和SD卡中
    public void put(String url, Bitmap bitmap) {
        mMemoryCache.put(url, bitmap);
        mDiskCache.put(url, bitmap);
    }
}
