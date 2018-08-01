package com.tiny.demo.firstlinecode.smarttoast;

import android.os.Bundle;
import android.view.Gravity;
import android.widget.Button;

import com.coder.zzq.smartshow.toast.SmartToast;
import com.tiny.demo.firstlinecode.R;
import com.tiny.demo.firstlinecode.base.BaseActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * SmartToast工具库的
 */
public class SmartToastActivity extends BaseActivity {

    @BindView(R.id.btn_smart_toast_test1)
    Button btnSmartToastTest1;
    @BindView(R.id.btn_smart_toast_test2)
    Button btnSmartToastTest2;
    @BindView(R.id.btn_smart_toast_test3)
    Button btnSmartToastTest3;
    @BindView(R.id.btn_smart_toast_test4)
    Button btnSmartToastTest4;
    @BindView(R.id.btn_smart_toast_test5)
    Button btnSmartToastTest5;
    @BindView(R.id.btn_smart_toast_test6)
    Button btnSmartToastTest6;
    @BindView(R.id.btn_smart_toast_test7)
    Button btnSmartToastTest7;
    @BindView(R.id.btn_smart_toast_test8)
    Button btnSmartToastTest8;
    @BindView(R.id.btn_smart_toast_test9)
    Button btnSmartToastTest9;

    @Override
    protected int setContentLayout() {
        return R.layout.activity_smart_toast;
    }

    @Override
    protected void buildContentView() {

    }

    @Override
    protected void initViewData() {

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    @OnClick(R.id.btn_smart_toast_test1)
    public void onBtnSmartToastTest1Clicked() {
        SmartToast.show(btnSmartToastTest1.getText().toString());
    }

    @OnClick(R.id.btn_smart_toast_test2)
    public void onBtnSmartToastTest2Clicked() {
        SmartToast.showAtTop(btnSmartToastTest2.getText().toString());
    }

    @OnClick(R.id.btn_smart_toast_test3)
    public void onBtnSmartToastTest3Clicked() {
        SmartToast.showInCenter(btnSmartToastTest3.getText().toString());
    }

    @OnClick(R.id.btn_smart_toast_test4)
    public void onBtnSmartToastTest4Clicked() {
        SmartToast.showAtLocation(btnSmartToastTest4.getText().toString(), Gravity.TOP, 0, 0);
    }

    @OnClick(R.id.btn_smart_toast_test5)
    public void onBtnSmartToastTest5Clicked() {
        SmartToast.showLong(btnSmartToastTest5.getText().toString());
    }

    @OnClick(R.id.btn_smart_toast_test6)
    public void onBtnSmartToastTest6Clicked() {
        SmartToast.showLongAtTop(btnSmartToastTest6.getText().toString());
    }

    @OnClick(R.id.btn_smart_toast_test7)
    public void onBtnSmartToastTest7Clicked() {
        SmartToast.showLongInCenter(btnSmartToastTest7.getText().toString());
    }

    @OnClick(R.id.btn_smart_toast_test8)
    public void onBtnSmartToastTest8Clicked() {
        SmartToast.showLongAtLocation(btnSmartToastTest8.getText().toString(),
                Gravity.LEFT | Gravity.CENTER_VERTICAL, 0, 0);
    }

    @OnClick(R.id.btn_smart_toast_test9)
    public void onBtnSmartToastTest9Clicked() {
        SmartToast.dismiss();
    }
}
