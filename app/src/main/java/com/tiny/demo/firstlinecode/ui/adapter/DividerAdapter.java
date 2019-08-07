package com.tiny.demo.firstlinecode.ui.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.tiny.demo.firstlinecode.R;
import com.tiny.demo.firstlinecode.common.utils.ToastUtils;
import com.tiny.demo.firstlinecode.ui.bean.Fruit;

import java.util.List;

/**
 * @Description: RecyclerView实现吸底效果
 * @Author wangjianzhou@qding.me
 * @Date 2019-08-06 16:28
 * @Version
 */
public class DividerAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private List<Fruit> fruits;

    public DividerAdapter(List<Fruit> fruits) {
        this.fruits = fruits;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == 0) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_rv_divider, parent, false);
            ViewHolder viewHolder = new ViewHolder(view);
            return viewHolder;
        } else {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_rv_bottom_footer, parent, false);
            BottomViewHolder bottomViewHolder = new BottomViewHolder(view);
            return bottomViewHolder;
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int position) {
        if (viewHolder instanceof ViewHolder) {
            ViewHolder holder = (ViewHolder) viewHolder;
            Fruit fruit = fruits.get(position);
            holder.txt.setText(fruit.getName());
            holder.img.setImageResource(fruit.getImgId());
            holder.itemView.setOnClickListener(v -> ToastUtils.showSingleToast("放肆，竟然敢点你二哈大爷，反了你了"));
        } else {
            BottomViewHolder bottomViewHolder = (BottomViewHolder) viewHolder;

        }
    }

    @Override
    public int getItemCount() {
        return fruits.size();
    }

    @Override
    public int getItemViewType(int position) {
        return fruits.get(position).getType();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        ImageView img;
        TextView txt;

        public ViewHolder(View itemView) {
            super(itemView);
            img = itemView.findViewById(R.id.img_fruit);
            txt = itemView.findViewById(R.id.txt_fruit);
        }
    }

    public static class BottomViewHolder extends RecyclerView.ViewHolder {
        ImageView img;
        TextView txt;

        public BottomViewHolder(View itemView) {
            super(itemView);
            img = itemView.findViewById(R.id.img_fruit);
            txt = itemView.findViewById(R.id.txt_fruit);
        }
    }
}
