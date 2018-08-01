package com.tiny.demo.firstlinecode.refresh.view.using;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;

import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.tiny.demo.firstlinecode.R;
import com.tiny.demo.firstlinecode.base.BaseActivity;
import com.tiny.demo.firstlinecode.common.utils.ToastUtils;
import com.tiny.demo.firstlinecode.refresh.adapter.BaseRecyclerAdapter;
import com.tiny.demo.firstlinecode.refresh.adapter.SmartViewHolder;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Locale;

import butterknife.BindView;

/**
 * 基本的功能使用
 */
public class BasicUsingRvActivity extends BaseActivity {

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.rv_list)
    RecyclerView mRecyclerView;
    @BindView(R.id.refreshLayout)
    RefreshLayout mRefreshLayout;
    private BaseRecyclerAdapter<Void> mAdapter;

    @Override
    protected int setContentLayout() {
        return R.layout.activity_basic_rv_using;
    }

    @Override
    protected void buildContentView() {
        toolbar.setNavigationOnClickListener(v -> finish());
        toolbar.setOnLongClickListener(v -> {
            mRefreshLayout.finishLoadmore();
            return false;
        });

        mRecyclerView.setLayoutManager(new LinearLayoutManager(mContext));
        mAdapter = new BaseRecyclerAdapter<Void>(android.R.layout.simple_list_item_2) {
            @Override
            protected void onBindViewHolder(SmartViewHolder holder, Void model, int position) {
                holder.text(android.R.id.text1, String.format(Locale.CHINA, "第%02d条数据", position));
                holder.text(android.R.id.text2, String.format(Locale.CHINA, "这是测试的第%02d条数据", position));
                holder.textColorId(android.R.id.text2, R.color.colorTextAssistant);
            }
        };
        mRecyclerView.setAdapter(mAdapter);

        //refreshLayout 设置
        mRefreshLayout.setEnableAutoLoadmore(true);//开启自动加载功能（非必须）
        mRefreshLayout.setOnRefreshListener(refreshlayout -> mRefreshLayout.getLayout().postDelayed(() -> {
            mAdapter.refresh(initData());
            mRefreshLayout.finishRefresh();
            mRefreshLayout.resetNoMoreData();
        }, 2000));
        mRefreshLayout.setOnLoadmoreListener(refreshlayout -> mRefreshLayout.getLayout().postDelayed(() -> {
            mAdapter.loadmore(initData());
            if (mAdapter.getItemCount() > 60) {
                ToastUtils.showSingleToast("\"数据全部加载完毕\"");
                mRefreshLayout.finishLoadmoreWithNoMoreData();//将不会再次触发加载更多事件
            } else {
                refreshlayout.finishLoadmore();
            }

        }, 2000));

        //触发自动刷新
        mRefreshLayout.autoRefresh();
    }

    @Override
    protected void initViewData() {

    }

    private Collection<Void> initData() {
        ArrayList<Void> list = new ArrayList<>();
        for (int j = 0; j < 10; j++) {
            list.add(null);
        }
        return list;
//        return Arrays.asList(null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null);
    }
}
