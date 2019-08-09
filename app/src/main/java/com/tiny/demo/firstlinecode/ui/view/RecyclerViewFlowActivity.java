package com.tiny.demo.firstlinecode.ui.view;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;

import com.tiny.demo.firstlinecode.base.BaseActivity;
import com.tiny.demo.firstlinecode.R;
import com.tiny.demo.firstlinecode.ui.adapter.FruitiAdapterFlow;
import com.tiny.demo.firstlinecode.ui.bean.Fruit;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class RecyclerViewFlowActivity extends BaseActivity {
    private List<Fruit> fruits;

    public static void actionStart(Context context){
        Intent intent = new Intent(context,RecyclerViewFlowActivity.class);
        context.startActivity(intent);
    }
    @Override
    protected int setContentLayout() {
        return R.layout.activity_recycler_view_flow;
    }

    @Override
    protected void buildContentView() {
        initFruits();
        RecyclerView recyclerView = findViewById(R.id.recycler_view_flow);
        StaggeredGridLayoutManager layoutManager = new StaggeredGridLayoutManager(3,StaggeredGridLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);
        FruitiAdapterFlow fruitiAdapterFlow = new FruitiAdapterFlow(fruits);
        recyclerView.setAdapter(fruitiAdapterFlow);
    }

    private void initFruits() {
        fruits = new ArrayList<>();
        for (int i = 0;i<100;i++){
            Fruit fruit = new Fruit(getRandomLengthName("Orange ")+i,R.drawable.ic_erha);
            fruits.add(fruit);
        }
    }

    private String getRandomLengthName(String name){
        Random random = new Random();
        int length = random.nextInt(20) -1;
        StringBuilder builder = new StringBuilder();
        for (int i=0;i<length;i++){
            builder.append(name);
        }
        return builder.toString();
    }

    @Override
    protected void initViewData() {

    }
}
