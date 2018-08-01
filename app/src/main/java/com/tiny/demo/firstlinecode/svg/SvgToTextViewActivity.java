package com.tiny.demo.firstlinecode.svg;

import android.content.Context;
import android.content.Intent;

import com.tiny.demo.firstlinecode.base.BaseActivity;
import com.tiny.demo.firstlinecode.R;

/**
 * 动态设置svg，通过textview显示出来。
 * https://mp.weixin.qq.com/s/GB4PJVo8Xx-iOCC9slibTQ
 * http://www.iconfont.cn/help/detail?helptype=code
 */
public class SvgToTextViewActivity extends BaseActivity {

    public static void actionStart(Context context) {
        Intent intent = new Intent();
        intent.setClass(context, SvgToTextViewActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected int setContentLayout() {
        return R.layout.activity_svg_to_text_view;
    }

    @Override
    protected void buildContentView() {

    }

    @Override
    protected void initViewData() {

    }
}
