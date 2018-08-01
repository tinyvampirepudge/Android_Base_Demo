package com.tiny.demo.firstlinecode.ui.view;

import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.tiny.demo.firstlinecode.base.BaseActivity;
import com.tiny.demo.firstlinecode.R;
import com.tiny.demo.firstlinecode.ui.adapter.FruitiAdapterGrid;
import com.tiny.demo.firstlinecode.ui.bean.Fruit;

import java.util.ArrayList;
import java.util.List;

public class RecycleViewGridActivity extends BaseActivity {
    private List<Fruit> fruits;
    @Override
    protected int setContentLayout() {
        return R.layout.activity_recycle_view_grid;
    }

    @Override
    protected void buildContentView() {
        initFruits();
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recycler_view_grid);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(mContext,4);
        recyclerView.setLayoutManager(gridLayoutManager);
        FruitiAdapterGrid fruitiAdapterGrid = new FruitiAdapterGrid(fruits);
        recyclerView.setAdapter(fruitiAdapterGrid);
    }

    private void initFruits() {
        fruits = new ArrayList<>();
        for (int i = 0;i<100;i++){
            Fruit fruit = new Fruit("erha --> "+i,R.drawable.ic_erha);
            fruits.add(fruit);
        }
    }

    @Override
    protected void initViewData() {

    }
}
