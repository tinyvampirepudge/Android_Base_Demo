package com.tiny.demo.firstlinecode.unittest;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.tiny.demo.firstlinecode.base.BaseActivity;
import com.tiny.demo.firstlinecode.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Desc:    Espresso Intent 测试
 * Created by tiny on 2017/10/12.
 * Version:
 */

public class EspressoActivity3 extends BaseActivity {
    private static final int REQUEST_CODE = 0x86;
    @BindView(R.id.not_for_result_button)
    Button notForResultButton;
    @BindView(R.id.for_result_button)
    Button forResultButton;
    @BindView(R.id.result_text_view)
    TextView resultTextView;

    @Override
    protected int setContentLayout() {
        return R.layout.activity_espresso3;
    }

    @Override
    protected void buildContentView() {

    }

    @Override
    protected void initViewData() {

    }

    @OnClick({R.id.not_for_result_button, R.id.for_result_button})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.not_for_result_button:
                activitySwitch(OtherActivity.class);
                break;
            case R.id.for_result_button:
                Intent intent = new Intent(mContext, OtherActivity.class);
                startActivityForResult(intent, REQUEST_CODE);
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_CODE && resultCode == RESULT_OK && data != null) {
            String result = data.getStringExtra("result");
            resultTextView.setText(result);
        } else {
            super.onActivityResult(requestCode, resultCode, data);
        }
    }
}
