package com.tiny.demo.firstlinecode.brvah;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.tiny.demo.firstlinecode.R;
import com.tiny.demo.firstlinecode.base.BaseActivity;
import com.tiny.demo.firstlinecode.brvah.adapter.HeaderAndFooterAdapter;
import com.tiny.demo.firstlinecode.brvah.data.DataServer;
import com.tiny.demo.firstlinecode.common.interfaces.OnTitleBarClick;
import com.tiny.demo.firstlinecode.common.view.TitleBarLayout;

import butterknife.BindView;

public class HeaderAndFooterUseActivity extends BaseActivity {
    private static final int PAGE_SIZE = 3;
    @BindView(R.id.title_bar_brvah_header_and_footer)
    TitleBarLayout titleBarLayout;
    @BindView(R.id.rv_list)
    RecyclerView mRecyclerView;
    private HeaderAndFooterAdapter headerAndFooterAdapter;

    @Override
    protected int setContentLayout() {
        return R.layout.activity_header_and_footer_use;
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
        mRecyclerView.setLayoutManager(new LinearLayoutManager(mContext));
        initAdapter();
        View headerView = getHeaderView(0, v -> headerAndFooterAdapter.addHeaderView(getHeaderView(1, getRemoveHeaderListener()), 0));
        headerAndFooterAdapter.addHeaderView(headerView);

        View footerView = getFooterView(0, v -> headerAndFooterAdapter.addFooterView(getFooterView(1, getRemoveFooterListener()), 0));
        headerAndFooterAdapter.addFooterView(footerView, 0);

        mRecyclerView.setAdapter(headerAndFooterAdapter);
    }

    private void initAdapter() {
        headerAndFooterAdapter = new HeaderAndFooterAdapter(PAGE_SIZE);
        headerAndFooterAdapter.openLoadAnimation();
        mRecyclerView.setAdapter(headerAndFooterAdapter);
//        mRecyclerView.addOnItemTouchListener(new OnItemClickListener() {
//            @Override
//            public void onSimpleItemClick(BaseQuickAdapter adapter, View view, int position) {
//                Toast.makeText(HeaderAndFooterUseActivity.this, "" + Integer.toString(position), Toast.LENGTH_LONG).show();
//            }
//        });
        headerAndFooterAdapter.setOnItemClickListener((adapter, view, position) -> {
            adapter.setNewData(DataServer.getSampleData(PAGE_SIZE));
            Toast.makeText(HeaderAndFooterUseActivity.this, "" + Integer.toString(position), Toast.LENGTH_LONG).show();
        });
    }

    private View getHeaderView(int type, View.OnClickListener listener) {
        View view = getLayoutInflater().inflate(R.layout.head_view, (ViewGroup) mRecyclerView.getParent(), false);
        if (type == 1) {
            ImageView imageView = view.findViewById(R.id.iv);
            imageView.setImageResource(R.drawable.rm_icon);
        }
        view.setOnClickListener(listener);
        return view;
    }

    private View getFooterView(int type, View.OnClickListener listener) {
        View view = getLayoutInflater().inflate(R.layout.footer_view, (ViewGroup) mRecyclerView.getParent(), false);
        if (type == 1) {
            ImageView imageView = view.findViewById(R.id.iv);
            imageView.setImageResource(R.drawable.rm_icon);
        }
        view.setOnClickListener(listener);
        return view;
    }

    private View.OnClickListener getRemoveHeaderListener() {
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                headerAndFooterAdapter.removeHeaderView(v);
            }
        };
    }


    private View.OnClickListener getRemoveFooterListener() {
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                headerAndFooterAdapter.removeFooterView(v);
            }
        };
    }

    @Override
    protected void initViewData() {

    }
}
