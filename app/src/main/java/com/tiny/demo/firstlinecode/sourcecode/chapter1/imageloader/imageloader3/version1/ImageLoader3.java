package com.tiny.demo.firstlinecode.sourcecode.chapter1.imageloader.imageloader3.version1;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.widget.ImageView;

import com.tiny.demo.firstlinecode.sourcecode.chapter1.imageloader.imageloader2.ImageCache2;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Desc:    开闭原则，不完整，小明写的第一步骤。
 * Created by tiny on 2017/10/3.
 * Version:
 */

public class ImageLoader3 {
    //图片缓存
    ImageCache2 mImageCache2 = new ImageCache2();
    //SD卡缓存
    DiskCache mDiskCache = new DiskCache();
    //双缓存
    DoubleCache mDoubleCache = new DoubleCache();
    //是否使用SD卡缓存
    boolean isUseDiskCache = false;
    //是否使用双缓存
    boolean isUseDoubleCache = false;
    //线程池，线程数量为CPU的数量
    ExecutorService mExecutorService = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());

    public void displayImage(String url, ImageView imageView) {
        //判断使用哪种缓存
//        Bitmap bitmap = isUseDiskCache ? mDiskCache.get(url) : mImageCache.get(url);
        Bitmap bitmap = null;
        if (isUseDoubleCache) {
            bitmap = mDoubleCache.get(url);
        } else if (isUseDiskCache) {
            bitmap = mDiskCache.get(url);
        } else {
            bitmap = mImageCache2.get(url);
        }
        if (null != bitmap) {
            imageView.setImageBitmap(bitmap);
            return;
        }
        //没有缓存，则提交给线程池异步下载图片
        imageView.setTag(url);
        mExecutorService.submit(() -> {
            Bitmap bitmap1 = downloadImage(url);
            if (null == bitmap1) {
                return;
            }
            if (imageView.getTag().equals(url)) {
                imageView.setImageBitmap(bitmap1);
            }
            mImageCache2.put(url, bitmap1);
            mDiskCache.put(url, bitmap1);
            mDoubleCache.put(url, bitmap1);
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

    public void setUseDiskCache(boolean useDiskCache) {
        isUseDiskCache = useDiskCache;
    }

    public void setDoubleCache(boolean useDoubleCache) {
        this.isUseDoubleCache = useDoubleCache;
    }
}
