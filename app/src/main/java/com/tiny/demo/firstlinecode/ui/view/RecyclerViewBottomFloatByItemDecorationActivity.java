package com.tiny.demo.firstlinecode.ui.view;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.tiny.demo.firstlinecode.R;
import com.tiny.demo.firstlinecode.base.BaseActivity;
import com.tiny.demo.firstlinecode.ui.adapter.BottomAdapter;
import com.tiny.demo.firstlinecode.ui.bean.Fruit;
import com.tiny.demo.firstlinecode.ui.widget.BottomFloatItemDecoration;
import com.tinytongtong.tinyutils.LogUtils;
import com.tinytongtong.tinyutils.ScreenUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * @Description: RecyclerView实现吸底效果，通过ItemDecoration
 * @Author wangjianzhou@qding.me
 * @Date 2019-08-06 22:22
 * @Version TODO
 */
public class RecyclerViewBottomFloatByItemDecorationActivity extends BaseActivity {

    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;

    public static void actionStart(Context context) {
        Intent starter = new Intent(context, RecyclerViewBottomFloatByItemDecorationActivity.class);
        context.startActivity(starter);
    }

    @Override
    protected int setContentLayout() {
        return R.layout.activity_recycler_view_bottom_float_by_item_decoration;
    }

    @Override
    protected void buildContentView() {
        LogUtils.INSTANCE.e(TAG, ScreenUtils.INSTANCE.getDisplayMetricsInfo(this));
    }

    @Override
    protected void initViewData() {
        LinearLayoutManager llm = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(llm);
        recyclerView.addItemDecoration(new BottomFloatItemDecoration(this));

        List<Fruit> lists = initFruits();
        Fruit fruit = new Fruit();
        fruit.setType(1);
        lists.add(fruit);
        BottomAdapter bottomAdapter = new BottomAdapter(lists);

        recyclerView.setAdapter(bottomAdapter);
    }

    private List<Fruit> initFruits() {
        List<Fruit> fruits = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            Fruit fruit = new Fruit("erha --> " + i, R.drawable.ic_erha);
            fruits.add(fruit);
        }
        return fruits;
    }

}
