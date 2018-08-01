package com.tiny.demo.firstlinecode.ui.swipe;

import android.os.Bundle;
import android.support.v7.widget.RecyclerView;

import com.tiny.demo.firstlinecode.base.BaseActivity;
import com.tiny.demo.firstlinecode.R;
import com.tiny.demo.firstlinecode.common.utils.ToastUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Desc:    侧拉删除recyclerview，自定义。
 * Created by tiny on 2017/10/28.
 * Version:
 */

public class SwipeDeleteRecyclerViewActivity extends BaseActivity {
    @BindView(R.id.recycler_view_swipe_delete)
    RecyclerView recyclerViewSwipeDelete;

    @Override
    protected int setContentLayout() {
        return R.layout.activity_swipe_delete_recycler_view;
    }

    @Override
    protected void buildContentView() {
        init();
    }

    @Override
    protected void initViewData() {

    }

    private void init() {
        final List<String> data = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            data.add("置顶");
        }
        final SwipeDeleteAdapter swipeDeleteAdapter = new SwipeDeleteAdapter(data, this);
        swipeDeleteAdapter.setOnClickListener(new SwipeDeleteAdapter.OnClickListener() {
            @Override
            public void onMenuClick(int position, boolean top) {
                data.set(position, top ? "取消置顶" : "置顶");
            }

            @Override
            public void onContentClick(int position) {
                ToastUtils.showSingleToast("click pos = " + position);
            }
        });
        recyclerViewSwipeDelete.setAdapter(swipeDeleteAdapter);
        recyclerViewSwipeDelete.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                swipeDeleteAdapter.setScrollingMenu(null);
            }
        });
    }

}
