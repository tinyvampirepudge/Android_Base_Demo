package com.tiny.demo.firstlinecode.view.dialog.fragment;

import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;

import com.tiny.demo.firstlinecode.R;
import com.tiny.demo.firstlinecode.common.utils.ToastUtils;

/**
 * Desc:
 * 创建自定义布局
 * 如果您想让对话框具有自定义布局，请创建一个布局，然后通过调用 AlertDialog.Builder 对象上的 setView() 将其添加到 AlertDialog。
 * 默认情况下，自定义布局会填充对话框窗口，但您仍然可以使用 AlertDialog.Builder 方法来添加按钮和标题
 * Created by tiny on 2017/9/24.
 * Version:
 */

public class CustomViewDialogFragment extends DialogFragment {
    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        // Get the layout inflater
        LayoutInflater inflater = getActivity().getLayoutInflater();

        // Inflate and set the layout for the dialog
        // Pass null as the parent view because its going in the dialog
        builder.setView(inflater.inflate(R.layout.dialog_signin, null));
        //Add action buttons
        builder.setPositiveButton(R.string.signin, (dialog, which) -> {
            // sign in the user ...
            ToastUtils.showSingleToast("Sign In");
        }).setNegativeButton(R.string.cancel, (dialog, which) -> {
            ToastUtils.showSingleToast("Canceled");
        });
        return builder.create();
    }
}
