package com.tiny.demo.firstlinecode.javareference;

import android.view.View;
import android.widget.Button;

import com.tiny.demo.firstlinecode.R;
import com.tiny.demo.firstlinecode.base.BaseActivity;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Desc:    Java相关操作的入口。
 * Created by tiny on 2017/10/30.
 * Version:
 */

public class JavaReferenceActivity extends BaseActivity {
    @BindView(R.id.btn_java_concurrent_modification_exception)
    Button btnJavaConcurrentModificationException;
    @BindView(R.id.btn_java_deep_copy)
    Button btnJavaDeepCopy;
    @BindView(R.id.btn_java_basis)
    Button btnJavaBasis;

    @Override
    protected int setContentLayout() {
        return R.layout.activity_java_refercnce;
    }

    @Override
    protected void buildContentView() {

    }

    @Override
    protected void initViewData() {

    }

    @OnClick(R.id.btn_java_concurrent_modification_exception)
    public void onConcurrentModificationExceptionClicked() {
        //Java并发修改异常
        activitySwitch(ConcurrentModificationExceptionActivity.class);
    }

    @OnClick({R.id.btn_java_deep_copy, R.id.btn_java_basis, R.id.btn_java_singleton})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_java_deep_copy:
                activitySwitch(JavaDeepCopyActivity.class);
                break;
            case R.id.btn_java_basis:
                activitySwitch(JavaBasisActivity.class);
                break;
            case R.id.btn_java_singleton:
                activitySwitch(SingletonActivity.class);
                break;
        }
    }
}
