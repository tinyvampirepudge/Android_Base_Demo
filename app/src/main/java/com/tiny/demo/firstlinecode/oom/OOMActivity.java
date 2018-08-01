package com.tiny.demo.firstlinecode.oom;

import android.content.Context;
import android.content.Intent;

import com.tiny.demo.firstlinecode.base.BaseActivity;
import com.tiny.demo.firstlinecode.R;
import com.tiny.demo.firstlinecode.common.utils.LogUtils;

/**
 * Android OOM 相关。
 * http://blog.csdn.net/gjnm820/article/details/51579080
 */
public class OOMActivity extends BaseActivity {

    public static void actionStart(Context context) {
        Intent intent = new Intent();
        intent.setClass(context, OOMActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected int setContentLayout() {
        return R.layout.activity_oom;
    }

    @Override
    protected void buildContentView() {

    }

    @Override
    protected void initViewData() {
        LogUtils.e("Runtime.getRuntime().maxMemory() --> " + Runtime.getRuntime().maxMemory());
        LogUtils.e("Runtime.getRuntime().totalMemory() --> " + Runtime.getRuntime().totalMemory());
    }

}
