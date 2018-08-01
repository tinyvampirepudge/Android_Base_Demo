package com.tiny.demo.firstlinecode.activity.launcher_mode;

import android.content.Intent;
import android.os.Process;
import android.util.Log;
import android.view.View;

import com.tiny.demo.firstlinecode.base.BaseActivity;
import com.tiny.demo.firstlinecode.R;
import com.tiny.demo.firstlinecode.activity.activity_stack_manager.ActivityCollector;

public class LauncherModeSingleInstanceActivity extends BaseActivity {
    private static final String TAG = "SingleInstanceActivity";

    @Override
    protected int setContentLayout() {
        return R.layout.activity_launcher_mode_single_instance;
    }

    @Override
    protected void buildContentView() {
        Log.e(TAG, "taskid --> " + getTaskId());
        findViewById(R.id.btn1).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LauncherModeSingleInstanceActivity.this,LauncherModeStandardActivity.class));
            }
        });

        findViewById(R.id.btn2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LauncherModeSingleInstanceActivity.this,LauncherModeSingleTopActivity.class));
            }
        });

        findViewById(R.id.btn3).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LauncherModeSingleInstanceActivity.this,LauncherModeSingleTaskActivity.class));
            }
        });
        findViewById(R.id.btn4).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LauncherModeSingleInstanceActivity.this,LauncherModeSingleInstanceActivity.class));
            }
        });
        findViewById(R.id.btn5).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ActivityCollector.finishAll();
                //还可以退出进程,os包下的。
                Process.killProcess(Process.myPid());
            }
        });
    }

    @Override
    protected void initViewData() {

    }
}
