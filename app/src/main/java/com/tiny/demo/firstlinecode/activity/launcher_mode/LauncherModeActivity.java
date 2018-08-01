package com.tiny.demo.firstlinecode.activity.launcher_mode;

import android.content.Intent;
import android.util.Log;
import android.view.View;

import com.tiny.demo.firstlinecode.base.BaseActivity;
import com.tiny.demo.firstlinecode.R;

public class LauncherModeActivity extends BaseActivity {
    private static final String TAG = "LauncherModeActivity";

    @Override
    protected int setContentLayout() {
        return R.layout.activity_launcher_mode;
    }

    @Override
    protected void buildContentView() {
        Log.e(TAG, "taskid --> " + getTaskId());

        findViewById(R.id.btn1).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LauncherModeActivity.this, LauncherModeStandardActivity.class));
            }
        });
        findViewById(R.id.btn2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LauncherModeActivity.this, LauncherModeSingleTopActivity.class));
            }
        });
        findViewById(R.id.btn3).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LauncherModeActivity.this, LauncherModeSingleTaskActivity.class));
            }
        });
        findViewById(R.id.btn4).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LauncherModeActivity.this, LauncherModeSingleInstanceActivity.class));
            }
        });
    }

    @Override
    protected void initViewData() {

    }
}
