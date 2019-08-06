package com.tiny.demo.firstlinecode.kfysts.chapter01.intent;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.tiny.demo.firstlinecode.R;
import com.tiny.demo.firstlinecode.common.utils.LogUtils;
import com.tiny.demo.firstlinecode.kfysts.chapter01.intent.explicit.ExplicitIntentEntryActivity;
import com.tiny.demo.firstlinecode.kfysts.chapter01.intent.intentfilter.ImplicitIntentEntryActivity;
import com.tiny.demo.firstlinecode.kfysts.chapter01.intent.resolve.IntentFilterResolvedEntryActivity;

import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Desc:    Intent相关的入口
 * Created by tiny on 2018/2/24 上午10:46
 * Version:
 */
public class IntentEntryActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intent_entry);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.btn_intent_test0)
    public void onBtnIntentTest0Clicked() {
        IntentFilterResolvedEntryActivity.actionStart(this);
    }

    @OnClick(R.id.btn_intent_test1)
    public void onBtnIntentTest01Clicked() {
        ImplicitIntentEntryActivity.actionStart(this);
    }

    @OnClick(R.id.btn_intent_test2)
    public void onBtnIntentTest02Clicked() {
        ExplicitIntentEntryActivity.actionStart(this);
    }



}
