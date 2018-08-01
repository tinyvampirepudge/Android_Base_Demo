package com.tiny.demo.firstlinecode.unittest;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.tiny.demo.firstlinecode.base.BaseActivity;
import com.tiny.demo.firstlinecode.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Desc:    android 测试入口
 * Created by tiny on 2017/10/11.
 * Version:
 */

public class AndroidTestActivity extends BaseActivity {
    @BindView(R.id.btn_junit_test)
    Button btnJunitTest;
    @BindView(R.id.btn_ui_test)
    Button btnUiTest;
    @BindView(R.id.btn_espresso_test1)
    Button btnAutoTest1;
    @BindView(R.id.btn_espresso_test2)
    Button btnEspressoTest2;
    @BindView(R.id.btn_espresso_test3)
    Button btnEspressoTest3;
    @BindView(R.id.btn_espresso_test4)
    Button btnEspressoTest4;

    @Override
    protected int setContentLayout() {
        return R.layout.activity_android_test;
    }

    @Override
    protected void buildContentView() {

    }

    @Override
    protected void initViewData() {

    }

    @OnClick({R.id.btn_junit_test, R.id.btn_ui_test, R.id.btn_espresso_test1})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_junit_test:

                break;
            case R.id.btn_ui_test:
                activitySwitch(UITestActivity.class);
                break;
            case R.id.btn_espresso_test1:
                activitySwitch(EspressoActivity1.class);
                break;
        }
    }

    @OnClick(R.id.btn_espresso_test2)
    public void onBtnEspressoTest2Clicked() {
        activitySwitch(EspressoActivity2.class);
    }

    @OnClick(R.id.btn_espresso_test3)
    public void onBtnEspressoTest3Clicked() {
        activitySwitch(EspressoActivity3.class);
    }

    @OnClick(R.id.btn_espresso_test4)
    public void onViewClicked() {
        activitySwitch(EspressoActivity4.class);
    }
}
