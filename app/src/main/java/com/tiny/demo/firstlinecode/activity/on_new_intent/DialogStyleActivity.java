package com.tiny.demo.firstlinecode.activity.on_new_intent;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import com.tiny.demo.firstlinecode.R;
import com.tiny.demo.firstlinecode.common.utils.ToastUtils;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * @Description: Dialog样式的Activity
 * @Author wangjianzhou@qding.me
 * @Date 2019-09-04 14:46
 * @Version TODO
 */
public class DialogStyleActivity extends Activity {

    @BindView(R.id.btn_cancel)
    Button btnCancel;
    @BindView(R.id.btn_ok)
    Button btnOk;

    public static void actionStart(Context context) {
        Intent starter = new Intent(context, DialogStyleActivity.class);
        context.startActivity(starter);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dialog_style);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.btn_cancel)
    public void onBtnCancelClicked() {
        ToastUtils.showSingleToast("放肆，竟然敢点击取消");
        finish();
    }

    @OnClick(R.id.btn_ok)
    public void onBtnOkClicked() {
        ToastUtils.showSingleToast("你很优秀");
        finish();
    }
}
