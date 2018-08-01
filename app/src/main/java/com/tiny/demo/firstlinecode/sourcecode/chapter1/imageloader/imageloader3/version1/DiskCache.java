package com.tiny.demo.firstlinecode.sourcecode.chapter1.imageloader.imageloader3.version1;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import com.tiny.demo.firstlinecode.app.FLCApplication;
import com.tiny.demo.firstlinecode.common.utils.StorageUtils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Desc:    SD卡缓存
 * Created by tiny on 2017/10/3.
 * Version:
 */

public class DiskCache {
    static String cacheDir = "flc_image_loader";

    //从缓存中获取图片
    public Bitmap get(String url) {
        return BitmapFactory.decodeFile(cacheDir + url);
    }

    //将图片缓存到内存中
    public void put(String url, Bitmap bmp) {
        FileOutputStream fileOutputStream = null;
        try {
            File dir = StorageUtils.getCacheDirectory(FLCApplication.instance());
            File file = new File(dir, url);
            fileOutputStream = new FileOutputStream(file);
            bmp.compress(Bitmap.CompressFormat.PNG, 100, fileOutputStream);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } finally {
            if (fileOutputStream != null) {
                try {
                    fileOutputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
