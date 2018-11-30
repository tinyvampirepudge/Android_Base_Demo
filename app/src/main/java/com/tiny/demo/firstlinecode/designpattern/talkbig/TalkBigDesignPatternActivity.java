package com.tiny.demo.firstlinecode.designpattern.talkbig;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import com.tiny.demo.firstlinecode.R;
import com.tiny.demo.firstlinecode.base.BaseActivity;
import com.tiny.demo.firstlinecode.designpattern.talkbig.chapter01.SimpleFactoryActivity;
import com.tiny.demo.firstlinecode.designpattern.talkbig.chapter06.DecorateActivity;
import com.tiny.demo.firstlinecode.designpattern.talkbig.chapter13.BuilderActivity;
import com.tiny.demo.firstlinecode.designpattern.talkbig.chapter21.SingletonActivity;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * @Description: 大化设计模式学习
 * @Author wangjianzhou@qding.me
 * @Date 2018/11/30 1:54 PM
 * @Version
 */
public class TalkBigDesignPatternActivity extends BaseActivity {
    @BindView(R.id.btn_chapter6)
    Button btnChapter6;
    @BindView(R.id.btn_chapter21)
    Button btnChapter21;
    @BindView(R.id.btn_chapter13)
    Button btnChapter13;
    @BindView(R.id.btn_chapter1)
    Button btnChapter1;

    public static void actionStart(Context context) {
        Intent starter = new Intent(context, TalkBigDesignPatternActivity.class);
        context.startActivity(starter);
    }

    @Override
    protected int setContentLayout() {
        return R.layout.activity_talk_big_design_pattern;
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
