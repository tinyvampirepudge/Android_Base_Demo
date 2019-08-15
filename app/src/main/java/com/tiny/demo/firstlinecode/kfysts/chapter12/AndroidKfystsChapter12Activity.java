package com.tiny.demo.firstlinecode.kfysts.chapter12;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.util.LruCache;
import android.widget.Button;
import android.widget.ImageView;

import com.tiny.demo.firstlinecode.R;
import com.tinytongtong.tinyutils.ScreenUtils;
import com.tiny.demo.firstlinecode.kfysts.chapter12.jakewarton.DiskLruCache;

import java.io.File;
import java.io.IOException;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Desc: 第十二章 Bitmap的加载和Cache
 *
 * @author tiny
 * @date 2018/5/27 上午1:04
 */
public class AndroidKfystsChapter12Activity extends AppCompatActivity {
    @BindView(R.id.iv)
    ImageView iv;
    @BindView(R.id.btn_test1)
    Button btnTest1;
    @BindView(R.id.btn_test2)
    Button btnTest2;
    @BindView(R.id.btn_test3)
    Button btnTest3;

    public static void actionStart(Context context) {
        Intent starter = new Intent(context, AndroidKfystsChapter12Activity.class);
        context.startActivity(starter);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_android_kfysts_chapter12);
        ButterKnife.bind(this);

        iv.setImageBitmap(BitmapUtils.decodeSampleBitmapFromResource(getResources(), R.drawable.ic_erha,
                ScreenUtils.dip2px(AndroidKfystsChapter12Activity.this, 160), ScreenUtils.dip2px(AndroidKfystsChapter12Activity.this, 160)));
    }

    @OnClick(R.id.btn_test1)
    public void onBtnTest1Clicked() {
        int maxMemory = (int) (Runtime.getRuntime().maxMemory() / 1024);
        int cacheSize = maxMemory / 8;
        LruCache<String, Bitmap> mMemoryCache = new LruCache<String, Bitmap>(cacheSize) {
            @Override
            protected int sizeOf(String key, Bitmap value) {
                return value.getRowBytes() * value.getHeight() / 1024;
            }
        };
    }

    public static final long DISK_CACHE_SIZE = 1024 * 1024 * 50;//50MB
    private DiskLruCache mDiskLruCache;

    @OnClick(R.id.btn_test2)
    public void onBtnTest2Clicked() {
        File diskCacheDir = getDiskCacheDir(this, "bitmap");
        if (!diskCacheDir.exists()) {
            diskCacheDir.mkdirs();
        }
        try {
            mDiskLruCache = DiskLruCache.open(diskCacheDir, 1, 1, DISK_CACHE_SIZE);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public File getDiskCacheDir(Context context, String uniqueName) {
        boolean externalStorageAvailable = Environment
                .getExternalStorageState().equals(Environment.MEDIA_MOUNTED);
        final String cachePath;
        if (externalStorageAvailable) {
            cachePath = context.getExternalCacheDir().getPath();
        } else {
            cachePath = context.getCacheDir().getPath();
        }

        return new File(cachePath + File.separator + uniqueName);
    }

    @OnClick(R.id.btn_test3)
    public void onBtnTest3Clicked() {
        AndroidKfystsChapter121Activity.actionStart(this);
    }
}
