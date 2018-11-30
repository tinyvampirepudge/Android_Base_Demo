package com.tiny.demo.firstlinecode.designpattern.practice.observer;

import android.content.Context;
import android.content.Intent;
import android.widget.Button;

import com.tiny.demo.firstlinecode.R;
import com.tiny.demo.firstlinecode.base.BaseActivity;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * @Description: 观察者模式实战
 * @Author wangjianzhou@qding.me
 * @Date 2018/11/30 1:34 PM
 * @Version
 */
public class ObserverPracticeActivity extends BaseActivity {

    @BindView(R.id.btn_test1)
    Button btnTest1;
    @BindView(R.id.btn_test2)
    Button btnTest2;

    public static void actionStart(Context context) {
        Intent starter = new Intent(context, ObserverPracticeActivity.class);
        context.startActivity(starter);
    }

    @Override
    protected int setContentLayout() {
        return R.layout.activity_observer_practice;
    }

    @Override
    protected void buildContentView() {
    }

    @Override
    protected void initViewData() {

    }

    @OnClick(R.id.btn_test1)
    public void onBtnTest1Clicked() {
        ObserverLoginActivity.actionStart(this);
    }

    @OnClick(R.id.btn_test2)
    public void onBtnTest2Clicked() {
    }
}
