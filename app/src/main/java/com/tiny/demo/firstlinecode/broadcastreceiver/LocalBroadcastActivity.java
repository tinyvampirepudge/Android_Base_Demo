package com.tiny.demo.firstlinecode.broadcastreceiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.v4.content.LocalBroadcastManager;
import android.view.View;
import android.widget.Toast;

import com.tiny.demo.firstlinecode.base.BaseActivity;
import com.tiny.demo.firstlinecode.R;
import com.tiny.demo.firstlinecode.common.utils.LogUtils;

public class LocalBroadcastActivity extends BaseActivity {

    private LocalBroadcastManager localBroadcastManager;
    private IntentFilter intentFilter;
    private LocalBroadcastReceiver localBroadcastReceiver;

    @Override
    protected int setContentLayout() {
        return R.layout.activity_local_broadcast;
    }

    @Override
    protected void buildContentView() {
        localBroadcastManager = LocalBroadcastManager.getInstance(mContext);
        findViewById(R.id.btn_send_local_broadcast).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent("com.tiny.firstlinecode.LOCAL_BROADCAST");
                intent.putExtra("local", "童二才是家里的老大！");
                localBroadcastManager.sendBroadcast(intent);
            }
        });

        intentFilter = new IntentFilter();
        intentFilter.addAction("com.tiny.firstlinecode.LOCAL_BROADCAST");
        localBroadcastReceiver = new LocalBroadcastReceiver();
        localBroadcastManager.registerReceiver(localBroadcastReceiver, intentFilter);
    }

    @Override
    protected void initViewData() {

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        localBroadcastManager.unregisterReceiver(localBroadcastReceiver);
    }

    class LocalBroadcastReceiver extends BroadcastReceiver {

        @Override
        public void onReceive(Context context, Intent intent) {
            Toast.makeText(mContext, "receive Local BroadCast", Toast.LENGTH_SHORT).show();
            LogUtils.e("local --> " + intent.getStringExtra("local"));
        }
    }
}
