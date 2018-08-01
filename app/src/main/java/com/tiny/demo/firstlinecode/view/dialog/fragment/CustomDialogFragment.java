package com.tiny.demo.firstlinecode.view.dialog.fragment;

import android.app.Dialog;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;

import com.tiny.demo.firstlinecode.R;

/**
 * Desc: 全屏显示对话框或将其显示为嵌入式片段
 * 您可能采用以下 UI 设计：您想让一部分 UI 在某些情况下显示为对话框，但在其他情况下全屏显示或显示为嵌入式片段（也许取决于设备使用大屏幕还是小屏幕）。
 * DialogFragment 类便具有这种灵活性，因为它仍然可以充当嵌入式 Fragment。
 * 但在这种情况下，您不能使用 AlertDialog.Builder 或其他 Dialog 对象来构建对话框。
 * 如果您想让 DialogFragment 具有嵌入能力，则必须在布局中定义对话框的 UI，然后在 onCreateView() 回调中加载布局。
 * 以下示例 DialogFragment 可以显示为对话框或嵌入式片段（使用名为 purchase_items.xml 的布局）：
 * Created by tiny on 2017/9/24.
 * Version:
 */

public class CustomDialogFragment extends DialogFragment {

    /**
     * The system calls this to get the DialogFragment's layout, regardless
     * of whether it's being displayed as a dialog or an embedded fragment.
     */
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout to use as dialog or embedded fragment
        return inflater.inflate(R.layout.purchase_items, container, false);
    }

    /**
     * The system calls this only when creating the layout in a dialog.
     */
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        // The only reason you might override this method when using onCreateView() is
        // to modify any dialog characteristics. For example, the dialog includes a
        // title by default, but your custom layout might not need it. So here you can
        // remove the dialog title, but you must call the superclass to get the Dialog.
        Dialog dialog = super.onCreateDialog(savedInstanceState);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);

        return dialog;
    }
}
