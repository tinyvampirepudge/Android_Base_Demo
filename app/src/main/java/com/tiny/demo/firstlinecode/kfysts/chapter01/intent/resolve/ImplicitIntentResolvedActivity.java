package com.tiny.demo.firstlinecode.kfysts.chapter01.intent.resolve;

import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.tiny.demo.firstlinecode.R;
import com.tiny.demo.firstlinecode.common.utils.LogUtils;

import java.util.List;

import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * @Description: 查询隐式Intent是否有匹配
 * @Author wangjianzhou@qding.me
 * @Date 2019-08-05 10:25
 * @Version TODO
 */
public class ImplicitIntentResolvedActivity extends AppCompatActivity {
    public static void actionStart(Context context) {
        Intent starter = new Intent(context, ImplicitIntentResolvedActivity.class);
        context.startActivity(starter);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_implicit_intent_resolved);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.btn_resolve_activity_test1)
    public void onBtnResolveActivityTest1Clicked() {
        Intent intent = new Intent();
        //action
//        intent.setAction("com.tinytongtong.implicit.intent.test.action.a");
        intent.setAction("com.tinytongtong.implicit.intent.test.action.abc");
        //Category可以不设置，因为一般在AndroidManifest.xml会设置Default，startActivity方法中也会默认添加Default。
        if (intent.resolveActivity(getPackageManager()) != null) {
            LogUtils.e("match success");
            startActivity(intent);
        } else {
            LogUtils.e("match failure");
        }
    }

    @OnClick(R.id.btn_resolve_activity_test2)
    public void onBtnResolveActivityTest2Clicked() {
        Intent intent = new Intent();
        //action
//        intent.setAction("com.tinytongtong.implicit.intent.test.action.a");
        intent.setAction("com.tinytongtong.implicit.intent.test.action.abc");
        //Category可以不设置，因为一般在AndroidManifest.xml会设置Default，startActivity方法中也会默认添加Default。
        if (getPackageManager().resolveActivity(intent, PackageManager.MATCH_DEFAULT_ONLY) != null) {
            LogUtils.e("match success");
            startActivity(intent);
        } else {
            LogUtils.e("match failure");
        }
    }

    @OnClick(R.id.btn_resolve_activity_test3)
    public void onBtnResolveActivityTest3Clicked() {
        Intent intent = new Intent();
        //action
        intent.setAction("com.tinytongtong.implicit.intent.test.action.abc");
        //Category可以不设置，因为一般在AndroidManifest.xml会设置Default，startActivity方法中也会默认添加Default。
        List<ResolveInfo> resolveInfoList = getPackageManager().queryIntentActivities(intent, PackageManager.MATCH_DEFAULT_ONLY);
        if (resolveInfoList != null) {
            LogUtils.e("query success");
            for (int i = 0; i < resolveInfoList.size(); i++) {
                ResolveInfo resolveInfo = resolveInfoList.get(i);
                if (resolveInfo != null) {
                    LogUtils.e("resolveInfo.resolvePackageName: " + resolveInfo.resolvePackageName);
                    ActivityInfo activityInfo = resolveInfo.activityInfo;
                    if (activityInfo != null) {
                        LogUtils.e("activityInfo.name:" + activityInfo.name);
                        LogUtils.e("activityInfo.packageName:" + activityInfo.packageName);
                        LogUtils.e("activityInfo.launchMode:" + activityInfo.launchMode);
                    }
                }
            }
        } else {
            LogUtils.e("match failure");
        }
    }
}
