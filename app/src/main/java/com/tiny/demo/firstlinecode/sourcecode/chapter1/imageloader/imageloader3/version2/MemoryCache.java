package com.tiny.demo.firstlinecode.sourcecode.chapter1.imageloader.imageloader3.version2;

import android.graphics.Bitmap;
import android.util.LruCache;

/**
 * Desc:    内存缓存MemoryCache类
 * Created by tiny on 2017/10/4.
 * Version:
 */

public class MemoryCache implements ImageCache {
    private LruCache<String, Bitmap> mMemoryCache;

    public MemoryCache() {
        //初始化LRU缓存
    }

    @Override
    public void put(String url, Bitmap bitmap) {
        mMemoryCache.put(url, bitmap);
    }

    @Override
    public Bitmap get(String url) {
        return mMemoryCache.get(url);
    }
}
