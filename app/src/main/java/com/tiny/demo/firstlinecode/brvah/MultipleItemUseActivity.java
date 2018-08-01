package com.tiny.demo.firstlinecode.brvah;

import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.tiny.demo.firstlinecode.R;
import com.tiny.demo.firstlinecode.base.BaseActivity;
import com.tiny.demo.firstlinecode.brvah.adapter.MultipleItemQuickAdapter;
import com.tiny.demo.firstlinecode.brvah.data.DataServer;
import com.tiny.demo.firstlinecode.brvah.entity.MultipleItem;
import com.tiny.demo.firstlinecode.common.interfaces.OnTitleBarClick;
import com.tiny.demo.firstlinecode.common.view.TitleBarLayout;

import java.util.List;

import butterknife.BindView;

public class MultipleItemUseActivity extends BaseActivity {

    @BindView(R.id.rv_list)
    RecyclerView mRecyclerView;
    @BindView(R.id.title_bar_brvah_multiitem)
    TitleBarLayout titleBarLayout;

    @Override
    protected int setContentLayout() {
        return R.layout.activity_multiple_item_use;
    }

    @Override
    protected void buildContentView() {
        titleBarLayout.setTitleBarListener(new OnTitleBarClick() {
            @Override
            public void onLeftClicked(int type, View v) {
                finish();
            }

            @Override
            public void onRightClicked(int type, View v) {

            }
        });

        mRecyclerView = findViewById(R.id.rv_list);
        final List<MultipleItem> data = DataServer.getMultipleItemData();
        final MultipleItemQuickAdapter multipleItemAdapter = new MultipleItemQuickAdapter(data);
        final GridLayoutManager manager = new GridLayoutManager(this, 4);
        mRecyclerView.setLayoutManager(manager);
        multipleItemAdapter.setSpanSizeLookup((gridLayoutManager, position) -> data.get(position).getSpanSize());
        mRecyclerView.setAdapter(multipleItemAdapter);
    }

    @Override
    protected void initViewData() {

    }
}
