package com.tiny.demo.firstlinecode.fragment;

import android.content.Context;
import android.content.Intent;

import com.tiny.demo.firstlinecode.base.BaseActivity;
import com.tiny.demo.firstlinecode.R;

public class FragmentActivity extends BaseActivity {
    public static void actionStart(Context context,String newsTitle,String newsContent){
        Intent intent = new Intent(context,FragmentActivity.class);
        intent.putExtra("news_title",newsTitle);
        intent.putExtra("news_content",newsContent);
        context.startActivity(intent);
    }

    @Override
    protected int setContentLayout() {
        return R.layout.activity_fragment;
    }

    @Override
    protected void buildContentView() {

    }

    @Override
    protected void initViewData() {

    }
}
