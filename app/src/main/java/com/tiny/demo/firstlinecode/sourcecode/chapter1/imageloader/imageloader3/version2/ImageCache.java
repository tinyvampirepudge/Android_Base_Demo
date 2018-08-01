package com.tiny.demo.firstlinecode.sourcecode.chapter1.imageloader.imageloader3.version2;

import android.graphics.Bitmap;

/**
 * Desc:
 * Created by tiny on 2017/10/4.
 * Version:
 */

public interface ImageCache {
    void put(String url, Bitmap bitmap);

    Bitmap get(String url);
}
