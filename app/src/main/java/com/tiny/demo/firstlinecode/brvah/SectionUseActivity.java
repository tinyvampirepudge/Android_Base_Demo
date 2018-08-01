package com.tiny.demo.firstlinecode.brvah;

import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.tiny.demo.firstlinecode.R;
import com.tiny.demo.firstlinecode.base.BaseActivity;
import com.tiny.demo.firstlinecode.brvah.adapter.SectionAdapter;
import com.tiny.demo.firstlinecode.brvah.data.DataServer;
import com.tiny.demo.firstlinecode.brvah.entity.MySection;
import com.tiny.demo.firstlinecode.common.interfaces.OnTitleBarClick;
import com.tiny.demo.firstlinecode.common.utils.ToastUtils;
import com.tiny.demo.firstlinecode.common.view.TitleBarLayout;

import java.util.List;

import butterknife.BindView;

public class SectionUseActivity extends BaseActivity {

    @BindView(R.id.title_bar_brvah_pull_to_refresh)
    TitleBarLayout titleBar;
    @BindView(R.id.rv_list)
    RecyclerView mRecyclerView;
    private List<MySection> mData;

    @Override
    protected int setContentLayout() {
        return R.layout.activity_section_use;
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

        mRecyclerView.setLayoutManager(new GridLayoutManager(mContext, 2));
        mData = DataServer.getSampleData();
        SectionAdapter sectionAdapter = new SectionAdapter(R.layout.item_section_content, R.layout.def_section_head, mData);
        sectionAdapter.setOnItemClickListener((adapter, view, position) -> {
            MySection mySection = mData.get(position);
            if (mySection.isHeader)
                ToastUtils.showSingleToast(mySection.header);
            else
                ToastUtils.showSingleToast(mySection.t.getName());
        });
        sectionAdapter.setOnItemChildClickListener((adapter, view, position) ->
                ToastUtils.showSingleToast("onItemChildClick" + position));
        mRecyclerView.setAdapter(sectionAdapter);
    }

    @Override
    protected void initViewData() {

    }

}
