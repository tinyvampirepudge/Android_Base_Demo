package com.tiny.demo.firstlinecode.ui.view;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.tiny.demo.firstlinecode.R;
import com.tiny.demo.firstlinecode.base.BaseActivity;
import com.tiny.demo.firstlinecode.common.utils.AppUtils;
import com.tiny.demo.firstlinecode.ui.adapter.DividerAdapter;
import com.tiny.demo.firstlinecode.ui.bean.Fruit;
import com.tinytongtong.tinyutils.LogUtils;
import com.tinytongtong.tinyutils.ScreenUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * @Description: 使用系统提供的DividerItemDecoration绘制分割线
 * https://www.jianshu.com/p/86aaaa49ed3e
 * @Author wangjianzhou@qding.me
 * @Date 2019-08-07 11:04
 * @Version
 */
public class RecyclerViewDividerItemDecorationActivity extends BaseActivity {
    public static final String TAG = RecyclerViewDividerItemDecorationActivity.class.getSimpleName().substring(0, 23);
    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;

    public static void actionStart(Context context) {
        Intent starter = new Intent(context, RecyclerViewDividerItemDecorationActivity.class);
        context.startActivity(starter);
    }

    @Override
    protected int setContentLayout() {
        return R.layout.activity_recycler_view_divider_item_decoration;
    }

    @Override
    protected void buildContentView() {

    }

    @Override
    protected void initViewData() {
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
//        recyclerView.addItemDecoration(new MyDividerItemDecoration());
        // DividerItemDecoration会改变item的高度
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(this, DividerItemDecoration.VERTICAL);
        // 可以通过setDrawable方法使用自定义样式的分割线
        // 1、设置颜色——每个item高度减少1px，很奇怪，竟然减少了
//        dividerItemDecoration.setDrawable(new ColorDrawable(ContextCompat.getColor(mContext,R.color.colorPrimary)));

        // 2、设置shape——每个item高度增加shape的高度，这里是10dp
        Drawable drawable = ContextCompat.getDrawable(mContext, R.drawable.shape_divider);
        dividerItemDecoration.setDrawable(drawable);

        // 3、设置图片——分割线的高度就是图片的高度，图片会有拉伸。图片大小36*5
//        Drawable drawableIcon = ContextCompat.getDrawable(mContext,R.drawable.ic_gray_line);
//        dividerItemDecoration.setDrawable(drawableIcon);

        recyclerView.addItemDecoration(dividerItemDecoration);

        List<Fruit> lists = initFruits();
//        Fruit fruit = new Fruit();
//        fruit.setType(1);
//        lists.add(fruit);
        DividerAdapter dividerAdapter = new DividerAdapter(lists);

        recyclerView.setAdapter(dividerAdapter);

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
