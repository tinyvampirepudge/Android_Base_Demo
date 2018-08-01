package com.tiny.demo.firstlinecode.unittest;

import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.tiny.demo.firstlinecode.base.BaseActivity;
import com.tiny.demo.firstlinecode.R;
import com.tiny.demo.firstlinecode.common.utils.ToastUtils;

/**
 * Desc:    AdapterView测试
 * Created by tiny on 2017/10/12.
 * Version:
 */

public class EspressoActivity4 extends BaseActivity {
    @Override
    protected int setContentLayout() {
        return R.layout.activity_espresso4;
    }

    @Override
    protected void buildContentView() {
        String[] content = new String[50];
        for (int j = 0; j < 50; j++) {
            content[j] = "item" + j;
        }


        ArrayAdapter<String> adapter = new ArrayAdapter<String>(mContext, android.R.layout.simple_list_item_1, content);
        ListView listView = (ListView) findViewById(R.id.listview);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener((parent, view, position, id) -> ToastUtils.showSingleToast(content[position]));
    }

    @Override
    protected void initViewData() {

    }
}
