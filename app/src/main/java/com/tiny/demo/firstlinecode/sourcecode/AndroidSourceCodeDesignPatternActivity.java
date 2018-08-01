package com.tiny.demo.firstlinecode.sourcecode;

import android.content.Intent;
import android.widget.Button;

import com.tiny.demo.firstlinecode.base.BaseActivity;
import com.tiny.demo.firstlinecode.R;
import com.tiny.demo.firstlinecode.sourcecode.chapter1.Chapter1Activity;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Android源码设计模式解析与实战
 */
public class AndroidSourceCodeDesignPatternActivity extends BaseActivity {

    @BindView(R.id.btn_six_principle_of_object_oriented)
    Button btnChapter1;

    @Override
    protected int setContentLayout() {
        return R.layout.activity_android_source_code_design_pattern;
    }

    @Override
    protected void buildContentView() {

    }

    @Override
    protected void initViewData() {

    }

    @OnClick(R.id.btn_six_principle_of_object_oriented)
    public void onViewClicked() {
        startActivity(new Intent(mContext, Chapter1Activity.class));
    }
}
