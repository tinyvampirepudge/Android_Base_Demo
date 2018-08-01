package com.tiny.demo.firstlinecode.test.view;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;

import com.tiny.demo.firstlinecode.MainActivity;
import com.tiny.demo.firstlinecode.R;
import com.tiny.demo.firstlinecode.common.utils.ActivityInfoUtils;
import com.tiny.demo.firstlinecode.common.utils.LogUtils;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Desc: 工具类测试
 *
 * @author tiny
 * @date 2018/6/1 下午4:37
 */
public class UtilsTestActivity extends AppCompatActivity {

    @BindView(R.id.btn_test_1)
    Button btnTest1;
    @BindView(R.id.btn_test_2)
    Button btnTest2;

    public static void actionStart(Context context) {
        Intent starter = new Intent(context, UtilsTestActivity.class);
        context.startActivity(starter);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_utils_test);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.btn_test_1)
    public void onBtnTest1Clicked() {
        boolean result = ActivityInfoUtils.activityOnceCreated(this, MainActivity.class);
        LogUtils.e("MainActivity started result:" + result);
    }

    @OnClick(R.id.btn_test_2)
    public void onBtnTest2Clicked() {

    }
}
