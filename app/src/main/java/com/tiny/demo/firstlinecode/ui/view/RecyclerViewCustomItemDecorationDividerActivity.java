package com.tiny.demo.firstlinecode.ui.view;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.tiny.demo.firstlinecode.R;
import com.tiny.demo.firstlinecode.base.BaseActivity;
import com.tiny.demo.firstlinecode.common.utils.AppUtils;
import com.tiny.demo.firstlinecode.ui.adapter.DividerAdapter;
import com.tiny.demo.firstlinecode.ui.bean.Fruit;
import com.tiny.demo.firstlinecode.ui.widget.MyDividerItemDecoration;
import com.tinytongtong.tinyutils.LogUtils;
import com.tinytongtong.tinyutils.ScreenUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * @Description: RecyclerView使用ItemDecoration自定义分割线
 * @Author wangjianzhou@qding.me
 * @Date 2019-08-07 11:48
 * @Version TODO
 */
public class RecyclerViewCustomItemDecorationDividerActivity extends BaseActivity {
    public static final String TAG = RecyclerViewCustomItemDecorationDividerActivity.class.getSimpleName().substring(0, 23);

    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;

    public static void actionStart(Context context) {
        Intent starter = new Intent(context, RecyclerViewCustomItemDecorationDividerActivity.class);
        context.startActivity(starter);
    }

    @Override
    protected int setContentLayout() {
        return R.layout.activity_recycler_view_custom_item_decoration_divider;
    }

    @Override
    protected void buildContentView() {

    }

    @Override
    protected void initViewData() {
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        recyclerView.addItemDecoration(new MyDividerItemDecoration());

        List<Fruit> lists = initFruits();
//        Fruit fruit = new Fruit();
//        fruit.setType(1);
//        lists.add(fruit);
        DividerAdapter bottomAdapter = new DividerAdapter(lists);

        recyclerView.setAdapter(bottomAdapter);

        recyclerView.post(() -> {
            // 获取屏幕可用高度，包含可用的navigationBar高度。
            int screenHeight = ScreenUtils.getScreenH(mContext);
            LogUtils.e(TAG, "screenHeight: " + screenHeight);

            // 获取statusBar高度
            int statusBarHeight = AppUtils.getStatusBarHeight(mContext);
            LogUtils.e(TAG, "statusBarHeight: " + statusBarHeight);

            // 获取recyclerView可滚动的高度
            int recyclerViewRealHeight = recyclerView.computeVerticalScrollRange();
            LogUtils.e(TAG, "recyclerViewRealHeight: " + recyclerViewRealHeight);

            float leftHeight = screenHeight - recyclerViewRealHeight - statusBarHeight;
            LogUtils.e(TAG, "leftHeight: " + leftHeight);

        });
    }

    private List<Fruit> initFruits() {
        List<Fruit> fruits = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            Fruit fruit = new Fruit("erha --> " + i, R.drawable.ic_erha);
            fruits.add(fruit);
        }
        return fruits;
    }
}
