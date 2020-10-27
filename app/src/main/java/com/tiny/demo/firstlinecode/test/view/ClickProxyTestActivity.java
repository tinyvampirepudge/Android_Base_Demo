package com.tiny.demo.firstlinecode.test.view;

import android.view.View;
import android.widget.Button;

import com.tiny.demo.firstlinecode.R;
import com.tiny.demo.firstlinecode.base.BaseActivity;
import com.tinytongtong.tinyutils.LogUtils;
import com.tiny.demo.firstlinecode.common.utils.click.ClickFilter;
import com.tiny.demo.firstlinecode.common.utils.click.ClickProxy;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * ClickProxy测试
 */
public class ClickProxyTestActivity extends BaseActivity {
    public static final String TAG = "ClickProxy";
    @BindView(R.id.btn_click_proxy1)
    Button btnClickProxy1;
    @BindView(R.id.btn_click_proxy2)
    Button btnClickProxy2;
    @BindView(R.id.btn_click_proxy3)
    Button btnClickProxy3;
    @BindView(R.id.btn_click_proxy4)
    Button btnClickProxy4;
    @BindView(R.id.btn_click_proxy5)
    Button btnClickProxy5;

    @Override
    protected int setContentLayout() {
        return R.layout.activity_click_proxy_test;
    }

    @Override
    protected void buildContentView() {
        ClickFilter.setFilter(btnClickProxy1);
        ClickFilter.setFilter(btnClickProxy2);
        ClickFilter.setFilter(btnClickProxy3);
        ClickFilter.setFilter(btnClickProxy4);
        ClickFilter.setFilter(btnClickProxy5);

        Button btn6 = findViewById(R.id.btn_click_proxy6);
        Button btn7 = findViewById(R.id.btn_click_proxy7);
        Button btn8 = findViewById(R.id.btn_click_proxy8);
        btn6.setOnClickListener(new ClickProxy(v -> LogUtils.INSTANCE.e(TAG, ((Button) v).getText().toString())));
        btn7.setOnClickListener(new ClickProxy(v -> LogUtils.INSTANCE.e(TAG, ((Button) v).getText().toString())));
        btn8.setOnClickListener(new ClickProxy(v -> LogUtils.INSTANCE.e(TAG, ((Button) v).getText().toString())));
        //switch
        Button btn9 = findViewById(R.id.btn_click_proxy9);
        Button btn10 = findViewById(R.id.btn_click_proxy10);
        Button btn11 = findViewById(R.id.btn_click_proxy11);
        btn9.setOnClickListener(clickProxy);
        btn10.setOnClickListener(clickProxy);
        btn11.setOnClickListener(clickProxy);
    }

    private ClickProxy clickProxy = new ClickProxy(v -> {
        switch (v.getId()) {
            case R.id.btn_click_proxy9:
                LogUtils.INSTANCE.e(TAG, ((Button) v).getText().toString());
                break;
            case R.id.btn_click_proxy10:
                LogUtils.INSTANCE.e(TAG, ((Button) v).getText().toString());
                break;
            case R.id.btn_click_proxy11:
                LogUtils.INSTANCE.e(TAG, ((Button) v).getText().toString());
                break;
            default:
                break;
        }
    });

    @Override
    protected void initViewData() {

    }

    @OnClick({R.id.btn_click_proxy1, R.id.btn_click_proxy2, R.id.btn_click_proxy3, R.id.btn_click_proxy4, R.id.btn_click_proxy5})
    public void onViewClicked(View v) {
        switch (v.getId()) {
            case R.id.btn_click_proxy1:
                LogUtils.INSTANCE.e(TAG, ((Button) v).getText().toString());
                break;
            case R.id.btn_click_proxy2:
                LogUtils.INSTANCE.e(TAG, ((Button) v).getText().toString());
                break;
            case R.id.btn_click_proxy3:
                LogUtils.INSTANCE.e(TAG, ((Button) v).getText().toString());
                break;
            case R.id.btn_click_proxy4:
                LogUtils.INSTANCE.e(TAG, ((Button) v).getText().toString());
                break;
            case R.id.btn_click_proxy5:
                LogUtils.INSTANCE.e(TAG, ((Button) v).getText().toString());
                break;
        }
    }
}