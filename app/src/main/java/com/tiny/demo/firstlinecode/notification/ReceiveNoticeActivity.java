package com.tiny.demo.firstlinecode.notification;

import android.app.NotificationManager;
import android.content.Context;
import android.os.Bundle;
import android.widget.TextView;

import com.tiny.demo.firstlinecode.base.BaseActivity;
import com.tiny.demo.firstlinecode.R;

public class ReceiveNoticeActivity extends BaseActivity {

    private TextView textView;
    private String str;
    private int id;

    @Override
    protected int setContentLayout() {
        return R.layout.activity_receive_notice;
    }

    @Override
    protected void buildContentView() {
        if (getIntent() != null) {
            Bundle bundle = getIntent().getExtras();
            if(bundle!=null){
                str = bundle.getString("para");
                id = bundle.getInt("ids");
            }
        }
        textView = (TextView) findViewById(R.id.txt_receive_message);
        textView.setText(str);
        NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager.cancel(id);
    }

    @Override
    protected void initViewData() {

    }
}
