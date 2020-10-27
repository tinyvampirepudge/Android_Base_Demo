package com.tiny.demo.firstlinecode.kfysts.chapter01.intent.resolve;

import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.tiny.demo.firstlinecode.R;
import com.tinytongtong.tinyutils.LogUtils;


import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * @Description: 查询隐式Intent是否有匹配
 * @Author wangjianzhou@qding.me
 * @Date 2019-08-05 10:25
 * @Version TODO
 */
public class IntentFilterResolvedEntryActivity extends AppCompatActivity {
    @BindView(R.id.tv_query_results)
    TextView tvQueryResults;

    public static void actionStart(Context context) {
        Intent starter = new Intent(context, IntentFilterResolvedEntryActivity.class);
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
        intent.setAction("com.tiny.demo.firstlinecode.kfysts.chapter01.intent.resolve.action.a");
//        intent.setAction("com.tiny.demo.firstlinecode.kfysts.chapter01.intent.resolve.action.abc");
        //Category可以不设置，因为一般在AndroidManifest.xml会设置Default，startActivity方法中也会默认添加Default。
        if (intent.resolveActivity(getPackageManager()) != null) {
            LogUtils.INSTANCE.e("match success");
            startActivity(intent);
        } else {
            LogUtils.INSTANCE.e("match failure");
        }
    }

    @OnClick(R.id.btn_resolve_activity_test2)
    public void onBtnResolveActivityTest2Clicked() {
        Intent intent = new Intent();
        //action
        intent.setAction("com.tiny.demo.firstlinecode.kfysts.chapter01.intent.resolve.action.a");
//        intent.setAction("com.tiny.demo.firstlinecode.kfysts.chapter01.intent.resolve.action.abc");
        //Category可以不设置，因为一般在AndroidManifest.xml会设置Default，startActivity方法中也会默认添加Default。
        if (getPackageManager().resolveActivity(intent, PackageManager.MATCH_DEFAULT_ONLY) != null) {
            LogUtils.INSTANCE.e("match success");
            startActivity(intent);
        } else {
            LogUtils.INSTANCE.e("match failure");
        }
    }

    @OnClick(R.id.btn_resolve_activity_test3)
    public void onBtnResolveActivityTest3Clicked() {
        Intent intent = new Intent();
        //action
        intent.setAction("com.tiny.demo.firstlinecode.kfysts.chapter01.intent.resolve.action.abc");
        //Category可以不设置，因为一般在AndroidManifest.xml会设置Default，startActivity方法中也会默认添加Default。
        List<ResolveInfo> resolveInfoList = getPackageManager().queryIntentActivities(intent, PackageManager.MATCH_DEFAULT_ONLY);
        StringBuilder results = new StringBuilder("匹配结果为：\n");
        if (resolveInfoList != null) {
            LogUtils.INSTANCE.e("query success");
            for (int i = 0; i < resolveInfoList.size(); i++) {
                ResolveInfo resolveInfo = resolveInfoList.get(i);
                if (resolveInfo != null) {
                    LogUtils.INSTANCE.e("resolveInfo.resolvePackageName: " + resolveInfo.resolvePackageName);
                    results.append("resolveInfo.resolvePackageName: " + resolveInfo.resolvePackageName);
                    results.append("\n");
                    ActivityInfo activityInfo = resolveInfo.activityInfo;
                    if (activityInfo != null) {
                        LogUtils.INSTANCE.e("activityInfo.name:" + activityInfo.name);
                        LogUtils.INSTANCE.e("activityInfo.packageName:" + activityInfo.packageName);
                        LogUtils.INSTANCE.e("activityInfo.launchMode:" + activityInfo.launchMode);
                        results.append("activityInfo.name: " + activityInfo.name);
                        results.append("\n");
                        results.append("activityInfo.packageName: " + activityInfo.packageName);
                        results.append("\n");
                        results.append("activityInfo.launchMode: " + activityInfo.launchMode);
                        results.append("\n");
                    }
                }
                results.append("\n");
            }
        } else {
            LogUtils.INSTANCE.e("match failure");
        }
        tvQueryResults.setText(results.toString());
    }
}
