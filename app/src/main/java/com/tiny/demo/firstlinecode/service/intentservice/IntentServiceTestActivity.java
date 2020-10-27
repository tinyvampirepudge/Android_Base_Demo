package com.tiny.demo.firstlinecode.service.intentservice;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.tiny.demo.firstlinecode.R;
import com.tinytongtong.tinyutils.LogUtils;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Desc:    intent Service
 * Created by tiny on 2018/2/12 下午4:37
 * Version:
 */
public class IntentServiceTestActivity extends AppCompatActivity {

    @BindView(R.id.btn_test_1)
    Button btnTest1;
    @BindView(R.id.btn_test_2)
    Button btnTest2;

    public static void actionStart(Context context) {
        Intent intent = new Intent();
        intent.setClass(context, IntentServiceTestActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intent_service_test);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.btn_test_1, R.id.btn_test_2})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_test_1:
                LogUtils.INSTANCE.e("普通Service");
                Intent intent1 = new Intent(this, TestService.class);
                startService(intent1);
                break;
            case R.id.btn_test_2:
                LogUtils.INSTANCE.e("IntentService");
                for (int j = 0; j < 20; j++) {
                    Intent intent2 = new Intent(this, TestIntentService.class);
                    startService(intent2);
                }
                break;
        }
    }
}
