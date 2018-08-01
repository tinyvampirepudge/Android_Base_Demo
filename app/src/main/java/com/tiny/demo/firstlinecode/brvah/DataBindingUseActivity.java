package com.tiny.demo.firstlinecode.brvah;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.tiny.demo.firstlinecode.R;
import com.tiny.demo.firstlinecode.base.BaseActivity;
import com.tiny.demo.firstlinecode.brvah.adapter.DataBindingUseAdapter;
import com.tiny.demo.firstlinecode.brvah.entity.Movie;
import com.tiny.demo.firstlinecode.common.interfaces.OnTitleBarClick;
import com.tiny.demo.firstlinecode.common.utils.ToastUtils;
import com.tiny.demo.firstlinecode.common.view.TitleBarLayout;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import butterknife.BindView;

public class DataBindingUseActivity extends BaseActivity {

    @BindView(R.id.title_bar_brvah_data_binding)
    TitleBarLayout titleBar;
    @BindView(R.id.rv_list)
    RecyclerView mRecyclerView;
    private DataBindingUseAdapter mAdapter;

    @Override
    protected int setContentLayout() {
        return R.layout.activity_data_binding_use;
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

        mAdapter = new DataBindingUseAdapter(R.layout.item_movie, genData());
        mRecyclerView.setLayoutManager(new LinearLayoutManager(mContext));
        mRecyclerView.setAdapter(mAdapter);
        mAdapter.setOnItemClickListener((adapter, view, position) -> {
            ToastUtils.showSingleToast("onItemClick");
        });
        mAdapter.setOnItemChildLongClickListener((adapter, view, position) -> {
            ToastUtils.showSingleToast("onItemChildLongClick");
            return true;
        });
        mAdapter.setOnItemLongClickListener((adapter, view, position) -> {
            ToastUtils.showSingleToast("onItemLongClick");
            return true;
        });
    }


    private List<Movie> genData() {
        ArrayList<Movie> list = new ArrayList<>();
        Random random = new Random();
        for (int i = 0; i < 10; i++) {
            String name = "Chad";
            int price = random.nextInt(10) + 10;
            int len = random.nextInt(80) + 60;
            Movie movie = new Movie(name, len, price, "He was one of Australia's most distinguished artistes");
            list.add(movie);
        }
        return list;
    }

    @Override
    protected void initViewData() {

    }
}
