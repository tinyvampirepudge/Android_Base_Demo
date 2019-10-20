package com.tiny.demo.firstlinecode.view.dialog;

import android.app.AlertDialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.widget.ProgressBar;
import android.widget.TextView;


import com.tiny.demo.firstlinecode.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @Description: 加载对话框
 * @Author wangjianzhou
 * @Date 2019-10-19 11:44
 * @Version v1.0.1
 */
public class LoadingDialog extends AlertDialog {

    private Context mContext;

    @BindView(R.id.progressBar)
    ProgressBar progressBar;
    @BindView(R.id.tv_loading)
    TextView tvLoading;

    public LoadingDialog(@NonNull Context context) {
        super(context, R.style.dialogTransparent);
        this.mContext = context;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_loading);
        ButterKnife.bind(this);
    }

}
