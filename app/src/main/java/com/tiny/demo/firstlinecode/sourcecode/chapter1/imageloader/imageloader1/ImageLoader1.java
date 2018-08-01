package com.tiny.demo.firstlinecode.sourcecode.chapter1.imageloader.imageloader1;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.LruCache;
import android.widget.ImageView;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Desc:    图片加载类，第一版
 * 缺点：这版的ImageLoader耦合太严重了，简直就没有设计可言。更不要说扩展性、灵活性了。
 * 所有的功能都写在一个类里怎么行呢，这样随着功能的增多，ImageLoader类会越来越大，
 * 代码也会越来越敷在，图片家在系统就越来越脆弱。
 * Created by tiny on 2017/9/30.
 * Version: v0.0.1
 */

public class ImageLoader1 {
    //图片缓存
    LruCache<String, Bitmap> mImageCache;
    //线程池，线程数量为CPU的数量
    ExecutorService mExecutorService = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());

    public ImageLoader1() {
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

    public void displayImage(final String url, final ImageView imageView) {
        imageView.setTag(url);
        mExecutorService.submit(() -> {
            Bitmap bitmap = downloadImage(url);
            if (null == bitmap) {
                return;
            }
            if (imageView.getTag().equals(url)) {
                imageView.setImageBitmap(bitmap);
            }
            mImageCache.put(url, bitmap);
        });
    }

    private Bitmap downloadImage(String imageUrl) {
        Bitmap bitmap = null;
        try {
            URL url = new URL(imageUrl);
            final HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            bitmap = BitmapFactory.decodeStream(conn.getInputStream());
            conn.disconnect();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return bitmap;
    }
}
