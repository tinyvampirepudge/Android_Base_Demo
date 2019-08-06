package com.tiny.demo.firstlinecode.kfysts.chapter01.intent.intentfilter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.tiny.demo.firstlinecode.R;
import com.tiny.demo.firstlinecode.staticmanager.StaticManagerActivity;

import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * @Description: IntentFilter匹配规则实战
 * @Author wangjianzhou@qding.me
 * @Date 2019-08-05 21:31
 * @Version
 */
public class IntentFilterEntryActivity extends AppCompatActivity {

    public static void actionStart(Context context) {
        Intent starter = new Intent(context, IntentFilterEntryActivity.class);
        context.startActivity(starter);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intent_filter_entry);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.btn_intent_test1)
    public void onBtnIntentTest1Clicked() {

    }

    @OnClick(R.id.btn_intent_test2)
    public void onBtnIntentTest2Clicked() {

    }

    @OnClick(R.id.btn_intent_test3)
    public void onBtnIntentTest3Clicked() {

    }

    @OnClick(R.id.btn_intent_test4)
    public void onBtnIntentTest4Clicked() {

    }
}
