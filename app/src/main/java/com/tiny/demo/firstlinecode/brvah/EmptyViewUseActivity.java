package com.tiny.demo.firstlinecode.brvah;

import android.os.Handler;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import com.tiny.demo.firstlinecode.R;
import com.tiny.demo.firstlinecode.base.BaseActivity;
import com.tiny.demo.firstlinecode.brvah.adapter.QuickAdapter;
import com.tiny.demo.firstlinecode.brvah.data.DataServer;
import com.tiny.demo.firstlinecode.common.interfaces.OnTitleBarClick;
import com.tiny.demo.firstlinecode.common.view.TitleBarLayout;

import butterknife.BindView;
import butterknife.OnClick;

public class EmptyViewUseActivity extends BaseActivity {

    @BindView(R.id.title_bar_brvah_empty)
    TitleBarLayout titleBar;
    @BindView(R.id.rv_list)
    RecyclerView mRecyclerView;
    @BindView(R.id.btn_reset)
    FloatingActionButton btnReset;
    private View notDataView;
    private View errorView;
    private QuickAdapter mQuickAdapter;

    private boolean mError = true;
    private boolean mNoData = true;

    @Override
    protected int setContentLayout() {
        return R.layout.activity_empty_view_use;
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
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        notDataView = getLayoutInflater().inflate(R.layout.empty_view, (ViewGroup) mRecyclerView.getParent(), false);
        notDataView.setOnClickListener(v -> onRefresh());

        errorView = getLayoutInflater().inflate(R.layout.error_view, (ViewGroup) mRecyclerView.getParent(), false);
        errorView.setOnClickListener(v -> onRefresh());
        initAdapter();
        onRefresh();
    }

    private void initAdapter() {
        mQuickAdapter = new QuickAdapter(R.layout.layout_animation, DataServer.getSampleData(0));
        mRecyclerView.setAdapter(mQuickAdapter);
    }

    private void onRefresh() {
        mQuickAdapter.setEmptyView(R.layout.loading_view, (ViewGroup) mRecyclerView.getParent());
        new Handler().postDelayed(() -> {
            if (mError) {
                mQuickAdapter.setEmptyView(errorView);
                mError = false;
            } else {
                if (mNoData) {
                    mQuickAdapter.setEmptyView(notDataView);
                    mNoData = false;
                } else {
                    mQuickAdapter.setNewData(DataServer.getSampleData(10));
                }
            }
        }, 1000);
    }

    @Override
    protected void initViewData() {

    }

    @OnClick(R.id.btn_reset)
    public void onViewClicked() {
        mError = true;
        mNoData = true;
        mQuickAdapter.setNewData(null);
        onRefresh();
    }
}
