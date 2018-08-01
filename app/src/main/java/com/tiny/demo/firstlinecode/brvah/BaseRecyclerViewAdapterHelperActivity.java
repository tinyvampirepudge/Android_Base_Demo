package com.tiny.demo.firstlinecode.brvah;

import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.tiny.demo.firstlinecode.R;
import com.tiny.demo.firstlinecode.base.BaseActivity;
import com.tiny.demo.firstlinecode.brvah.adapter.HomeAdapter;
import com.tiny.demo.firstlinecode.brvah.entity.HomeItem;
import com.tiny.demo.firstlinecode.common.interfaces.OnTitleBarClick;
import com.tiny.demo.firstlinecode.common.view.TitleBarLayout;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * BaseRecyclerViewAdapterHelper工具类demo的入口
 */
public class BaseRecyclerViewAdapterHelperActivity extends BaseActivity {
    private static Class<?>[] ACTIVITY = {AnimationUseActivity.class, MultipleItemUseActivity.class, HeaderAndFooterUseActivity.class, PullToRefreshUseActivity.class, SectionUseActivity.class,
            EmptyViewUseActivity.class, ItemDragAndSwipeUseActivity.class, ItemClickActivity.class, ExpandableUseActivity.class, DataBindingUseActivity.class, UpFetchUseActivity.class};
    private static String[] TITLE;
    private static final int[] IMG = {R.drawable.gv_animation, R.drawable.gv_multipleltem, R.drawable.gv_header_and_footer, R.drawable.gv_pulltorefresh, R.drawable.gv_section, R.drawable.gv_empty, R.drawable.gv_drag_and_swipe, R.drawable.gv_item_click, R.drawable.gv_expandable, R.drawable.gv_databinding, R.drawable.gv_up_fetch};
    @BindView(R.id.rv_bravh)
    RecyclerView rvBravh;
    @BindView(R.id.title_bar_brvah_home)
    TitleBarLayout titleBar;
    private GridLayoutManager gridLayoutManager;
    private List<HomeItem> mDataList;

    @Override
    protected int setContentLayout() {
        return R.layout.activity_base_recycler_view_adapter_helper;
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
        initView();
        initData();
        initAdapter();
    }

    private void initView() {
        gridLayoutManager = new GridLayoutManager(mContext, 2);
        rvBravh.setLayoutManager(gridLayoutManager);
    }

    private void initData() {
        TITLE = getResources().getStringArray(R.array.bravhHomeTitle);

        mDataList = new ArrayList<>();
        for (int j = 0; j < TITLE.length; j++) {
            HomeItem item = new HomeItem();
            item.setTitle(TITLE[j]);
            item.setActivity(ACTIVITY[j]);
            item.setImageResource(IMG[j]);
            mDataList.add(item);
        }
    }

    private void initAdapter() {
        BaseQuickAdapter homeAdapter = new HomeAdapter(R.layout.home_item_view, mDataList);
        homeAdapter.openLoadAnimation();
        View top = getLayoutInflater().inflate(R.layout.top_view, (ViewGroup) rvBravh.getParent(), false);
        homeAdapter.addHeaderView(top);
        homeAdapter.setOnItemClickListener((adapter, view, position) -> {
            activitySwitch(mDataList.get(position).getActivity());
        });
        rvBravh.setAdapter(homeAdapter);
    }

    @Override
    protected void initViewData() {

    }
}
