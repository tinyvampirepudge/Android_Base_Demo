package com.tiny.demo.firstlinecode.view.dialog;

import android.content.Context;
import android.support.v7.app.AlertDialog;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.tiny.demo.firstlinecode.R;
import com.tinytongtong.tinyutils.ScreenUtils;


/**
 * Desc: 通用对话框的基类, 采用Builder设计模式。
 * Created by tiny on 2017/9/19.
 * Version: 1.0.0
 */

public abstract class BaseDialog extends AlertDialog {
    private static final float defaultWidthRatio = 6 / 7f;
    private Context mContext;
    private boolean showTopLine = true;//是否展示title下方的横线
    private CharSequence cancelStr = "取消";//取消按钮的文案
    private CharSequence okStr = "确定";//确定按钮的文案
    private View.OnClickListener cancelListener;//取消按钮的点击事件
    private View.OnClickListener okListener;//确定按钮的点击事件
    private boolean cancelable = true;//对话框是否可以点击返回按钮取消掉。默认为true，可以取消。
    private boolean canceledOutside = true;//对话框是否可以点击外部取消掉。默认为true，可以取消。
    private boolean showAnim = false;//对话框是否设置动画, 默认不设置。
    private float widthRatio = defaultWidthRatio;//对话框占据屏幕宽度的比例。
    private boolean showOneBtn = false;//下方的按钮只展示确定按钮，针对强制更新对话框的需求。
    private boolean hasEditText = false;//内容中是否含有edittext
    private View view;
//    private AlertDialog mDialog;

    protected BaseDialog(Context context) {
        super(context);
    }

    private void initView(Context mContext) {
        this.mContext = mContext;
        if (mContext == null) {
            return;
        }
        if (view == null) {
            view = LayoutInflater.from(mContext).inflate(R.layout.dialog_base, null);
        }
        setView(view);

        TextView title = (TextView) view.findViewById(R.id.txt_dialog_base);
        View lineTop = view.findViewById(R.id.line_dialog_base_top);
        //内容布局的容器
        RelativeLayout layoutContainer = (RelativeLayout) view.findViewById(R.id.layout_container_dialog_base);
        TextView btnCancel = (TextView) view.findViewById(R.id.btn_dialog_base_cancel);
        View lineInterval = view.findViewById(R.id.interval_line_dialog_base);
        TextView btnOk = (TextView) view.findViewById(R.id.btn_dialog_base_ok);

        //set view data
        title.setText(setTitle());
        if (showTopLine) {
            lineTop.setVisibility(View.VISIBLE);
        } else {
            lineTop.setVisibility(View.GONE);
        }

        if (setContentView() != null) {
            layoutContainer.removeAllViews();
            layoutContainer.addView(setContentView());
        }

        if (!TextUtils.isEmpty(cancelStr)) {
            btnCancel.setText(cancelStr);
        }

        if (!TextUtils.isEmpty(okStr)) {
            btnOk.setText(okStr);
        }

//        if (mDialog == null) {
//            mDialog = new AlertDialog.Builder(mContext).create();
//        }

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (cancelListener != null) {
                    cancelListener.onClick(v);
                }
//                if (mDialog != null && mDialog.isShowing()) {
//                    mDialog.dismiss();
//                }
                if (isShowing()) {
                    dismiss();
                }
            }
        });

        btnOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (okListener != null) {
                    okListener.onClick(v);
                }
//                if (mDialog != null && mDialog.isShowing()) {
//                    mDialog.dismiss();
//                }
                if (isShowing()) {
                    dismiss();
                }
            }
        });

        if (showOneBtn) {
            btnCancel.setVisibility(View.GONE);
            lineInterval.setVisibility(View.GONE);
        } else {
            btnCancel.setVisibility(View.VISIBLE);
            lineInterval.setVisibility(View.VISIBLE);
        }

//        mDialog.setCancelable(cancelable);
//        mDialog.setCanceledOnTouchOutside(canceledOutside);
//        mDialog.show();
//        Window window = mDialog.getWindow();

        super.setCancelable(cancelable);
        super.setCanceledOnTouchOutside(canceledOutside);
        show();
        Window window = getWindow();
        window.setGravity(Gravity.CENTER);
//        window.setContentView(view);
        if (showAnim) {
            window.setWindowAnimations(R.style.animation_dialog);
        }
        if (widthRatio <= 0) {
            widthRatio = defaultWidthRatio;
        } else if (widthRatio > 1) {
            widthRatio = 1;
        }
        int width = (int) (ScreenUtils.INSTANCE.getScreenW(mContext) * widthRatio);
        window.setLayout(width, ViewGroup.LayoutParams.WRAP_CONTENT);
    }

    public abstract CharSequence setTitle();

    public abstract View setContentView();//外部传入的view对象

    public void showDialog(Context context) {
        initView(context);
        //针对有editetxt的alertdialog，需要在show之后调用。
        if (hasEditText) {
            getWindow().clearFlags(WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE | WindowManager.LayoutParams.FLAG_ALT_FOCUSABLE_IM);
        }
    }

    public void setShowTopLine(boolean showTopLine) {
        this.showTopLine = showTopLine;
    }

    public void setCancelStr(CharSequence cancelStr) {
        this.cancelStr = cancelStr;
    }

    public void setOkStr(CharSequence okStr) {
        this.okStr = okStr;
    }

    public void setCancelListener(View.OnClickListener cancelListener) {
        this.cancelListener = cancelListener;
    }

    public void setOkListener(View.OnClickListener okListener) {
        this.okListener = okListener;
    }

    @Override
    public void setCancelable(boolean cancelable) {
        this.cancelable = cancelable;
    }

    @Override
    public void setCanceledOnTouchOutside(boolean canceledOutside) {
        this.canceledOutside = canceledOutside;
    }

    public void setShowAnim(boolean showAnim) {
        this.showAnim = showAnim;
    }

    public void setWidthRatio(float widthRatio) {
        this.widthRatio = widthRatio;
    }

    public void setShowOneBtn(boolean showOneBtn) {
        this.showOneBtn = showOneBtn;
    }

    public void setCanceledOutside(boolean canceledOutside) {
        this.canceledOutside = canceledOutside;
    }

    public void setHasEditText(boolean hasEditText) {

        this.hasEditText = hasEditText;
    }
}
