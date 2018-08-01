package com.tiny.demo.firstlinecode.ui.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.tiny.demo.firstlinecode.R;
import com.tiny.demo.firstlinecode.ui.bean.Fruit;

import java.util.List;

/**
 * Created by 87959 on 2017/3/13.
 */

public class FruitiAdapterHorizontal extends RecyclerView.Adapter<FruitiAdapterHorizontal.ViewHolder> {
    private List<Fruit> fruits;

    public FruitiAdapterHorizontal(List<Fruit> fruits) {
        this.fruits = fruits;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(
                R.layout.adapter_recycler_view_horizontal,parent,false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Fruit fruit = fruits.get(position);
        holder.img.setImageResource(fruit.getImgId());
        holder.txt.setText(fruit.getName());
    }

    @Override
    public int getItemCount() {
        return fruits.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder{
        ImageView img;
        TextView txt;

        public ViewHolder(View itemView) {
            super(itemView);
            img = (ImageView) itemView.findViewById(R.id.img_recycler_h);
            txt = (TextView) itemView.findViewById(R.id.txt_recycler_h);
        }
    }
}
