package com.tiny.demo.firstlinecode.uicomponents.viewpager;

import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.tiny.demo.firstlinecode.R;
import com.tiny.demo.firstlinecode.base.BaseActivity;
import com.tiny.demo.firstlinecode.common.utils.ToastUtils;
import com.tiny.demo.firstlinecode.uicomponents.viewpager.pagetransformer.ScaleDownPageTransformer;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Desc:    自定义的无限循环轮播。
 * Created by tiny on 2018/1/19 下午4:30
 * Version:
 */
public class ViewPagerPageTransformerActivity extends BaseActivity {
    public static final int LIST_SIZE = 10;

    @BindView(R.id.id_viewpager)
    MyLoopViewPager mViewPager;
    @BindView(R.id.fl_vp_parent)
    FrameLayout flVpParent;

    @Override
    protected int setContentLayout() {
        return R.layout.activity_view_pgaer_page_transformer;
    }

    @Override
    protected void buildContentView() {
        List<TextView> viewList = getViewLists();
        PageTransformerAdapter mAdapter = new PageTransformerAdapter(mContext, viewList);
        //设置Page间间距
        mViewPager.setPageMargin(20);
        //设置缓存的页面数量
        //这个如果设置为1，或者使用默认值1时，在向左滑动时，右面新加载出的view第一次加载时的缩放动画不显示。。
        mViewPager.setOffscreenPageLimit(2);
        mViewPager.setPageTransformer(true, new ScaleDownPageTransformer());
        mViewPager.setAdapter(mAdapter);

        int startPos = 1000 * viewList.size() + 1;
        mViewPager.setCurrentItem(startPos);
        mViewPager.start();

        //扩大ViewPager的滑动区域
        flVpParent.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                return mViewPager.onTouchEvent(event);
            }
        });
    }

    @Override
    protected void initViewData() {

    }

    @Override
    protected void onStart() {
        super.onStart();
        if (mViewPager != null) {
            mViewPager.start();
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        if (mViewPager != null) {
            mViewPager.stop();
        }
    }

    private List<TextView> getViewLists() {
        List<TextView> list = new ArrayList<>();
        for (int j = 0; j < LIST_SIZE; j++) {
            TextView tv = generateTextView(j);
            list.add(tv);
        }
        return list;
    }

    private TextView generateTextView(int j) {
        TextView tv = new TextView(this);
        tv.setBackgroundResource(R.drawable.bg_lunbo);
        String str = "这个是第" + (j + 1) + "个View";
        tv.setTag(str);
        tv.setText(str);
        tv.setTextColor(getResources().getColor(R.color.font_red));
        tv.setTextSize(30);
        tv.setGravity(Gravity.CENTER);
        int finalJ = j;
        tv.setOnClickListener(v -> {
            ToastUtils.showSingleToast("第" + (finalJ + 1) + "个View被点击了");
        });
        return tv;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }
}
