package com.tiny.demo.firstlinecode.ui.view;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.tiny.demo.firstlinecode.R;
import com.tiny.demo.firstlinecode.base.BaseActivity;
import com.tiny.demo.firstlinecode.common.interfaces.OnTitleBarClick;
import com.tiny.demo.firstlinecode.common.view.TitleBarLayout;
import com.tiny.demo.firstlinecode.ui.adapter.MultiItemAdapter1;
import com.tiny.demo.firstlinecode.ui.bean.MultiItem1;
import com.tiny.demo.firstlinecode.ui.model.RecyclerViewDataServer;

import java.util.List;

import butterknife.BindView;

/**
 * recycler view 多种类型的item
 */
public class RecyclerViewMultiItemActivity1 extends BaseActivity {

    @BindView(R.id.title_bar_rv_multiitem)
    TitleBarLayout titleBar;
    @BindView(R.id.rv_list)
    RecyclerView mRecyclerView;

    @Override
    protected int setContentLayout() {
        return R.layout.activity_recycler_view_multi_item;
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
        List<MultiItem1> mData = RecyclerViewDataServer.getMultiItem1List();
        MultiItemAdapter1 mAdapter = new MultiItemAdapter1(mContext, mData);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(mContext));
        mRecyclerView.setAdapter(mAdapter);
    }

    @Override
    protected void initViewData() {

    }
}
