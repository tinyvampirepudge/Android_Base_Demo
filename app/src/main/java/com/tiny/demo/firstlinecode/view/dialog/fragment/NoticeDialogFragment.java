package com.tiny.demo.firstlinecode.view.dialog.fragment;

import android.app.Activity;
import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;

import com.tiny.demo.firstlinecode.R;

/**
 * Desc: 将事件传递回对话框的宿主
 * 当用户触摸对话框的某个操作按钮或从列表中选择某一项时，您的 DialogFragment 可能会自行执行必要的操作，
 * 但通常您想将事件传递给打开该对话框的 Activity 或片段。 为此，请定义一个界面，为每种点击事件定义一种方法。
 * 然后在从该对话框接收操作事件的宿主组件中实现该界面。
 * Created by tiny on 2017/9/24.
 * Version:
 */

public class NoticeDialogFragment extends DialogFragment {
    /* The activity that creates an instance of this dialog fragment must
     * implement this interface in order to receive event callbacks.
     * Each method passes the DialogFragment in case the host needs to query it. */
    public interface NoticeDialogListener {
        void onDialogPositiveClick(DialogFragment dialog);

        void onDialogNegativeClick(DialogFragment dialog);
    }

    // Use this instance of the interface to deliver action events
    NoticeDialogListener mListener;

    // Override the Fragment.onAttach() method to instantiate the NoticeDialogListener
    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        // Verify that the host activity implements the callback interface
        try {
            // Instantiate the NoticeDialogListener so we can send events to the host
            mListener = (NoticeDialogListener) activity;
        } catch (ClassCastException e) {
            // The activity doesn't implement the interface, throw exception
            throw new ClassCastException(activity.toString() + " must implement NoticeDialogListener");
        }
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        // Build the dialog and set up the button click handlers
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle("NoticeDialogFragment")
                .setMessage("This is NoticeDialogFragment's Message.")
                .setPositiveButton(R.string.ok, (dialog, which) -> {
                    // Send the positive button event back to the host activity
                    if (mListener != null) {
                        mListener.onDialogPositiveClick(NoticeDialogFragment.this);
                    }
                })
                .setNegativeButton(R.string.cancel, (dialog, which) -> {
                    // Send the negative button event back to the host activity
                    if (mListener != null) {
                        mListener.onDialogNegativeClick(NoticeDialogFragment.this);
                    }
                });
        return builder.create();
    }
}
