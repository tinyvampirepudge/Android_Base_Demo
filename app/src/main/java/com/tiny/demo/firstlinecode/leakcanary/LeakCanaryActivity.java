package com.tiny.demo.firstlinecode.leakcanary;

import android.widget.TextView;

import com.tiny.demo.firstlinecode.R;
import com.tiny.demo.firstlinecode.base.BaseActivity;

import butterknife.BindView;

/**
 * 内存泄漏
 */
public class LeakCanaryActivity extends BaseActivity {

    @BindView(R.id.txt_leak_canary)
    TextView txtLeakCanary;

    @Override
    protected int setContentLayout() {
        return R.layout.activity_leak_canary;
    }

    @Override
    protected void buildContentView() {
        LeakHelper.getInstance(LeakCanaryActivity.this).setRetainedTextView(txtLeakCanary);
    }

    @Override
    protected void initViewData() {

    }
}
