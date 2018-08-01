package com.tiny.demo.firstlinecode.provider;

import android.content.Context;
import android.content.Intent;
import android.view.View;

import com.tiny.demo.firstlinecode.base.BaseActivity;
import com.tiny.demo.firstlinecode.R;

public class ContentProviderActivity extends BaseActivity {

    public static void actionStart(Context context) {
        Intent intent = new Intent();
        intent.setClass(context, ContentProviderActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected int setContentLayout() {
        return R.layout.activity_content_provider;
    }

    @Override
    protected void buildContentView() {
        findViewById(R.id.btn_contact).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ContactActivity.actionStart(mContext);
            }
        });
    }

    @Override
    protected void initViewData() {

    }
}
