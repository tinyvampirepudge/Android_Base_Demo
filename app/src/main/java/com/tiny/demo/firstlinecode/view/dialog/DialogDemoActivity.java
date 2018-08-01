package com.tiny.demo.firstlinecode.view.dialog;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.tiny.demo.firstlinecode.base.BaseActivity;
import com.tiny.demo.firstlinecode.R;
import com.tiny.demo.firstlinecode.common.utils.ToastUtils;
import com.tiny.demo.firstlinecode.view.dialog.fragment.CustomDialogFragment;
import com.tiny.demo.firstlinecode.view.dialog.fragment.CustomViewDialogFragment;
import com.tiny.demo.firstlinecode.view.dialog.fragment.FireMissilesDialogFragment;
import com.tiny.demo.firstlinecode.view.dialog.fragment.MutilChioceListDialogFragment;
import com.tiny.demo.firstlinecode.view.dialog.fragment.NoticeDialogFragment;
import com.tiny.demo.firstlinecode.view.dialog.fragment.SimpleListDialogFragment;
import com.tiny.demo.firstlinecode.view.dialog.fragment.SingleChioceListDialogFragment;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Desc: 调用系统对话框的代码演示
 * 对话框Google官网地址：https://developer.android.com/guide/topics/ui/dialogs.html
 * <p>
 * Created by tiny on 2017/9/21.
 * Version:
 */
public class DialogDemoActivity extends BaseActivity implements NoticeDialogFragment.NoticeDialogListener {

    @BindView(R.id.btn_standard_dialog)
    Button btnStandardDialog;
    @BindView(R.id.btn_custom_dialog)
    Button btnCustomDialog;
    @BindView(R.id.btn_standard_alert_dialog)
    Button btnStandardAlertDialog;
    @BindView(R.id.btn_custom_alert_dialog)
    Button btnCustomAlertDialog;
    @BindView(R.id.btn_fragment_dialog)
    Button btnFragmentDialog;
    @BindView(R.id.btn_fragment_dialog_single_list1)
    Button btnFragmentDialogSingleList1;
    @BindView(R.id.btn_fragment_dialog_simple_list2)
    Button btnFragmentDialogSimpleList2;
    @BindView(R.id.btn_fragment_dialog_list_single_choice)
    Button btnFragmentDialogListSingleChoice;
    @BindView(R.id.btn_fragment_dialog_list_multi_choice)
    Button btnFragmentDialogListMultiChoice;
    @BindView(R.id.btn_fragment_dialog_custom_view)
    Button btnFragmentDialogCustomView;
    @BindView(R.id.btn_activity_dialog)
    Button btnActivityDialog;
    @BindView(R.id.btn_fragment_dialog_notice)
    Button btnFragmentDialogNotice;
    @BindView(R.id.btn_fragment_dialog_custom)
    Button btnFragmentDialogCustom;
    private boolean mIsLargeLayout;

    @Override
    protected int setContentLayout() {
        return R.layout.activity_dialog;
    }

    @Override
    protected void buildContentView() {
        /**
         * Dialog 类是对话框的基类，但您应该避免直接实例化 Dialog，而是使用下列子类之一：
         * ①AlertDialog，此对话框可显示标题、最多三个按钮、可选择项列表或自定义布局。
         * ②DatePickerDialog 或 TimePickerDialog，此对话框带有允许用户选择日期或时间的预定义 UI。
         * ③ProgressDialog，可显示具有进度条的对话框。
         * Dialog默认对输入法有影响：https://developer.android.com/reference/android/app/Dialog.html
         * AlertDialog默认对输入法有影响：https://developer.android.com/reference/android/app/AlertDialog.html
         */

        /**
         * Dialog类不推荐直接使用，Google推荐使用它的子类。
         * ①如果使用Dialog时，必须通过setContentView(view)方法来给它设置view，不然不会有view显示出来的。
         * ②AlertDialog中，是通过setView(view)方法来给它设置view的，
         */

        mIsLargeLayout = getResources().getBoolean(R.bool.large_layout);
    }

    @Override
    protected void initViewData() {

    }

    @OnClick(R.id.btn_standard_dialog)
    public void onBtnStandardDialogClicked() {
//        Dialog dialog = new Dialog(mContext);
//        dialog.setTitle("standard dialog");
//        dialog.setOnCancelListener(dialog1 -> {
//            ToastUtils.showSingleToast("standard dialog canceled");
//        });
//        dialog.setCancelable(true);
//        dialog.setCanceledOnTouchOutside(true);
//        dialog.show();
//        Window window = getWindow();
//        TextView textView = new TextView(mContext);
//        textView.setText("我是标准Dialog的内部布局。");
//        window.setContentView(textView);//将view设置给window.
//        WindowManager.LayoutParams params = window.getAttributes();
//        params.gravity = Gravity.CENTER;
//        params.alpha = 1.0f;
//        params.width = (int) (ScreenUtils.getScreenW(mContext) * 0.8f);
//        params.height = ScreenUtils.dip2px(mContext, 500f);
//        window.setAttributes(params);
    }

    @OnClick(R.id.btn_custom_dialog)
    public void onBtnCustomDialogClicked() {
        Dialog dialog = new Dialog(mContext);
        dialog.setCancelable(true);
        dialog.setCanceledOnTouchOutside(true);
        View view = LayoutInflater.from(mContext).inflate(R.layout.dialog_custom, null);
        dialog.setContentView(view);
        TextView btnCancel = (TextView) view.findViewById(R.id.btn_dialog_custom_cancel);
        TextView btnOk = (TextView) view.findViewById(R.id.btn_dialog_custom_ok);
        btnCancel.setOnClickListener(v -> {
            ToastUtils.showSingleToast("Custom Dialog Canceled");
            if (dialog != null && dialog.isShowing()) {
                dialog.dismiss();
            }
        });

        btnOk.setOnClickListener(v -> {
            ToastUtils.showSingleToast("Custom Dialog Confirmed");
            if (dialog != null && dialog.isShowing()) {
                dialog.dismiss();
            }
        });
        dialog.show();
    }

    @OnClick(R.id.btn_standard_alert_dialog)
    public void onBtnStandardAlertDialogClicked() {
        // Use the Builder class for convenient dialog construction
        AlertDialog.Builder builder = new AlertDialog.Builder(mContext);
        builder.setMessage(R.string.dialog_fire_missiles)
                .setPositiveButton(R.string.fire, (dialog, id) -> {
                    // FIRE ZE MISSILES!
                    ToastUtils.showSingleToast("FIRE ZE MISSILES!");
                })
                .setNegativeButton(R.string.cancel, (dialog, id) -> {
                    // User cancelled the dialog
                    ToastUtils.showSingleToast("User cancelled the dialog");
                });
        // Create the AlertDialog object
        builder.create();
        builder.show();
    }

    @OnClick(R.id.btn_custom_alert_dialog)
    public void onBtnCustomAlertDialogClicked() {
        TextViewDialog textViewDialog = new TextViewDialog.Builder(mContext)
                .setTitleStr("Title")
                .setDescStr1("自定义的AlertDialog的desc")
                .setOkStr("确定")
                .setOkListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        ToastUtils.showSingleToast("点击了确定按钮");
                    }
                })
                .setCancelStr("取消")
                .setCancelListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        ToastUtils.showSingleToast("点击了取消按钮");
                    }
                })
                .setShowTopLine(false)
                .build();
        textViewDialog.showDialog((Activity) mContext);
    }

    @OnClick(R.id.btn_fragment_dialog)
    public void onViewClicked() {
        DialogFragment dialogFragment = new FireMissilesDialogFragment();
        dialogFragment.show(getSupportFragmentManager(), dialogFragment.getClass().getSimpleName());
    }

    @OnClick(R.id.btn_fragment_dialog_single_list1)
    public void onBtnFragmentDialogSingleList1Clicked() {
        //simple list on DialogFragment setItems
        DialogFragment dialogFragment = new SimpleListDialogFragment();
        Bundle bundle = new Bundle();
        bundle.putBoolean(SimpleListDialogFragment.FLAG, true);
        dialogFragment.setArguments(bundle);
        dialogFragment.show(getSupportFragmentManager(), dialogFragment.getClass().getSimpleName());
    }

    @OnClick(R.id.btn_fragment_dialog_simple_list2)
    public void onBtnFragmentDialogSimpleList2Clicked() {
        //simple list on DialogFragment setAdapter
        DialogFragment dialogFragment = new SimpleListDialogFragment();
        Bundle bundle = new Bundle();
        bundle.putBoolean(SimpleListDialogFragment.FLAG, false);
        dialogFragment.setArguments(bundle);
        dialogFragment.show(getSupportFragmentManager(), dialogFragment.getClass().getSimpleName());
    }

    @OnClick(R.id.btn_fragment_dialog_list_single_choice)
    public void onBtnFragmentDialogListSingleChoiceClicked() {
        DialogFragment dialogFragment = new SingleChioceListDialogFragment();
        dialogFragment.show(getSupportFragmentManager(), dialogFragment.getClass().getSimpleName());
    }

    @OnClick(R.id.btn_fragment_dialog_list_multi_choice)
    public void onBtnFragmentDialogListMutilChoiceClicked() {
        DialogFragment dialogFragment = new MutilChioceListDialogFragment();
        dialogFragment.show(getSupportFragmentManager(), dialogFragment.getClass().getSimpleName());
    }

    @OnClick(R.id.btn_fragment_dialog_custom_view)
    public void onBtnFragmentDialogCustomViewClicked() {
        DialogFragment dialogFragment = new CustomViewDialogFragment();
        dialogFragment.show(getSupportFragmentManager(), dialogFragment.getClass().getSimpleName());
    }

    @OnClick(R.id.btn_activity_dialog)
    public void onBtnActivityDialogClicked() {
        startActivity(new Intent(mContext, DialogActivity.class));
    }

    @OnClick(R.id.btn_fragment_dialog_notice)
    public void onBtnFragmentDialogNoticeClicked() {
        // Create an instance of the dialog fragment and show it
        DialogFragment dialogFragment = new NoticeDialogFragment();
        dialogFragment.show(getSupportFragmentManager(), dialogFragment.getClass().getSimpleName());
    }


    // The dialog fragment receives a reference to this Activity through the
    // Fragment.onAttach() callback, which it uses to call the following methods
    // defined by the NoticeDialogFragment.NoticeDialogListener interface
    @Override
    public void onDialogPositiveClick(DialogFragment dialog) {
        // User touched the dialog's positive button
        ToastUtils.showSingleToast("Activity里面的回调，Positive Clicked");
    }

    @Override
    public void onDialogNegativeClick(DialogFragment dialog) {
        // User touched the dialog's negative button
        ToastUtils.showSingleToast("Activity里面的回调，Negative Clicked");
    }

    @OnClick(R.id.btn_fragment_dialog_custom)
    public void onBtnFragmentDialogCustomClicked() {
        showDialog();
    }

    public void showDialog() {
        FragmentManager fragmentManager = getSupportFragmentManager();
        CustomDialogFragment newFragment = new CustomDialogFragment();

        if (mIsLargeLayout) {
            // The device is using a large layout, so show the fragment as a dialog
            newFragment.show(fragmentManager, "dialog");
        } else {
            // The device is smaller, so show the fragment fullscreen
            FragmentTransaction transaction = fragmentManager.beginTransaction();
            // For a little polish, specify a transition animation
            transaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
            // To make it fullscreen, use the 'content' root view as the container
            // for the fragment, which is always the root view for the activity
            transaction.add(android.R.id.content, newFragment)
                    .addToBackStack(null).commit();
        }
    }
}
