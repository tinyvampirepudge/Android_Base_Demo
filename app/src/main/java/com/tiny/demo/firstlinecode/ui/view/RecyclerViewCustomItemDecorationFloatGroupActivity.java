package com.tiny.demo.firstlinecode.ui.view;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.tiny.demo.firstlinecode.R;
import com.tiny.demo.firstlinecode.base.BaseActivity;
import com.tiny.demo.firstlinecode.common.utils.AppUtils;
import com.tiny.demo.firstlinecode.ui.bean.City;
import com.tiny.demo.firstlinecode.ui.bean.CityUtil;
import com.tiny.demo.firstlinecode.ui.listener.GroupListener;
import com.tiny.demo.firstlinecode.ui.listener.OnGroupClickListener;
import com.tiny.demo.firstlinecode.ui.widget.GroupFloatItemDecorationGetItemOffsetsAndOnDrawOver;
import com.tiny.demo.firstlinecode.ui.widget.StickyDecoration;
import com.tinytongtong.tinyutils.LogUtils;
import com.tinytongtong.tinyutils.ScreenUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * @Description: RecyclerView使用ItemDecoration自定义分组悬停效果。分组悬停，悬停布局直接在Item上层绘制，不是复用Item的View
 * {@link StickyDecoration}
 * {@link GroupFloatItemDecorationGetItemOffsetsAndOnDrawOver}
 * @Author wangjianzhou@qding.me
 * @Date 2019-08-07 11:50
 * @Version
 */
public class RecyclerViewCustomItemDecorationFloatGroupActivity extends BaseActivity {
    public static final String TAG = RecyclerViewCustomItemDecorationFloatGroupActivity.class.getSimpleName().substring(0, 23);

    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;

    List<City> dataList = new ArrayList<>();

    public static void actionStart(Context context) {
        Intent starter = new Intent(context, RecyclerViewCustomItemDecorationFloatGroupActivity.class);
        context.startActivity(starter);
    }

    @Override
    protected int setContentLayout() {
        return R.layout.activity_recycler_view_custom_item_decoration_float_group;
    }

    @Override
    protected void buildContentView() {

    }

    @Override
    protected void initViewData() {
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        //模拟数据
        dataList.addAll(CityUtil.getCityList());
        dataList.addAll(CityUtil.getCityList());

        //------------- StickyDecoration 使用部分  ----------------
        StickyDecoration decoration = StickyDecoration.Builder
                .init(new GroupListener() {
                    @Override
                    public String getGroupName(int position) {
                        //组名回调
                        if (dataList.size() > position && position > -1) {
                            //获取组名，用于判断是否是同一组
                            return dataList.get(position).getProvince();
                        }
                        return null;
                    }
                })
                .setGroupBackground(Color.parseColor("#48BDFF"))        //背景色
                .setGroupHeight(ScreenUtils.dip2px(this, 35))     //高度
                .setDivideColor(Color.parseColor("#EE96BC"))            //分割线颜色
                .setDivideHeight(ScreenUtils.dip2px(this, 2))     //分割线高度 (默认没有分割线)
                .setGroupTextColor(Color.BLACK)                                    //字体颜色 （默认）
                .setGroupTextSize(ScreenUtils.INSTANCE.sp2px(this, 15))    //字体大小
                .setTextSideMargin(ScreenUtils.dip2px(this, 10))  // 边距   靠左时为左边距  靠右时为右边距
                //.setHeaderCount(2)                                             // header数量（默认0）
                .setOnClickListener(new OnGroupClickListener() {                   //点击事件，返回当前分组下的第一个item的position
                    @Override
                    public void onClick(int position, int id) {                                 //Group点击事件
                        String content = "onGroupClick --> " + dataList.get(position).getProvince();
                        Toast.makeText(mContext, content, Toast.LENGTH_SHORT).show();
                    }
                })
                .build();
//        recyclerView.addItemDecoration(decoration);
        //------------- StickyDecoration 使用部分  ----------------

        GroupFloatItemDecorationGetItemOffsetsAndOnDrawOver groupFloatItemDecorationGetItemOffsetsAndOnDrawOver = new GroupFloatItemDecorationGetItemOffsetsAndOnDrawOver(position -> {
            //组名回调
            if (dataList.size() > position && position > -1) {
                //获取组名，用于判断是否是同一组
                return dataList.get(position).getProvince();
            }
            return null;
        });
        recyclerView.addItemDecoration(groupFloatItemDecorationGetItemOffsetsAndOnDrawOver);

        RecyclerView.Adapter mAdapter = new RecyclerView.Adapter() {
            @Override
            public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
                View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_recycler_view, parent, false);
                return new Holder(view);
            }

            @Override
            public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, final int position) {
                Holder holder = (Holder) viewHolder;
                holder.mTextView.setText(dataList.get(position).getName());
                holder.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Toast.makeText(mContext, "item click " + position, Toast.LENGTH_LONG).show();
                    }
                });
            }

            @Override
            public int getItemCount() {
                return dataList.size();
            }
        };
        recyclerView.setAdapter(mAdapter);

        recyclerView.post(() -> {
            // 获取屏幕可用高度，包含可用的navigationBar高度。
            int screenHeight = ScreenUtils.INSTANCE.getScreenH(mContext);
            LogUtils.INSTANCE.e(TAG, "screenHeight: " + screenHeight);

            // 获取statusBar高度
            int statusBarHeight = AppUtils.getStatusBarHeight(mContext);
            LogUtils.INSTANCE.e(TAG, "statusBarHeight: " + statusBarHeight);

            // 获取recyclerView可滚动的高度
            int recyclerViewRealHeight = recyclerView.computeVerticalScrollRange();
            LogUtils.INSTANCE.e(TAG, "recyclerViewRealHeight: " + recyclerViewRealHeight);

            float leftHeight = screenHeight - recyclerViewRealHeight - statusBarHeight;
            LogUtils.INSTANCE.e(TAG, "leftHeight: " + leftHeight);

        });
    }

    static class Holder extends RecyclerView.ViewHolder {
        TextView mTextView;

        public Holder(View itemView) {
            super(itemView);
            mTextView = itemView.findViewById(R.id.tv);
        }
    }
}
