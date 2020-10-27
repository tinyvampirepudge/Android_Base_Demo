package com.tiny.demo.firstlinecode.kfysts.chapter01.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.tiny.demo.firstlinecode.R;
import com.tinytongtong.tinyutils.LogUtils;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class LauncherModeSecondActivity extends AppCompatActivity {
    public static final String TAG = LauncherModeSecondActivity.class.getSimpleName();
    @BindView(R.id.tv)
    TextView tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_launcher_mode_second);
        ButterKnife.bind(this);
        LogUtils.INSTANCE.e(TAG, "onCreate");
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        LogUtils.INSTANCE.e(TAG, "onNewIntent time:" + intent.getLongExtra("time", 0));
    }

    @OnClick(R.id.tv)
    public void onViewClicked() {
        Intent intent = new Intent(this, LauncherModeSecondActivity.class);
        intent.putExtra("time", System.currentTimeMillis());
        startActivity(intent);
    }
}
