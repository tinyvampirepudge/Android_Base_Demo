package com.tiny.demo.firstlinecode.activity;

import android.content.Intent;
import android.os.Process;
import android.view.View;

import com.tiny.demo.firstlinecode.base.BaseActivity;
import com.tiny.demo.firstlinecode.R;
import com.tiny.demo.firstlinecode.activity.activity_lifecycle.LifecycleActivity;
import com.tiny.demo.firstlinecode.activity.activity_stack_manager.ActivityCollector;
import com.tiny.demo.firstlinecode.activity.intent.IntentActivity;
import com.tiny.demo.firstlinecode.activity.launcher_mode.LauncherModeActivity;

/**
 * activity相关的知识。
 */
public class ActivityReferedActivity extends BaseActivity {

    @Override
    protected int setContentLayout() {
        return R.layout.activity_activty;
    }

    @Override
    protected void buildContentView() {
        //intent
        //显示intent
        findViewById(R.id.btn_intent).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ActivityReferedActivity.this, IntentActivity.class));
            }
        });

        //Activity生命周期
        findViewById(R.id.btn_activity_lifecycle).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ActivityReferedActivity.this, LifecycleActivity.class));
            }
        });

        //启动模式
        findViewById(R.id.btn_launcher_mode).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ActivityReferedActivity.this, LauncherModeActivity.class));
            }
        });

        //activity栈和一键退出程序
        findViewById(R.id.btn_activity_stack_manager).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ActivityCollector.finishAll();
                //还可以退出进程,os包下的。
                Process.killProcess(Process.myPid());
            }
        });

        //activity的正确启动方式
        findViewById(R.id.start_activity).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                StartActivity.actionStart(ActivityReferedActivity.this,"hello","start activity");
            }
        });
    }

    @Override
    protected void initViewData() {

    }
}
