package com.tiny.demo.firstlinecode.view.dialog.fragment;

import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import com.tiny.demo.firstlinecode.R;
import com.tiny.demo.firstlinecode.common.utils.ToastUtils;

/**
 * Desc: AlertDialog展示列表。
 * 传统单选列表
 * Created by tiny on 2017/9/23.
 * Version:
 */

public class SimpleListDialogFragment extends DialogFragment {
    public static final String FLAG = "FLAG";
    private boolean flag;//true表示setItems,false表示setAdapter

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (getArguments() != null) {
            flag = getArguments().getBoolean(FLAG, false);
        }
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        /**
         *
         可通过 AlertDialog API 提供三种列表：
         传统单选列表
         永久性单选列表（单选按钮）
         永久性多选列表（复选框）
         要想创建如图 3 所示的单选列表，请使用 setItems() 方法：
         */
        String[] colors = getActivity().getResources().getStringArray(R.array.colors_array);
        String[] colorsAdapter = getActivity().getResources().getStringArray(R.array.colors_array_adapter);
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle(R.string.pick_color);
        if (flag) {
            builder.setItems(colors, (dialog, which) -> {
                //The 'which' argument contains the index position of the selected item
                ToastUtils.showSingleToast("The picked color is " + colors[which]);
            });
        } else {
            builder.setAdapter(new ArrayAdapter<>(getActivity(), android.R.layout.simple_list_item_1, colorsAdapter), (dialog, which) -> {
                ToastUtils.showSingleToast("The picked color is " + colorsAdapter[which]);
            });
        }
        return builder.create();
    }
}
