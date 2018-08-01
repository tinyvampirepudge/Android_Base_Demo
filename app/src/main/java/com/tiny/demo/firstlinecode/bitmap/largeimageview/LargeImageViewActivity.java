package com.tiny.demo.firstlinecode.bitmap.largeimageview;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.tiny.demo.firstlinecode.R;
import com.tiny.demo.firstlinecode.bitmap.largeimageview.view.LargeImageView;

import java.io.IOException;
import java.io.InputStream;

/**
 * Desc: Android 高清加载巨图方案 拒绝压缩图片
 * https://blog.csdn.net/lmj623565791/article/details/49300989#commentBox
 *
 * @author tiny
 * @date 2018/7/2 下午3:00
 */
public class LargeImageViewActivity extends AppCompatActivity {
    private LargeImageView mLargeImageView;

    public static void actionStart(Context context) {
        Intent starter = new Intent(context, LargeImageViewActivity.class);
        context.startActivity(starter);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_large_image_view);

        mLargeImageView = findViewById(R.id.id_largetImageview);
        try {
            InputStream inputStream = getAssets().open("world_map.jpg");
            mLargeImageView.setInputStream(inputStream);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
