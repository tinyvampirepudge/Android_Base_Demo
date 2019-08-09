package com.tiny.demo.firstlinecode.ui.view;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Button;

import com.tiny.demo.firstlinecode.R;
import com.tiny.demo.firstlinecode.base.BaseActivity;
import com.tiny.demo.firstlinecode.ui.adapter.FruitAdapter;
import com.tiny.demo.firstlinecode.ui.bean.Fruit;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class RecyclerViewVerticalActivity extends BaseActivity {

    @BindView(R.id.btn_to_top)
    Button btnToTop;
    private List<Fruit> fruits;
    private RecyclerView recyclerView;
    private LinearLayoutManager linearLayoutManager;

    public static void actionStart(Context context) {
        Intent intent = new Intent(context, RecyclerViewVerticalActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected int setContentLayout() {
        return R.layout.activity_recycler_view_vertical;
    }

    @Override
    protected void buildContentView() {
        recyclerView = findViewById(R.id.recycler_view);
        initFruits();
        linearLayoutManager = new LinearLayoutManager(mContext);
        recyclerView.setLayoutManager(linearLayoutManager);
        FruitAdapter fruitAdapter = new FruitAdapter(fruits);
        recyclerView.setAdapter(fruitAdapter);
    }

    private void initFruits() {
        fruits = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            Fruit fruit = new Fruit("erha --> " + i, R.drawable.ic_erha);
            fruits.add(fruit);
        }
    }

    @Override
    protected void initViewData() {

    }

    @OnClick(R.id.btn_to_top)
    public void onViewClicked() {
        // 会有滚动效果，平滑动画，适用于列表数量不多的情景
//        recyclerView.smoothScrollToPosition(0);

        // 无滚动效果，不平滑。
        linearLayoutManager.scrollToPositionWithOffset(0,0);
    }
}
