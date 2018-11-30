package com.tiny.demo.firstlinecode.designpattern;

import android.os.Bundle;

import com.tiny.demo.firstlinecode.R;
import com.tiny.demo.firstlinecode.base.BaseActivity;
import com.tiny.demo.firstlinecode.designpattern.practice.DesignPatternPracticeActivity;
import com.tiny.demo.firstlinecode.designpattern.talkbig.TalkBigDesignPatternActivity;

import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * @Description: 设计模式
 * @Author wangjianzhou@qding.me
 * @Date 2017/10/18 1:55 PM
 * @Version
 */
public class DesignPatternActivity extends BaseActivity {

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

    @OnClick(R.id.btn_test1)
    public void onBtnTest1Clicked() {
        DesignPatternPracticeActivity.actionStart(this);
    }

    @OnClick(R.id.btn_test2)
    public void onBtnTest2Clicked() {
        TalkBigDesignPatternActivity.actionStart(this);
    }
}
