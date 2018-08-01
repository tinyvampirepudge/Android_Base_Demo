package com.tiny.demo.firstlinecode.test.view;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.TextView;

import com.tiny.demo.firstlinecode.R;
import com.tiny.demo.firstlinecode.base.BaseActivity;
import com.tiny.demo.firstlinecode.common.utils.LogUtils;
import com.tiny.demo.firstlinecode.test.adapter.WeekGridAdapter;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Desc:    王宇峰的
 * Created by tiny on 2017/11/28.
 * Version:
 */

public class DatePickerActivity extends BaseActivity {
    @BindView(R.id.txt_title)
    TextView txtTitle;
    @BindView(R.id.lv_date_picker_demo)
    ListView mListView;
    private WeekGridAdapter adapter;
    private SharedPreferences sp;

    @Override
    protected int setContentLayout() {
        return R.layout.activity_date_picker_demo;
    }

    @Override
    protected void buildContentView() {
        sp = mContext.getSharedPreferences("wyf_is_a_big_sb", Context.MODE_PRIVATE);
        int selected = sp.getInt("selected", 0);
        adapter = new WeekGridAdapter(mContext, onAdapterChangedListener);
        adapter.updateSelected(selected);
        updateTextContent(selected);
        mListView.setAdapter(adapter);
    }

    private WeekGridAdapter.OnAdapterChangedListener onAdapterChangedListener = new WeekGridAdapter.OnAdapterChangedListener() {
        @Override
        public void onChange(int selected) {
            LogUtils.e("selected --> " + selected);
            sp.edit().putInt("selected", selected).apply();

            updateTextContent(selected);
        }
    };

    private void updateTextContent(int selected) {
        ArrayList<String> selectedList = new ArrayList<>();

        for (int j = 0; j < WeekGridAdapter.weekStr.length; j++) {
            if ((selected >> j) % 2 == 1) {
                selectedList.add(WeekGridAdapter.weekStr[j]);
            }
        }
        txtTitle.setText("王宇峰是个大傻逼，选中的周内如期有：\n" +
                selectedList.toString());
    }

    @Override
    protected void initViewData() {

    }

}
