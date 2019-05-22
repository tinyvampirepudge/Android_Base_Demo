package com.tiny.demo.firstlinecode.javareference;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.tiny.demo.firstlinecode.R;
import com.tiny.demo.firstlinecode.base.BaseActivity;
import com.tiny.demo.firstlinecode.javareference.async2sync.AsyncToSyncActivity;
import com.tiny.demo.firstlinecode.javareference.clazz.ClazzActivity;
import com.tiny.demo.firstlinecode.javareference.reflect.JavaReflectActivity;
import com.tiny.demo.firstlinecode.javareference.async2sync.FutureTaskActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
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
    @BindView(R.id.btn_java_reflect)
    Button btnJavaReflect;
    @BindView(R.id.btn_java_singleton)
    Button btnJavaSingleton;
    @BindView(R.id.btn_java_clazz)
    Button btnJavaClazz;

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    @OnClick(R.id.btn_java_reflect)
    public void onViewClicked() {
        JavaReflectActivity.actionStart(this, null);
    }

    @OnClick(R.id.btn_java_clazz)
    public void onViewJavaClazzClicked() {
        ClazzActivity.Companion.actionStart(this, null);
    }


    @OnClick(R.id.btn_java_async_to_sync)
    public void onViewJavaAsyncToSyncClicked() {
        AsyncToSyncActivity.actionStart(this);
    }
}
