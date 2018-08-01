package com.tiny.demo.firstlinecode.javareference;

import android.view.View;

import com.tiny.demo.firstlinecode.R;
import com.tiny.demo.firstlinecode.base.BaseActivity;
import com.tiny.demo.firstlinecode.javareference.copy.JavaCloneActivity1;
import com.tiny.demo.firstlinecode.javareference.copy.JavaCloneActivity2;
import com.tiny.demo.firstlinecode.javareference.copy.JavaCloneActivity3;
import com.tiny.demo.firstlinecode.javareference.copy.JavaCloneActivity4;
import com.tiny.demo.firstlinecode.javareference.copy.JavaCloneActivity5;
import com.tiny.demo.firstlinecode.javareference.copy.JavaCloneActivity6;
import com.tiny.demo.firstlinecode.javareference.copy.JavaCloneActivity7;

import butterknife.OnClick;

/**
 * Java深复制浅复制
 */
public class JavaDeepCopyActivity extends BaseActivity {

    @Override
    protected int setContentLayout() {
        return R.layout.activity_java_deep_copy;
    }

    @Override
    protected void buildContentView() {

    }

    @Override
    protected void initViewData() {

    }

    @OnClick(R.id.btn_java_deep_copy1)
    public void onJavaClone1Clicked() {
        activitySwitch(JavaCloneActivity1.class);
    }

    @OnClick(R.id.btn_java_deep_copy2)
    public void onJavaClone2Clicked() {
        activitySwitch(JavaCloneActivity2.class);
    }

    @OnClick(R.id.btn_java_deep_copy3)
    public void onJavaClone3Clicked() {
        activitySwitch(JavaCloneActivity3.class);
    }

    @OnClick({R.id.btn_java_deep_copy2, R.id.btn_java_deep_copy3, R.id.btn_java_deep_copy4, R.id.btn_java_deep_copy5, R.id.btn_java_deep_copy6, R.id.btn_java_deep_copy7})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_java_deep_copy4:
                activitySwitch(JavaCloneActivity4.class);
                break;
            case R.id.btn_java_deep_copy5:
                activitySwitch(JavaCloneActivity5.class);
                break;
            case R.id.btn_java_deep_copy6:
                activitySwitch(JavaCloneActivity6.class);
                break;
            case R.id.btn_java_deep_copy7:
                activitySwitch(JavaCloneActivity7.class);
                break;
        }
    }
}
