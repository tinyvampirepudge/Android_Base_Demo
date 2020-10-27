package com.tiny.demo.firstlinecode.kfysts.chapter10;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;

import com.tiny.demo.firstlinecode.R;
import com.tinytongtong.tinyutils.LogUtils;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Desc:   第十章 Android的消息机制。
 *
 * @author tiny
 * @date 2018/4/18 下午2:42
 */
public class AndroidKfystsChapter10Activity extends AppCompatActivity {
    public static final String TAG = AndroidKfystsChapter10Activity.class.getSimpleName();

    @BindView(R.id.btn_test1)
    Button btnTest1;
    @BindView(R.id.btn_test2)
    Button btnTest2;

    public static void actionStart(Context context) {
        Intent starter = new Intent(context, AndroidKfystsChapter10Activity.class);
        context.startActivity(starter);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_android_kfysts_chapter10);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.btn_test1)
    public void onBtnTest1Clicked() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                Handler handler = new Handler();
            }
        }).start();
    }

    private ThreadLocal<Boolean> mBooleanThreadLocal = new ThreadLocal<>();

    @OnClick(R.id.btn_test2)
    public void onBtnTest2Clicked() {
        mBooleanThreadLocal.set(true);
        LogUtils.INSTANCE.e(TAG, "[Thread#main]mBooleanThreadLocal=" + mBooleanThreadLocal.get());

        new Thread("Thread#1") {
            @Override
            public void run() {
                mBooleanThreadLocal.set(false);
                LogUtils.INSTANCE.e(TAG, "[Thread#1]mBooleanThreadLocal=" + mBooleanThreadLocal.get());
            }
        }.start();

        new Thread("Thread#2") {
            @Override
            public void run() {
                LogUtils.INSTANCE.e(TAG, "[Thread#2]=" + mBooleanThreadLocal.get());
            }
        }.start();
    }

    @OnClick(R.id.btn_test3)
    public void onBtnTest3Clicked() {
        new Thread("Thread#3") {
            @Override
            public void run() {
                Looper.prepare();
                Handler handler = new Handler();
                Looper.loop();
            }
        }.start();
    }
}
