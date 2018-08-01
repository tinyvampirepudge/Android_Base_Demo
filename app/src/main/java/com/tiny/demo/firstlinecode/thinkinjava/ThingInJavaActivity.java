package com.tiny.demo.firstlinecode.thinkinjava;

import android.widget.Button;

import com.tiny.demo.firstlinecode.R;
import com.tiny.demo.firstlinecode.base.BaseActivity;
import com.tiny.demo.firstlinecode.thinkinjava.annotation.AnnotationActivity;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Think In Java 入口
 */
public class ThingInJavaActivity extends BaseActivity {

    @BindView(R.id.btn_annotation)
    Button btnAnnotation;
    @BindView(R.id.btn_annotation1)
    Button btnAnnotation1;

    @Override
    protected int setContentLayout() {
        return R.layout.activity_thing_in_java;
    }

    @Override
    protected void buildContentView() {

    }

    @Override
    protected void initViewData() {

    }

    @OnClick(R.id.btn_annotation)
    public void onBtnAnnotationClicked() {
        activitySwitch(AnnotationActivity.class);
    }

    @OnClick(R.id.btn_annotation1)
    public void onBtnAnnotation1Clicked() {
    }
}
