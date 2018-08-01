package com.tiny.demo.firstlinecode.refresh.view.using;

import android.support.v7.widget.Toolbar;

import com.coder.zzq.smartshow.toast.SmartToast;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.tiny.demo.firstlinecode.R;
import com.tiny.demo.firstlinecode.base.BaseActivity;

import butterknife.BindView;

/**
 * RefreshLayout 与 ScrollView配合使用
 */
public class BasicUsingSvActivity extends BaseActivity {

    @BindView(R.id.refreshLayout)
    SmartRefreshLayout mRefreshLayout;
    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @Override
    protected int setContentLayout() {
        return R.layout.activity_basic_using_sv;
    }

    @Override
    protected void buildContentView() {
        toolbar.setNavigationOnClickListener(v -> finish());
        toolbar.setOnLongClickListener(v -> {
            mRefreshLayout.finishLoadmore();
            return false;
        });

        mRefreshLayout.setOnRefreshListener(refreshlayout -> mRefreshLayout.getLayout().postDelayed(() -> {
            SmartToast.show("下拉刷新");
            mRefreshLayout.finishRefresh();
            mRefreshLayout.resetNoMoreData();
        }, 2000));

        mRefreshLayout.setOnLoadmoreListener(refreshlayout -> mRefreshLayout.getLayout().postDelayed(() -> {
            SmartToast.show("上拉加载更多");
            mRefreshLayout.finishLoadmore();
        }, 2000));

        mRefreshLayout.autoRefresh();
    }

    @Override
    protected void initViewData() {

    }
}
