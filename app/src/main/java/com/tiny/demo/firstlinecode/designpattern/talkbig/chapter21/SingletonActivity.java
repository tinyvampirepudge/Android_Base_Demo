package com.tiny.demo.firstlinecode.designpattern.talkbig.chapter21;

import android.content.Intent;
import android.os.Process;
import android.util.Log;
import android.widget.Button;

import com.tiny.demo.firstlinecode.base.BaseActivity;
import com.tiny.demo.firstlinecode.R;
import com.tiny.demo.firstlinecode.common.utils.ProcessUtil;
import com.tinytongtong.tinyutils.LogUtils;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Desc:    单例模式
 * Created by tiny on 2017/10/19.
 * Version:
 */

public class SingletonActivity extends BaseActivity {
    @BindView(R.id.btn_singleton_lhs)
    Button btnSingletonLhs;
    @BindView(R.id.btn_singleton_ehs)
    Button btnSingletonEhs;

    @Override
    protected int setContentLayout() {
        return R.layout.activity_singleton;
    }

    @Override
    protected void buildContentView() {

    }

    @Override
    protected void initViewData() {

    }

    @OnClick(R.id.btn_singleton_lhs)
    public void onBtnSingletonLhsClicked() {
        SingletonLhs.getInstance().showInfo();
    }

    @OnClick(R.id.btn_singleton_ehs)
    public void onBtnSingletonEhsClicked() {
        SingletonEhs.getInstance().showInfo();
    }

    @OnClick(R.id.btn_singleton_safe_dcl)
    public void onBtnSingletonSafeDCLClicked() {
        //进程名称
        String processName = ProcessUtil.getProcessNameByCtx(this, Process.myPid());
        LogUtils.INSTANCE.e(TAG, "processName000:" + processName);
        SafeDoubleCheckedLocking.getInstance().addValue();
        SafeDoubleCheckedLocking.getInstance().addValue();
        LogUtils.INSTANCE.e(TAG, "SafeDoubleCheckedLocking.getInstance().getValue()111:" + SafeDoubleCheckedLocking.getInstance().getValue());

        startActivity(new Intent(this, SingletonOtherProcessTestActivity.class));
    }

    @OnClick(R.id.btn_singleton_static_inner_class)
    public void onBtnSingletonStaticInnerClassClicked() {
        //进程名称
        String processName = ProcessUtil.getProcessNameByCtx(this, Process.myPid());
        LogUtils.INSTANCE.e(TAG, "processName111:" + processName);
        InstanceFactory.getInstance().addValue();
        InstanceFactory.getInstance().addValue();
        LogUtils.INSTANCE.e(TAG, "InstanceFactory.getInstance().getValue()111:" + InstanceFactory.getInstance().getValue());

        startActivity(new Intent(this, SingletonOtherProcessTestActivity.class));
    }
}
