package com.tiny.demo.firstlinecode.bitmap;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.tiny.demo.firstlinecode.R;
import com.tiny.demo.firstlinecode.bitmap.largeimageview.LargeImageViewActivity;

import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Desc: Bitmap加载
 *
 * @author tiny
 * @date 2018/6/30 下午3:34
 */
public class BitmapActivity extends AppCompatActivity {

    public static void actionStart(Context context) {
        Intent starter = new Intent(context, BitmapActivity.class);
        context.startActivity(starter);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bitmap);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.btn_test1)
    public void onBtnTest1Clicked() {

    }

    @OnClick(R.id.btn_test2)
    public void onBtnTest2Clicked() {
        LargeImageViewActivity.actionStart(this);
    }

    @OnClick(R.id.btn_test3)
    public void onBtnTest3Clicked() {

    }
}
