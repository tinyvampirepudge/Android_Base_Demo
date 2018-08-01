package com.tiny.demo.firstlinecode.kfysts.chapter09;

import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;

import com.tiny.demo.firstlinecode.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Desc:  第九章 四大组件的工作过程
 *
 * @author tiny
 * @date 2018/5/17 下午10:45
 */
public class AndroidKfystsChapter09Activity extends AppCompatActivity {
    @BindView(R.id.btn_test_01)
    Button btnTest01;
    @BindView(R.id.btn_test_02)
    Button btnTest02;
    private MyReceiver myReceiver;

    public static void actionStart(Context context) {
        Intent starter = new Intent(context, AndroidKfystsChapter09Activity.class);
        context.startActivity(starter);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_android_kfysts_chapter09);
        ButterKnife.bind(this);
        myReceiver = new MyReceiver();
    }

    @OnClick(R.id.btn_test_01)
    public void onBtnTest01Clicked() {
        IntentFilter filter = new IntentFilter();
        filter.addAction("com.tiny.demo.firstlinecode.receiver.LAUNCH");
        registerReceiver(myReceiver, filter);
    }

    @OnClick(R.id.btn_test_02)
    public void onBtnTest02Clicked() {
        Intent intent = new Intent();
        intent.setAction("com.tiny.demo.firstlinecode.receiver.LAUNCH");
        sendBroadcast(intent);
    }

    @Override
    protected void onDestroy() {
        unregisterReceiver(myReceiver);
        super.onDestroy();
    }
}
