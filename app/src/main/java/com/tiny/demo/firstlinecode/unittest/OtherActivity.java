package com.tiny.demo.firstlinecode.unittest;

import com.tiny.demo.firstlinecode.base.BaseActivity;
import com.tiny.demo.firstlinecode.R;

/**
 * Desc:    Espresso Intent 测试， 跳转的那个Activity。
 * Created by tiny on 2017/10/12.
 * Version:
 */

public class OtherActivity extends BaseActivity {
    @Override
    protected int setContentLayout() {
        return R.layout.activity_other;
    }

    @Override
    protected void buildContentView() {

    }

    @Override
    protected void initViewData() {

    }
}
