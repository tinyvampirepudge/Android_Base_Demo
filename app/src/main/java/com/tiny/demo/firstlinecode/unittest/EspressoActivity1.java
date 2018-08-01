package com.tiny.demo.firstlinecode.unittest;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import com.tiny.demo.firstlinecode.base.BaseActivity;
import com.tiny.demo.firstlinecode.R;
import com.tiny.demo.firstlinecode.common.utils.ToastUtils;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Desc:    androidTest 1
 * Created by tiny on 2017/10/11.
 * Version:
 */

public class EspressoActivity1 extends BaseActivity {
    @BindView(R.id.btn_auto_test1_first)
    Button btnAutoTest1First;
    @BindView(R.id.txt_auto_test1_first)
    TextView txtAutoTest1First;
    @BindView(R.id.btn_auto_test1_show_toast)
    Button btnAutoTest1ShowToast;

    @Override
    protected int setContentLayout() {
        return R.layout.activity_espresso1;
    }

    @Override
    protected void buildContentView() {

    }

    @Override
    protected void initViewData() {

    }

    @OnClick(R.id.btn_auto_test1_first)
    public void onBtnAutoTest1FirstClicked() {
    }

    @OnClick(R.id.btn_auto_test1_show_toast)
    public void onBtnAutoTest1ShowToastClicked() {
        ToastUtils.showSingleToast(getString(R.string.show_toast_en));
    }
}
