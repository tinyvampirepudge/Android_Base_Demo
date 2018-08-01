package com.tiny.demo.firstlinecode.service;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.tiny.demo.firstlinecode.R;
import com.tiny.demo.firstlinecode.common.utils.LogUtils;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Desc: bindService
 * Created by tiny on 2018/2/12 下午2:36
 * Version:
 */
public class ServiceBindBActivity extends AppCompatActivity {
    public static final String TAG = "ServiceBind";
    @BindView(R.id.btn_test1)
    Button btnTest1;
    @BindView(R.id.btn_test2)
    Button btnTest2;
    @BindView(R.id.btn_test4)
    Button btnTest4;

    private boolean isBind = false;

    private ServiceConnection conn = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            LogUtils.e(TAG, "onServiceConnected");
            isBind = true;
            TestTwoService.TestTwoBinder binder = (TestTwoService.TestTwoBinder) service;
            testTwoService = binder.getService();
            int num = testTwoService.getRandomNumber();
            LogUtils.e(TAG, "num --> " + num);
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            LogUtils.e(TAG, "onServiceDisconnected");
            isBind = false;
        }
    };
    private TestTwoService testTwoService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_service_bind_b);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.btn_test1, R.id.btn_test2, R.id.btn_test4})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_test1:
                Intent intent = new Intent(this, TestTwoService.class);
                intent.putExtra("from", "ActivityB");
                bindService(intent, conn, BIND_AUTO_CREATE);
                break;
            case R.id.btn_test2:
                if (isBind) {
                    unbindService(conn);
                }
                break;
            case R.id.btn_test4:
                finish();
                break;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        LogUtils.e(TAG, "onDestroy");
    }
}
