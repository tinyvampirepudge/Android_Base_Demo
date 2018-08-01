package com.tiny.demo.firstlinecode.ui.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.tiny.demo.firstlinecode.R;
import com.tiny.demo.firstlinecode.common.utils.ToastUtils;
import com.tiny.demo.firstlinecode.ui.bean.MultiItem1;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Desc:
 * Created by tiny on 2017/12/13.
 * Version:
 */

public class MultiItemAdapter1 extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    public enum TypeEnum {
        TYPE1, TYPE2, TYPE3
    }

    private Context mContext;
    private List<MultiItem1> list;
    LayoutInflater mLayoutInflater;

    public MultiItemAdapter1(Context mContext, List<MultiItem1> list) {
        this.mContext = mContext;
        this.list = list;
        mLayoutInflater = LayoutInflater.from(this.mContext);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == TypeEnum.TYPE1.ordinal()) {
            return new MultiItemViewHolder1(mLayoutInflater.inflate(R.layout.adapter_multi_item_1, parent, false));
        } else if (viewType == TypeEnum.TYPE2.ordinal()) {
            return new MultiItemViewHolder2(mLayoutInflater.inflate(R.layout.adapter_multi_item_2, parent, false));
        } else {
            return new MultiItemViewHolder3(mLayoutInflater.inflate(R.layout.adapter_multi_item_3, parent, false));
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof MultiItemViewHolder1) {
            ((MultiItemViewHolder1) holder).title1.setText(list.get(position).name);
            ((MultiItemViewHolder1) holder).values1.setText(list.get(position).gender);
        } else if (holder instanceof MultiItemViewHolder2) {
            ((MultiItemViewHolder2) holder).title2.setText(list.get(position).name);
            ((MultiItemViewHolder2) holder).values2.setImageResource(R.drawable.ic_erha);
        } else {
            ((MultiItemViewHolder3) holder).title3.setText(list.get(position).name);
            ((MultiItemViewHolder3) holder).values3.setText(list.get(position).gender);
            ((MultiItemViewHolder3) holder).values3.setOnClickListener(v -> ToastUtils.showSingleToast(list.get(position).name));
        }
    }

    @Override
    public int getItemCount() {
        return list == null ? 0 : list.size();
    }

    @Override
    public int getItemViewType(int position) {
        return list.get(position).getType();
    }

    static class MultiItemViewHolder1 extends RecyclerView.ViewHolder {
        @BindView(R.id.title1)
        TextView title1;
        @BindView(R.id.values1)
        TextView values1;

        public MultiItemViewHolder1(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    static class MultiItemViewHolder2 extends RecyclerView.ViewHolder {
        @BindView(R.id.title2)
        TextView title2;
        @BindView(R.id.values2)
        ImageView values2;

        public MultiItemViewHolder2(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    static class MultiItemViewHolder3 extends RecyclerView.ViewHolder {
        @BindView(R.id.title3)
        TextView title3;
        @BindView(R.id.values3)
        Button values3;

        public MultiItemViewHolder3(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

}
