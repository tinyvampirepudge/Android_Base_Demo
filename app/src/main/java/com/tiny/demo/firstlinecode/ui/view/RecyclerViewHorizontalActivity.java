package com.tiny.demo.firstlinecode.ui.view;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.tiny.demo.firstlinecode.base.BaseActivity;
import com.tiny.demo.firstlinecode.R;
import com.tiny.demo.firstlinecode.ui.adapter.FruitiAdapterHorizontal;
import com.tiny.demo.firstlinecode.ui.bean.Fruit;

import java.util.ArrayList;
import java.util.List;

public class RecyclerViewHorizontalActivity extends BaseActivity {

    private List<Fruit> fruits;

    public static void actionStart(Context context){
        Intent intent = new Intent(context,RecyclerViewHorizontalActivity.class);
        context.startActivity(intent);
    }
    @Override
    protected int setContentLayout() {
        return R.layout.activity_recycler_view_horizontal;
    }

    @Override
    protected void buildContentView() {
        initFruits();
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recycler_view_h);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(mContext);
        linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        recyclerView.setLayoutManager(linearLayoutManager);
        FruitiAdapterHorizontal fruitiAdapterHorizontal = new FruitiAdapterHorizontal(fruits);
        recyclerView.setAdapter(fruitiAdapterHorizontal);
    }

    private void initFruits() {
        fruits = new ArrayList<>();
        for (int i = 0;i<100;i++){
            Fruit fruit = new Fruit(getString(R.string.string_erha)+i,R.drawable.ic_erha);
            fruits.add(fruit);
        }
    }

    @Override
    protected void initViewData() {

    }
}
