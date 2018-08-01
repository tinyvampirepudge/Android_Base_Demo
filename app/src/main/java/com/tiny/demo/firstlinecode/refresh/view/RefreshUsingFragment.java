package com.tiny.demo.firstlinecode.refresh.view;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.AdapterView;

import com.tiny.demo.firstlinecode.R;
import com.tiny.demo.firstlinecode.base.BaseFragment;
import com.tiny.demo.firstlinecode.refresh.FragmentActivity;
import com.tiny.demo.firstlinecode.refresh.adapter.BaseRecyclerAdapter;
import com.tiny.demo.firstlinecode.refresh.adapter.SmartViewHolder;
import com.tiny.demo.firstlinecode.refresh.util.StatusBarUtil;
import com.tiny.demo.firstlinecode.refresh.view.using.BasicUsingRvActivity;
import com.tiny.demo.firstlinecode.refresh.view.using.BasicUsingSvActivity;

import java.util.Arrays;

import butterknife.BindView;

/**
 * Desc:
 * Created by tiny on 2017/12/13.
 * Version:
 */

public class RefreshUsingFragment extends BaseFragment implements AdapterView.OnItemClickListener {
    @BindView(R.id.rv_list)
    RecyclerView mRecyclerView;

    public enum Item {
        BasicRv("基本的使用——RecyclerView", BasicUsingRvActivity.class),
        BasicSv("基本的使用——ScrollView", BasicUsingSvActivity.class);

        private String name;
        private Class<?> clazz;

        Item(String name, Class<?> clazz) {
            this.name = name;
            this.clazz = clazz;
        }
    }

    @Override
    protected int setContentLayout() {
        return R.layout.fragment_refresh_using;
    }

    @Override
    protected void buildContentView(View root, Bundle savedInstanceState) {
        StatusBarUtil.setPaddingSmart(getContext(), root.findViewById(R.id.toolbar));

        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        mRecyclerView.setLayoutManager(new LinearLayoutManager(mContext));
        mRecyclerView.addItemDecoration(new DividerItemDecoration(mContext, DividerItemDecoration.VERTICAL));
        mRecyclerView.setAdapter(new BaseRecyclerAdapter<Item>(Arrays.asList(Item.values()), android.R.layout.simple_list_item_2, this) {

            @Override
            protected void onBindViewHolder(SmartViewHolder holder, Item model, int position) {
                holder.text(android.R.id.text1, model.name());
                holder.text(android.R.id.text2, model.name);
                holder.textColorId(android.R.id.text2, R.color.colorTextAssistant);
            }
        });
    }

    @Override
    protected void initViewData() {

    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Item item = Item.values()[position];
        if (Activity.class.isAssignableFrom(item.clazz)) {
            startActivity(new Intent(getContext(), item.clazz));
        } else if (Fragment.class.isAssignableFrom(item.clazz)) {
            FragmentActivity.start(this, item.clazz);
        }
    }

}
