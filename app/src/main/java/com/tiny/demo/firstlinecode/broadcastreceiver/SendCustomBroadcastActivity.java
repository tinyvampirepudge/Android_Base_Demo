package com.tiny.demo.firstlinecode.broadcastreceiver;

import android.content.Intent;
import android.view.View;

import com.tiny.demo.firstlinecode.base.BaseActivity;
import com.tiny.demo.firstlinecode.R;

public class SendCustomBroadcastActivity extends BaseActivity {

    @Override
    protected int setContentLayout() {
        return R.layout.activity_send_custom_broadcast;
    }

    @Override
    protected void buildContentView() {
        findViewById(R.id.btn_send_standard_broadcast).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent("com.tiny.firstlinecode.broadcasttest.MY_BROADCAST");
                sendBroadcast(intent);
            }
        });

        findViewById(R.id.btn_send_ordered_broadcast).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent("com.tiny.firstlinecode.broadcasttest.MY_BROADCAST");
                sendOrderedBroadcast(intent,null);
            }
        });
    }

    @Override
    protected void initViewData() {

    }
}
