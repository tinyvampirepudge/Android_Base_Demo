package com.tiny.demo.firstlinecode.common.utils;

import android.app.AlertDialog;
import android.content.Context;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

import com.tiny.demo.firstlinecode.R;
import com.tinytongtong.tinyutils.ScreenUtils;

/**
 * Created by tiny on 16/12/19.
 * 对 框工具类
 */
public class DialogUtils {
    //一般类型的对话框的屏幕宽度的比例。
    public static final float widthRatio = 6 / 7f;

    /**
     * 支持强制更新对 框，拨打电 对 框，取消  个股、营业 对 框、开启权 对 框。
     *
     * @param context
     * @param titleStr
     * @param descStr1
     * @param okStr
     * @param okListener
     */
    public static void showNormalDialog(final Context context, CharSequence titleStr,
                                        CharSequence descStr1, CharSequence okStr,
                                        final View.OnClickListener okListener) {
        showBaseDialog(context, titleStr, descStr1, null, okStr, okListener,
                null, null, false, true, false);
    }

    /**
     * @param context
     * @param titleStr
     * @param descStr1
     * @param descStr2
     * @param okStr
     * @param okListener
     */
    //显示龙虎币订阅样式的dialog, 可修改确定按钮的文本颜色。
    public static void showBaseDialog(final Context context, CharSequence titleStr,
                                      CharSequence descStr1, CharSequence descStr2,
                                      CharSequence okStr, final View.OnClickListener okListener) {
        showBaseDialog(context, titleStr, descStr1, descStr2, okStr, okListener,
                null, null, false, true, false);
    }

    //定义一个通用的对话框，可以设置所有属性。
    //TextView的属性设置，交由SpannableStringBuilder解决。
    public static void showBaseDialog(final Context context, CharSequence titleStr,
                                      CharSequence descStr1, CharSequence descStr2,
                                      CharSequence okStr, final View.OnClickListener okListener,
                                      CharSequence cancelStr, final View.OnClickListener cancelListener,
                                      boolean showTopLine, boolean cancelable, boolean setAnim) {
        if (context == null) {
            return;
        }
        View view = LayoutInflater.from(context).inflate(R.layout.layout_dialog_common, null);
        TextView title = view.findViewById(R.id.txt_dialog_title);
        View lineTop = view.findViewById(R.id.line_dialog_top);
        TextView desc1 = view.findViewById(R.id.txt_dialog_desc1);
        TextView desc2 = view.findViewById(R.id.txt_dialog_desc2);
        TextView btnOk = view.findViewById(R.id.btn_dialog_ok);
        TextView btnCancel = view.findViewById(R.id.btn_dialog_cancel);
        //set view data
        title.setText(titleStr);
        if (showTopLine) {
            lineTop.setVisibility(View.VISIBLE);
        } else {
            lineTop.setVisibility(View.GONE);
        }
        if (!TextUtils.isEmpty(descStr1)) {
            desc1.setText(descStr1);
        }
        if (!TextUtils.isEmpty(descStr2)) {
            desc2.setVisibility(View.VISIBLE);
            desc2.setText(descStr2);
        } else {
            desc2.setVisibility(View.GONE);
        }
        if (!TextUtils.isEmpty(okStr)) {
            btnOk.setText(okStr);
        }
        if (!TextUtils.isEmpty(cancelStr)) {
            btnCancel.setText(cancelStr);
        }
        // 设置全屏主题
        final AlertDialog mDialog = new AlertDialog.Builder(context, R.style.custom_dialog).create();
//        final AlertDialog mDialog = new AlertDialog.Builder(context).create();

        btnOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (okListener != null) {
                    okListener.onClick(v);
                }
                if (mDialog != null && mDialog.isShowing()) {
                    mDialog.dismiss();
                }
            }
        });
        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (cancelListener != null) {
                    cancelListener.onClick(v);
                }
                if (mDialog != null && mDialog.isShowing()) {
                    mDialog.dismiss();
                }
            }
        });

        mDialog.setCancelable(cancelable);
        mDialog.setCanceledOnTouchOutside(cancelable);
        mDialog.show();
        Window window = mDialog.getWindow();
        window.setGravity(Gravity.CENTER);
        window.setContentView(view);
        if (setAnim) {
            window.setWindowAnimations(R.style.animation_dialog);
        }
        int width = (int) (ScreenUtils.getScreenW(context) * widthRatio);
        window.setLayout(width, ViewGroup.LayoutParams.WRAP_CONTENT);
    }

    /**
     * 显示删除自选对话框
     *
     * @param mContext
     * @param deleteDesc
     * @param okListener
     * @param cancelListener
     * @param cancelable
     */
    public static void showDeleteStockDialog(Context mContext, String deleteDesc, final View.OnClickListener okListener,
                                             final View.OnClickListener cancelListener, boolean cancelable) {
        View viewDeleteStock = LayoutInflater.from(mContext).inflate(R.layout.layout_delete_stock, null);
        Button btnOk = viewDeleteStock.findViewById(R.id.btn_ok);
        Button btnCancel = viewDeleteStock.findViewById(R.id.btn_cancel);
        TextView txHint = viewDeleteStock.findViewById(R.id.text_hint);
        txHint.setText(deleteDesc);

        final AlertDialog mDialog = new AlertDialog.Builder(mContext, AlertDialog.THEME_HOLO_LIGHT).create();

        btnOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (okListener != null) {
                    okListener.onClick(v);
                }
                if (mDialog != null && mDialog.isShowing()) {
                    mDialog.dismiss();
                }
            }
        });
        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (cancelListener != null) {
                    cancelListener.onClick(v);
                }
                if (mDialog != null && mDialog.isShowing()) {
                    mDialog.dismiss();
                }
            }
        });

        mDialog.setCancelable(cancelable);
        mDialog.setCanceledOnTouchOutside(cancelable);
        mDialog.show();
        Window window = mDialog.getWindow();
        //设置透明
        WindowManager.LayoutParams lp = window.getAttributes();
        lp.alpha = 0.9f;
        window.setAttributes(lp);
        window.setGravity(Gravity.CENTER);
        window.setContentView(viewDeleteStock);
        window.setWindowAnimations(R.style.animation_dialog);
        int width = (int) (ScreenUtils.getScreenW(mContext) * 5 / 6f);
        window.setLayout(width, ViewGroup.LayoutParams.WRAP_CONTENT);
    }
}
