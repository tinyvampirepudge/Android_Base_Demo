package com.tiny.demo.firstlinecode.designpattern.chapter21;

import android.os.Bundle;
import android.widget.Button;

import com.tiny.demo.firstlinecode.base.BaseActivity;
import com.tiny.demo.firstlinecode.R;

import butterknife.BindView;
import butterknife.ButterKnife;
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
}
