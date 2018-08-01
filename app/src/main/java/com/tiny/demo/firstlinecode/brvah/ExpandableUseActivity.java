package com.tiny.demo.firstlinecode.brvah;

import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.chad.library.adapter.base.entity.MultiItemEntity;
import com.tiny.demo.firstlinecode.R;
import com.tiny.demo.firstlinecode.base.BaseActivity;
import com.tiny.demo.firstlinecode.brvah.adapter.ExpandableItemAdapter;
import com.tiny.demo.firstlinecode.brvah.entity.Level0Item;
import com.tiny.demo.firstlinecode.brvah.entity.Level1Item;
import com.tiny.demo.firstlinecode.brvah.entity.Person;
import com.tiny.demo.firstlinecode.common.interfaces.OnTitleBarClick;
import com.tiny.demo.firstlinecode.common.view.TitleBarLayout;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import butterknife.BindView;

public class ExpandableUseActivity extends BaseActivity {

    @BindView(R.id.title_bar_brvah_expandable)
    TitleBarLayout titleBar;
    @BindView(R.id.rv_list)
    RecyclerView mRecyclerView;
    private List<MultiItemEntity> mData;
    private ExpandableItemAdapter adapter;

    @Override
    protected int setContentLayout() {
        return R.layout.activity_expandable_use;
    }

    @Override
    protected void buildContentView() {
        titleBar.setTitleBarListener(new OnTitleBarClick() {
            @Override
            public void onLeftClicked(int type, View v) {
                finish();
            }

            @Override
            public void onRightClicked(int type, View v) {

            }
        });
        mData = generateData();
        adapter = new ExpandableItemAdapter(mData);
        GridLayoutManager manager = new GridLayoutManager(mContext, 3);
        manager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
            @Override
            public int getSpanSize(int position) {
                return adapter.getItemViewType(position) == ExpandableItemAdapter.TYPE_PERSON ? 1 : manager.getSpanCount();
            }
        });
        mRecyclerView.setAdapter(adapter);
        // important! setLayoutManager should be called after setAdapter
        mRecyclerView.setLayoutManager(manager);
        adapter.expandAll();
    }

    @Override
    protected void initViewData() {

    }

    private ArrayList<MultiItemEntity> generateData() {
        int lv0Count = 9;
        int lv1Count = 3;
        int personCount = 6;

        String[] nameList = {"Bob", "Andy", "Lily", "Brown", "Bruce", "DanDan"};
        Random random = new Random();

        ArrayList<MultiItemEntity> res = new ArrayList<>();
        for (int i = 0; i < lv0Count; i++) {
            Level0Item lv0 = new Level0Item("This is " + i + "th item in Level 0", "subtitle of " + i);
            for (int j = 0; j < lv1Count; j++) {
                Level1Item lv1 = new Level1Item("Level 1 item: " + j, "(no animation)");
                for (int k = 0; k < personCount; k++) {
                    lv1.addSubItem(new Person(nameList[k], random.nextInt(40)));
                }
                lv0.addSubItem(lv1);
            }
            res.add(lv0);
        }
        res.add(new Level0Item("This is " + lv0Count + "th item in Level 0", "subtitle of " + lv0Count));
        return res;
    }
}
