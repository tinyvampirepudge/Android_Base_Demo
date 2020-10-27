package com.tiny.demo.firstlinecode.brvah;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.tiny.demo.firstlinecode.R;
import com.tiny.demo.firstlinecode.base.BaseActivity;
import com.tiny.demo.firstlinecode.brvah.adapter.ItemClickAdapter;
import com.tiny.demo.firstlinecode.brvah.entity.ClickEntity;
import com.tiny.demo.firstlinecode.common.interfaces.OnTitleBarClick;
import com.tinytongtong.tinyutils.LogUtils;
import com.tiny.demo.firstlinecode.common.utils.ToastUtils;
import com.tiny.demo.firstlinecode.common.view.TitleBarLayout;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ItemClickActivity extends BaseActivity {
    public static final String TAG = ItemClickActivity.class.getSimpleName();

    @BindView(R.id.title_bar_brvah_item_click)
    TitleBarLayout titleBar;
    @BindView(R.id.rv_list)
    RecyclerView mRecyclerView;
    private ItemClickAdapter adapter;

    @Override
    protected int setContentLayout() {
        return R.layout.activity_item_click;
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

        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        initAdapter();

        adapter.setOnItemClickListener((adapter, view, position) -> {
            LogUtils.INSTANCE.e(TAG, "onItemClick");
            ToastUtils.showSingleToast("onItemClick: " + position);
        });
        adapter.setOnItemLongClickListener((adapter, view, position) -> {
            LogUtils.INSTANCE.d(TAG, "onItemLongClick");
            ToastUtils.showSingleToast("onItemLongClick: " + position);
            return true;
        });
        adapter.setOnItemChildClickListener((adapter, view, position) -> {
            LogUtils.INSTANCE.d(TAG, "onItemChildClick");
            ToastUtils.showSingleToast("onItemChildClick: " + position);
        });
        adapter.setOnItemChildLongClickListener((adapter, view, position) -> {
            LogUtils.INSTANCE.d(TAG, "onItemChildLongClick");
            ToastUtils.showSingleToast("onItemChildLongClick: " + position);
            return true;
        });
    }

    private void initAdapter() {
        List<ClickEntity> data = new ArrayList<>();
        data.add(new ClickEntity(ClickEntity.CLICK_ITEM_VIEW));
        data.add(new ClickEntity(ClickEntity.CLICK_ITEM_CHILD_VIEW));
        data.add(new ClickEntity(ClickEntity.LONG_CLICK_ITEM_VIEW));
        data.add(new ClickEntity(ClickEntity.LONG_CLICK_ITEM_CHILD_VIEW));
        data.add(new ClickEntity(ClickEntity.NEST_CLICK_ITEM_CHILD_VIEW));
        adapter = new ItemClickAdapter(data);
        adapter.openLoadAnimation();
        mRecyclerView.setAdapter(adapter);
    }

    @Override
    protected void initViewData() {

    }

}
