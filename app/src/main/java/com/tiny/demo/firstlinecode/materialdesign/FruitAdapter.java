package com.tiny.demo.firstlinecode.materialdesign;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.tiny.demo.firstlinecode.R;

import java.util.List;

/**
 * Created by 87959 on 2017/5/28.
 */

public class FruitAdapter extends RecyclerView.Adapter<FruitAdapter.ViewHolder> {
    private Context context;
    private List<Fruit> fruits;

    public FruitAdapter(List<Fruit> fruits) {
        this.fruits = fruits;
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        CardView cardView;
        ImageView img;
        TextView name;

        public ViewHolder(View itemView) {
            super(itemView);
            cardView = (CardView) itemView;
            img = (ImageView) itemView.findViewById(R.id.fruit_image);
            name = (TextView) itemView.findViewById(R.id.fruit_name);
        }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (context == null) {
            context = parent.getContext();
        }
        View view = LayoutInflater.from(context).inflate(R.layout.fruit_item, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        final Fruit fruit = fruits.get(position);
        holder.name.setText(fruit.getName());
        Glide.with(context).load(fruit.getImageId()).into(holder.img);
        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle = new Bundle();
                bundle.putString(FruitDetailActivity.FRUIT_NAME, fruit.getName());
                bundle.putInt(FruitDetailActivity.FRUIT_IMG, fruit.getImageId());
                FruitDetailActivity.actionStart(context, bundle);
            }
        });
    }

    @Override
    public int getItemCount() {
        return fruits == null ? 0 : fruits.size();
    }
}
