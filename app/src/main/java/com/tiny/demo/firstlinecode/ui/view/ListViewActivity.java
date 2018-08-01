package com.tiny.demo.firstlinecode.ui.view;

import android.content.Context;
import android.content.Intent;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.tiny.demo.firstlinecode.base.BaseActivity;
import com.tiny.demo.firstlinecode.R;

import java.util.ArrayList;
import java.util.List;

public class ListViewActivity extends BaseActivity {

    public static void actionStart(Context context) {
        Intent intent = new Intent(context, ListViewActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected int setContentLayout() {
        return R.layout.activity_list_view;
    }

    @Override
    protected void buildContentView() {
        List<String> lists = new ArrayList<>();
        for (int i = 1; i < 100; i++) {
            lists.add("list --> " + i);
        }
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(mContext,
                android.R.layout.simple_list_item_1, lists);
        ListView listView = (ListView) findViewById(R.id.listview);
        listView.setAdapter(adapter);
    }

    @Override
    protected void initViewData() {

    }
}
