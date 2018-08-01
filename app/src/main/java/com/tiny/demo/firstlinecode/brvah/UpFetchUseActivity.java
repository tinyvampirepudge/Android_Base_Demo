package com.tiny.demo.firstlinecode.brvah;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.tiny.demo.firstlinecode.R;
import com.tiny.demo.firstlinecode.base.BaseActivity;
import com.tiny.demo.firstlinecode.brvah.adapter.UpFetchAdapter;
import com.tiny.demo.firstlinecode.brvah.entity.Movie;
import com.tiny.demo.firstlinecode.common.interfaces.OnTitleBarClick;
import com.tiny.demo.firstlinecode.common.view.TitleBarLayout;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import butterknife.BindView;

public class UpFetchUseActivity extends BaseActivity {

    @BindView(R.id.title_bar_brvah_upfetch)
    TitleBarLayout titleBar;
    @BindView(R.id.rv_list)
    RecyclerView mRecyclerView;
    private UpFetchAdapter mAdapter;
    private int count;

    @Override
    protected int setContentLayout() {
        return R.layout.activity_up_fetch_use;
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

        mAdapter = new UpFetchAdapter(R.layout.item_movie, null);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(mContext));
        mRecyclerView.setAdapter(mAdapter);
        mAdapter.setNewData(genData());
        mAdapter.setUpFetchEnable(true);
        /**
         * start fetch when scroll to position 2, default is 1.
         */
        mAdapter.setStartUpFetchPosition(2);
        mAdapter.setUpFetchListener(() -> startUpFetch());
    }

    private void startUpFetch() {
        count++;
        /**
         * set fetching on when start network request.
         */
        mAdapter.setUpFetching(true);
        /**
         * get data from internet.
         */
        mRecyclerView.postDelayed(() -> {
            mAdapter.addData(0, genData());
            /**
             * set fetching off when network request ends.
             */
            mAdapter.setUpFetching(false);
            /**
             * set fetch enable false when you don't need anymore.
             */
            if (count > 5) {
                mAdapter.setUpFetchEnable(false);
            }
        }, 300);
    }

    @Override
    protected void initViewData() {

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

}
