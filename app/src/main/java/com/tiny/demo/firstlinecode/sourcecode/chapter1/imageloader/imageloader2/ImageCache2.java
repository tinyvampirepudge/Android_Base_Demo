package com.tiny.demo.firstlinecode.sourcecode.chapter1.imageloader.imageloader2;

import android.graphics.Bitmap;
import android.util.LruCache;

/**
 * Desc:    用于处理图片缓存
 * Created by tiny on 2017/10/2.
 * Version: v0.0.2
 */

public class ImageCache2 {
    //图片LRU缓存
    LruCache<String, Bitmap> mImageCache;

    public ImageCache2() {
        initImageCache();
    }

    private void initImageCache() {
        //计算可使用的最大内存
        final int maxMemory = (int) (Runtime.getRuntime().maxMemory() / 1024);
        //取四分之一的可用内存作为缓存
        final int cacheSize = maxMemory / 4;
        mImageCache = new LruCache<String, Bitmap>(cacheSize) {
            @Override
            protected int sizeOf(String key, Bitmap value) {
                return value.getRowBytes() * value.getHeight() / 1024;
            }
        };
    }

    public void put(String url, Bitmap bitmap) {
        mImageCache.put(url, bitmap);
    }

    public Bitmap get(String url) {
        return mImageCache.get(url);
    }
}
