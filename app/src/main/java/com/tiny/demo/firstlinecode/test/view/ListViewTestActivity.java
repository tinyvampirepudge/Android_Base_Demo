package com.tiny.demo.firstlinecode.test.view;

import android.content.Context;
import android.content.Intent;
import android.widget.ListView;

import com.tiny.demo.firstlinecode.base.BaseActivity;
import com.tiny.demo.firstlinecode.R;
import com.tiny.demo.firstlinecode.test.adapter.TestAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Listview的复用测试
 */
public class ListViewTestActivity extends BaseActivity {

    public static void actionStart(Context context) {
        Intent intent = new Intent();
        intent.setClass(context, ListViewTestActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected int setContentLayout() {
        return R.layout.activity_list_view_test;
    }

    @Override
    protected void buildContentView() {
        ListView lv = (ListView) findViewById(R.id.lv_test);
        List<String> strs = new ArrayList<>();
        for (int j = 0; j < 100; j++) {
            strs.add("呵呵哈哈 --> " + j);
        }
        TestAdapter testAdapter = new TestAdapter(strs, mContext);
        lv.setAdapter(testAdapter);
    }

    @Override
    protected void initViewData() {

    }
}
