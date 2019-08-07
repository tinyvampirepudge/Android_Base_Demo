package com.tiny.demo.firstlinecode.ui.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.tiny.demo.firstlinecode.R;
import com.tiny.demo.firstlinecode.common.utils.ToastUtils;
import com.tiny.demo.firstlinecode.ui.bean.GroupItemBean;

import java.util.List;

/**
 * @Description:
 * @Author wangjianzhou@qding.me
 * @Date 2019-08-07 15:54
 * @Version
 */
public class GroupFloatOnDrawOverAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<GroupItemBean> groupItemBeanList;

    public GroupFloatOnDrawOverAdapter(List<GroupItemBean> fruits) {
        this.groupItemBeanList = fruits;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == 0) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_recycler_view, parent, false);
            ViewHolder viewHolder = new ViewHolder(view);
            return viewHolder;
        } else {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_recycler_view_header, parent, false);
            HeaderViewHolder headerViewHolder = new HeaderViewHolder(view);
            return headerViewHolder;
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int position) {
        if (viewHolder instanceof ViewHolder) {
            ViewHolder holder = (ViewHolder) viewHolder;
            GroupItemBean bean = groupItemBeanList.get(position);
            holder.mTextView.setText(bean.getCityName());
            holder.itemView.setOnClickListener(v -> ToastUtils.showSingleToast("放肆，竟然敢点你二哈大爷，反了你了"));
        } else {
            HeaderViewHolder holder = (HeaderViewHolder) viewHolder;
            GroupItemBean bean = groupItemBeanList.get(position);
            holder.mTextView.setText(bean.getProvinceName());
            holder.itemView.setOnClickListener(v -> ToastUtils.showSingleToast("心情不好，别点我"));
        }
    }

    @Override
    public int getItemCount() {
        return groupItemBeanList == null ? 0 : groupItemBeanList.size();
    }

    @Override
    public int getItemViewType(int position) {
        return groupItemBeanList.get(position) == null || TextUtils.isEmpty(groupItemBeanList.get(position).getProvinceName()) ? 0 : 1;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView mTextView;

        public ViewHolder(View itemView) {
            super(itemView);
            mTextView = itemView.findViewById(R.id.tv);
        }
    }

    public static class HeaderViewHolder extends RecyclerView.ViewHolder {

        TextView mTextView;

        public HeaderViewHolder(View itemView) {
            super(itemView);
            mTextView = itemView.findViewById(R.id.tv);
        }
    }
}
