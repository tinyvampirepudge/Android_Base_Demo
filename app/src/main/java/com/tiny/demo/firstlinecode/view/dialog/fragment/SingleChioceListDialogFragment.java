package com.tiny.demo.firstlinecode.view.dialog.fragment;

import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;

import com.tiny.demo.firstlinecode.R;
import com.tiny.demo.firstlinecode.common.utils.ToastUtils;

/**
 * Desc: 永久性单选列表（单选按钮）
 * Created by tiny on 2017/9/24.
 * Version:
 */

public class SingleChioceListDialogFragment extends DialogFragment {
    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        String[] dataList = getActivity().getResources().getStringArray(R.array.toppings);
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        //Set the dialog title
        builder.setTitle(R.string.pick_toppings);
        //Specify the list array, the items to be selected by default (null or none),
        //and the listener through which to receive callbacks when items are selected
        builder.setSingleChoiceItems(dataList, 0, (dialog, which) -> {
            ToastUtils.showSingleToast("selected pos is " + which);
        });
        //Set the action buttons
        builder.setPositiveButton(R.string.ok, (dialog, which) -> {
            //User clicked OK, so save the mSelectedItems results somewhere
            //or return them to the component that opened the dialog
            ToastUtils.showSingleToast("OK");
        }).setNegativeButton(R.string.cancel, (dialog, which) -> {
            ToastUtils.showSingleToast("Canceled");
        });
        return builder.create();
    }
}
