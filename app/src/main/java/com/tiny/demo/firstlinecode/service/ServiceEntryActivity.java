package com.tiny.demo.firstlinecode.service;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.tiny.demo.firstlinecode.R;
import com.tiny.demo.firstlinecode.service.intentservice.IntentServiceTestActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ServiceEntryActivity extends AppCompatActivity {

    @BindView(R.id.btn_service_1)
    Button btnService1;
    @BindView(R.id.btn_service_2)
    Button btnService2;

    public static void actionStart(Context context) {
        Intent intent = new Intent();
        intent.setClass(context, ServiceEntryActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_service_entry);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.btn_service_1, R.id.btn_service_2, R.id.btn_service_3, R.id.btn_service_4})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_service_1:
                ServiceActivity.actionStart(this);
                break;
            case R.id.btn_service_2:
                ServiceStartActivity.actionStart(this);
                break;
            case R.id.btn_service_3:
                ServiceBindAActivity.actionStart(this);
                break;
            case R.id.btn_service_4:
                IntentServiceTestActivity.actionStart(this);
                break;
        }
    }
}
