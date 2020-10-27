package com.tiny.demo.firstlinecode.activity.on_new_intent;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.tiny.demo.firstlinecode.R;
import com.tiny.demo.firstlinecode.common.utils.DialogUtils;
import com.tiny.demo.firstlinecode.common.utils.ToastUtils;
import com.tinytongtong.tinyutils.LogUtils;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * @Description: onNewIntent
 * Dialog
 * @Author wangjianzhou@qding.me
 * @Date 2019-09-04 12:04
 * @Version
 */
public class OnNewIntentActivity extends AppCompatActivity {
    public static final String TAG = OnNewIntentActivity.class.getSimpleName();
    @BindView(R.id.btn_test_01)
    Button btnTest01;
    @BindView(R.id.btn_test_02)
    Button btnTest02;
    @BindView(R.id.btn_test_03)
    Button btnTest03;
    @BindView(R.id.btn_test_04)
    Button btnTest04;
    @BindView(R.id.btn_test_05)
    Button btnTest05;
    @BindView(R.id.btn_test_06)
    Button btnTest06;

    public static void actionStart(Context context) {
        Intent starter = new Intent(context, OnNewIntentActivity.class);
        context.startActivity(starter);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        LogUtils.INSTANCE.e(TAG, "onCreate");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_on_new_intent);
        ButterKnife.bind(this);
    }

    @Override
    protected void onStart() {
        super.onStart();
        LogUtils.INSTANCE.e(TAG, "onStart");
    }

    @Override
    protected void onResume() {
        super.onResume();
        LogUtils.INSTANCE.e(TAG, "onResume");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        LogUtils.INSTANCE.e(TAG, "onRestart");
    }

    @Override
    protected void onPause() {
        super.onPause();
        LogUtils.INSTANCE.e(TAG, "onPause");
    }

    @Override
    protected void onStop() {
        super.onStop();
        LogUtils.INSTANCE.e(TAG, "onStop");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        LogUtils.INSTANCE.e(TAG, "onDestroy");
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        LogUtils.INSTANCE.e(TAG, "onNewIntent");
    }

    @OnClick(R.id.btn_test_01)
    public void onBtnTest01Clicked() {
        OnNewIntentTest1Activity.actionStart(this);
    }

    @OnClick(R.id.btn_test_02)
    public void onBtnTest02Clicked() {
        OnNewIntentActivity.actionStart(this);
    }

    @OnClick(R.id.btn_test_03)
    public void onViewClicked() {
        DialogUtils.showNormalDialog(this, "OnNewIntent测试", "我就是测试一下", "ojbk", new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ToastUtils.showSingleToast("你很帅气");
            }
        });
    }


    @OnClick(R.id.btn_test_04)
    public void onBtnTest04Clicked() {
        showAlertDialogV7();
    }

    @OnClick(R.id.btn_test_05)
    public void onBtnTest05Clicked() {
        showAlertDialog();
    }

    @OnClick(R.id.btn_test_06)
    public void onBtnTest06Clicked() {
        DialogStyleActivity.actionStart(this);
    }

    private void showAlertDialogV7() {
        android.support.v7.app.AlertDialog alertDialogV7 = new android.support.v7.app.AlertDialog.Builder(this)
                .setTitle("OnNewIntent测试")
                .setMessage("我就是测试一下")
                .setNegativeButton("cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                        ToastUtils.showSingleToast("放肆，竟然敢点击取消");
                    }
                })
                .setPositiveButton("ojbk", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                        ToastUtils.showSingleToast("你很帅气");
                    }
                })
                .setCancelable(false)
                .create();
        alertDialogV7.setCanceledOnTouchOutside(false);
        alertDialogV7.show();
    }

    private void showAlertDialog() {
        AlertDialog alertDialog = new AlertDialog.Builder(this)
                .setTitle("OnNewIntent测试")
                .setMessage("我就是测试一下")
                .setNegativeButton("cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                        ToastUtils.showSingleToast("放肆，竟然敢点击取消");
                    }
                })
                .setPositiveButton("ojbk", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                        ToastUtils.showSingleToast("你很帅气");
                    }
                })
                .setCancelable(false)
                .create();
        alertDialog.setCanceledOnTouchOutside(false);
        alertDialog.show();
    }
}
