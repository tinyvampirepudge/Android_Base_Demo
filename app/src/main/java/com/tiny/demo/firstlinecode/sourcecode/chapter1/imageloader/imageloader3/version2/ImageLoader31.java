package com.tiny.demo.firstlinecode.sourcecode.chapter1.imageloader.imageloader3.version2;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.widget.ImageView;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Desc:    主管亲自上阵搞得
 * Created by tiny on 2017/10/4.
 * Version:
 */

public class ImageLoader31 {
    //图片缓存
    ImageCache mImageCache = new MemoryCache();
    //线程池、线程数量为CPU的数量
    ExecutorService mExecutorService = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());

    //注入缓存实现
    public void setImageCache(ImageCache cache) {
        mImageCache = cache;
    }

    public void displayImage(String imageUrl, ImageView imageView) {
        Bitmap bitmap = mImageCache.get(imageUrl);
        if (bitmap != null) {
            imageView.setImageBitmap(bitmap);
            return;
        }
        //图片没有缓存，提交到线程池中下载图片
        submitLoadRequest(imageUrl, imageView);
    }

    private void submitLoadRequest(String imageUrl, ImageView imageView) {
        imageView.setTag(imageUrl);
        mExecutorService.submit(() -> {
            Bitmap bitmap = downloadImage(imageUrl);
            if (bitmap == null) {
                return;
            }
            if (imageView.getTag().equals(imageUrl)) {
                imageView.setImageBitmap(bitmap);
            }
            mImageCache.put(imageUrl, bitmap);
        });
    }

    private Bitmap downloadImage(String imageUrl) {
        Bitmap bitmap = null;
        try {
            URL url = new URL(imageUrl);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
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
