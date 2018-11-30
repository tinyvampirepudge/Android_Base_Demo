package com.tiny.demo.firstlinecode.designpattern.practice;

import android.content.Context;
import android.content.Intent;

import com.tiny.demo.firstlinecode.R;
import com.tiny.demo.firstlinecode.base.BaseActivity;
import com.tiny.demo.firstlinecode.designpattern.practice.observer.ObserverPracticeActivity;

import butterknife.OnClick;

/**
 * @Description: 设计模式实战
 * @Author wangjianzhou@qding.me
 * @Date 2018/11/30 1:29 PM
 * @Version
 */
public class DesignPatternPracticeActivity extends BaseActivity {
    public static void actionStart(Context context) {
        Intent starter = new Intent(context, DesignPatternPracticeActivity.class);
        context.startActivity(starter);
    }

    @Override
    protected int setContentLayout() {
        return R.layout.activity_design_pattern_practice;
    }

    @Override
    protected void buildContentView() {

    }

    @Override
    protected void initViewData() {

    }

    @OnClick(R.id.btn_test1)
    public void onBtnTest1Clicked() {
        ObserverPracticeActivity.actionStart(this);
    }

    @OnClick(R.id.btn_test2)
    public void onBtnTest2Clicked() {
    }
}