package com.tiny.demo.firstlinecode.view.dialog;

import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.tiny.demo.firstlinecode.R;


/**
 * Desc: 内容是textview文本的对话框。
 * <p>
 * Created by tiny on 2017/9/19.
 * Version:
 */

public class TextViewDialog extends BaseDialog {
    private static final float defaultWidthRatio = 6 / 7f;
    private Context mContext;
    //title，内容文本1，内容文本2，确定按钮的文案，取消按钮的文案。
    private CharSequence titleStr, descStr1, descStr2, okStr, cancelStr;
    //确定、取消按钮的监听器
    private View.OnClickListener okListener, cancelListener;
    //是否展示title下方的line；点击返回按钮是否可以取消掉对话框；点击对话框外部是否可以取消掉对话框；
    // 是否展示对话框动画；是否只展示确定按钮，针对下方只有一个按钮的情况，比如说强制更新对话框。
    private boolean showTopLine, cancelable, canceledOutside, showAnim, showOneBtn;
    private View contentView;
    private float widthRatio = defaultWidthRatio;//对话框占据屏幕宽度的比例。

    public TextViewDialog(Context context, Builder builder) {
        super(context);
        this.mContext = builder.context;
        this.titleStr = builder.titleStr;
        this.descStr1 = builder.descStr1;
        this.descStr2 = builder.descStr2;
        this.okStr = builder.okStr;
        this.cancelStr = builder.cancelStr;
        this.okListener = builder.okListener;
        this.cancelListener = builder.cancelListener;
        this.showTopLine = builder.showTopLine;
        this.cancelable = builder.cancelable;
        this.canceledOutside = builder.canceledOutside;
        this.showAnim = builder.showAnim;
        this.showOneBtn = builder.showOneBtn;
        this.widthRatio = builder.widthRatio;
        initView();
    }

    public static class Builder {
        private Context context;
        private CharSequence titleStr;
        private CharSequence descStr1;
        private CharSequence descStr2;
        private CharSequence okStr = "确定";
        private CharSequence cancelStr = "取消";
        private View.OnClickListener okListener, cancelListener;
        private boolean showTopLine = true, cancelable = true, canceledOutside = true;
        private boolean showAnim = false, showOneBtn = false;
        private float widthRatio = defaultWidthRatio;//对话框占据屏幕宽度的比例。

        public Builder(Context context) {
            this.context = context;
        }

        public TextViewDialog build() {
            return new TextViewDialog(context, this);
        }

        public Builder setTitleStr(CharSequence titleStr) {
            this.titleStr = titleStr;
            return this;
        }

        public Builder setDescStr1(CharSequence descStr1) {
            this.descStr1 = descStr1;
            return this;
        }

        public Builder setDescStr2(CharSequence descStr2) {
            this.descStr2 = descStr2;
            return this;
        }

        public Builder setOkStr(CharSequence okStr) {
            this.okStr = okStr;
            return this;
        }

        public Builder setCancelStr(CharSequence cancelStr) {
            this.cancelStr = cancelStr;
            return this;
        }

        public Builder setOkListener(View.OnClickListener okListener) {
            this.okListener = okListener;
            return this;
        }

        public Builder setCancelListener(View.OnClickListener cancelListener) {
            this.cancelListener = cancelListener;
            return this;
        }

        public Builder setShowTopLine(boolean showTopLine) {
            this.showTopLine = showTopLine;
            return this;
        }

        public Builder setCancelable(boolean cancelable) {
            this.cancelable = cancelable;
            return this;
        }

        public Builder setCanceledOnTouchOutside(boolean canceledOutside) {
            this.canceledOutside = canceledOutside;
            return this;
        }

        public Builder setShowAnim(boolean showAnim) {
            this.showAnim = showAnim;
            return this;
        }

        public Builder setShowOneBtn(boolean showOneBtn) {
            this.showOneBtn = showOneBtn;
            return this;
        }

        public Builder setWidthRatio(float widthRatio) {
            this.widthRatio = widthRatio;
            return this;
        }
    }

    private void initView() {
        contentView = LayoutInflater.from(mContext).inflate(R.layout.dialog_textview, null);
        TextView desc1 = (TextView) contentView.findViewById(R.id.txt_desc1_dialog_textview);
        TextView desc2 = (TextView) contentView.findViewById(R.id.txt_desc2_dialog_textview);
        if (!TextUtils.isEmpty(descStr1)) {
            desc1.setText(descStr1);
        }
        if (!TextUtils.isEmpty(descStr2)) {
            desc2.setVisibility(View.VISIBLE);
            desc2.setText(descStr2);
        } else {
            desc2.setVisibility(View.GONE);
        }
        //给baseDialog设置属性
        setShowTopLine(showTopLine);
        setCancelStr(cancelStr);
        setOkStr(okStr);
        setCancelListener(cancelListener);
        setOkListener(okListener);
        setCancelable(cancelable);
        setCanceledOnTouchOutside(canceledOutside);
        setShowAnim(showAnim);
        setWidthRatio(widthRatio);
        setShowOneBtn(showOneBtn);
    }

    @Override
    public CharSequence setTitle() {
        return titleStr;
    }

    @Override
    public View setContentView() {
        return contentView;
    }
}
