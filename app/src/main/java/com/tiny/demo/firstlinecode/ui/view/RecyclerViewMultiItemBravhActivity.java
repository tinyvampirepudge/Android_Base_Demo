package com.tiny.demo.firstlinecode.ui.view;

import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.tiny.demo.firstlinecode.R;
import com.tiny.demo.firstlinecode.base.BaseActivity;
import com.tiny.demo.firstlinecode.common.interfaces.OnTitleBarClick;
import com.tiny.demo.firstlinecode.common.view.TitleBarLayout;
import com.tiny.demo.firstlinecode.ui.adapter.MultiItemBravhAdapter;
import com.tiny.demo.firstlinecode.ui.bean.MultiItemBravh;
import com.tiny.demo.firstlinecode.ui.model.RecyclerViewDataServer;

import java.util.List;

import butterknife.BindView;

public class RecyclerViewMultiItemBravhActivity extends BaseActivity {

    @BindView(R.id.title_bar_rv_multiitem_bravh)
    TitleBarLayout titleBar;
    @BindView(R.id.rv_list)
    RecyclerView mRecyclerView;

    @Override
    protected int setContentLayout() {
        return R.layout.activity_recycler_view_multi_item_bravh;
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

        List<MultiItemBravh> mData = RecyclerViewDataServer.getMultiItemBravh(10);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(mContext, 3);
        MultiItemBravhAdapter mAdapter = new MultiItemBravhAdapter(mData);
        mRecyclerView.setLayoutManager(gridLayoutManager);
        mAdapter.setSpanSizeLookup((gridLayoutManager1, position) -> mData.get(position).getSpanType());
        mRecyclerView.setAdapter(mAdapter);
    }

    @Override
    protected void initViewData() {

    }
}
