package com.tiny.demo.firstlinecode.view.dialog.fragment;

import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;

import com.tiny.demo.firstlinecode.R;
import com.tiny.demo.firstlinecode.common.utils.ToastUtils;

/**
 * Desc: google官网上的dialog示例。
 * 这个示例提供了三种按钮。
 * Created by tiny on 2017/9/23.
 * Version:
 */

public class FireMissilesDialogFragment extends DialogFragment {
    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        //Use the Builder class for convenient dialog construction
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle("DialogFragment AlertDialog")
                .setMessage(getString(R.string.dialog_fire_missiles))
                .setPositiveButton(R.string.fire, (dialog, which) -> ToastUtils.showSingleToast("DialogFragment AlertDialog Positive"))
                .setNegativeButton(R.string.cancel, (dialog, which) -> ToastUtils.showSingleToast("DialogFragment AlertDialog Negative"))
                .setNeutralButton("中性按钮", (dialog, which) -> ToastUtils.showSingleToast("DialogFragment AlertDialog Neutral"));
        //Create the AlertDialog object and return it.
        return builder.create();
    }
}
