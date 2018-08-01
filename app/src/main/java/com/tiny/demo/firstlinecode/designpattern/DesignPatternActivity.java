package com.tiny.demo.firstlinecode.designpattern;

import android.widget.Button;

import com.tiny.demo.firstlinecode.R;
import com.tiny.demo.firstlinecode.base.BaseActivity;
import com.tiny.demo.firstlinecode.designpattern.chapter01.SimpleFactoryActivity;
import com.tiny.demo.firstlinecode.designpattern.chapter06.DecorateActivity;
import com.tiny.demo.firstlinecode.designpattern.chapter13.BuilderActivity;
import com.tiny.demo.firstlinecode.designpattern.chapter21.SingletonActivity;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Desc:    大话设计模式
 * Created by tiny on 2017/10/18.
 * Version:
 */

public class DesignPatternActivity extends BaseActivity {
    @BindView(R.id.btn_chapter6)
    Button btnChapter6;
    @BindView(R.id.btn_chapter21)
    Button btnChapter21;
    @BindView(R.id.btn_chapter13)
    Button btnChapter13;
    @BindView(R.id.btn_chapter1)
    Button btnChapter1;

    @Override
    protected int setContentLayout() {
        return R.layout.activity_design_pattern;
    }

    @Override
    protected void buildContentView() {

    }

    @Override
    protected void initViewData() {

    }

    @OnClick(R.id.btn_chapter6)
    public void onViewClicked() {
        activitySwitch(DecorateActivity.class);
    }

    @OnClick(R.id.btn_chapter21)
    public void onChapter21Clicked() {
        activitySwitch(SingletonActivity.class);
    }

    @OnClick(R.id.btn_chapter13)
    public void onChapter13Clicked() {
        activitySwitch(BuilderActivity.class);
    }

    @OnClick(R.id.btn_chapter1)
    public void onChapter1Clicked() {
        activitySwitch(SimpleFactoryActivity.class);
    }
}
