package com.tiny.demo.firstlinecode.view.dialog.fragment;

import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;

import com.tiny.demo.firstlinecode.R;
import com.tiny.demo.firstlinecode.common.utils.ToastUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Desc: 永久性多选列表（复选框）
 * Created by tiny on 2017/9/24.
 * Version:
 */

public class MutilChioceListDialogFragment extends DialogFragment {

    private List<String> mSelectedItems;

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        //Where we track the selected items
        mSelectedItems = new ArrayList<>();
        String[] dataList = getActivity().getResources().getStringArray(R.array.toppings);
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        //Set the dialog title
        builder.setTitle(R.string.pick_toppings);
        //Specify the list array, the items to be selected by default (null or none),
        //and the listener through which to receive callbacks when items are selected
        builder.setMultiChoiceItems(dataList, null, (dialog, which, isChecked) -> {
            if (isChecked) {
                // If the user checked the item, add it to the selected items
                mSelectedItems.add(dataList[which]);
            } else {
                //Else, if the item is already in the array, remove it
                mSelectedItems.remove(dataList[which]);
            }
        });
        //Set the action buttons
        builder.setPositiveButton(R.string.ok, (dialog, which) -> {
            //User clicked OK, so save the mSelectedItems results somewhere
            //or return them to the component that opened the dialog
            ToastUtils.showSingleToast("selected items is " + mSelectedItems.toString());
        }).setNegativeButton(R.string.cancel, (dialog, which) -> {
            ToastUtils.showSingleToast("Canceled");
        });
        return builder.create();
    }
}
