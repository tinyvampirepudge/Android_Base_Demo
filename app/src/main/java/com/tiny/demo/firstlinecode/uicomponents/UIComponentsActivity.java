package com.tiny.demo.firstlinecode.uicomponents;

import android.os.Bundle;
import android.widget.Button;

import com.tiny.demo.firstlinecode.R;
import com.tiny.demo.firstlinecode.base.BaseActivity;
import com.tiny.demo.firstlinecode.uicomponents.bottomnaviview.BottomNavigationViewActivity;
import com.tiny.demo.firstlinecode.uicomponents.constraintlayout.ConstraintLayoutActivity;
import com.tiny.demo.firstlinecode.uicomponents.tablayout.TabLayoutActivity;
import com.tiny.demo.firstlinecode.uicomponents.textview.SpannableStringBuilderActivity;
import com.tiny.demo.firstlinecode.uicomponents.textview.TextViewEntryActivity;
import com.tiny.demo.firstlinecode.uicomponents.viewpager.ViewPagerPageTransformerActivity;
import com.tiny.demo.firstlinecode.uicomponents.viewpager.loop.LoopViewPagerActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Desc:    UI组件
 * Created by tiny on 2017/11/20.
 * Version:
 */

public class UIComponentsActivity extends BaseActivity {

    @BindView(R.id.btn_tablayout)
    Button btnTablayout;
    @BindView(R.id.btn_bottomNavigationView)
    Button btnBottomNavigationView;

    @Override
    protected int setContentLayout() {
        return R.layout.activity_ui_component;
    }

    @Override
    protected void buildContentView() {

    }

    @Override
    protected void initViewData() {

    }

    @OnClick(R.id.btn_tablayout)
    public void onBtnTablayoutClicked() {
        //TabLayout + ViewPager + Fragment
        activitySwitch(TabLayoutActivity.class);
    }

    @OnClick(R.id.btn_bottomNavigationView)
    public void onBtnBottomNavigationViewClicked() {
        //BottomNavigationView + ViewPager + Fragment
        activitySwitch(BottomNavigationViewActivity.class);
    }

    @OnClick(R.id.btn_constraintLayout)
    public void onConstraintLayoutClicked() {
        activitySwitch(ConstraintLayoutActivity.class);
    }

    @OnClick(R.id.btn_viewpager_page_transformer)
    public void onViewpagerPageTransformerClicked() {
        activitySwitch(ViewPagerPageTransformerActivity.class);
    }

    @OnClick(R.id.btn_loop_viewpager)
    public void onLoopViewPagerClicked() {
        activitySwitch(LoopViewPagerActivity.class);
    }

    @OnClick(R.id.btn_textview)
    public void onViewClicked() {
        TextViewEntryActivity.actionStart(this);
    }
}
