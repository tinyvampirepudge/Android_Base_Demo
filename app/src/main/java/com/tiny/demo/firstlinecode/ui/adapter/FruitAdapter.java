package com.tiny.demo.firstlinecode.ui.adapter;

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
 * Created by 87959 on 2017/3/12.
 */

public class FruitAdapter extends RecyclerView.Adapter<FruitAdapter.ViewHolder> {
    private List<Fruit> fruits;

    public FruitAdapter(List<Fruit> fruits) {
        this.fruits = fruits;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_fruit,parent,false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Fruit fruit = fruits.get(position);
        holder.txt.setText(fruit.getName());
        holder.img.setImageResource(fruit.getImgId());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ToastUtils.showSingleToast("放肆，竟然敢点你二哈大爷");
            }
        });
    }

    @Override
    public int getItemCount() {
        return fruits.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{
        ImageView img;
        TextView txt;

        public ViewHolder(View itemView) {
            super(itemView);
            img = (ImageView) itemView.findViewById(R.id.img_fruit);
            txt = (TextView) itemView.findViewById(R.id.txt_fruit);
        }
    }
}
