package com.tiny.demo.firstlinecode.brvah.adapter;

import android.util.Log;

import com.chad.library.adapter.base.BaseMultiItemQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.chad.library.adapter.base.entity.MultiItemEntity;
import com.tiny.demo.firstlinecode.R;
import com.tiny.demo.firstlinecode.brvah.entity.Level0Item;
import com.tiny.demo.firstlinecode.brvah.entity.Level1Item;
import com.tiny.demo.firstlinecode.brvah.entity.Person;
import com.tinytongtong.tinyutils.LogUtils;

import java.util.List;

/**
 * Desc:
 * Created by tiny on 2017/12/12.
 * Version:
 */

public class ExpandableItemAdapter extends BaseMultiItemQuickAdapter<MultiItemEntity, BaseViewHolder> {
    public static final String TAG = ExpandableItemAdapter.class.getSimpleName();
    public static final int TYPE_LEVEL_0 = 0;
    public static final int TYPE_LEVEL_1 = 1;
    public static final int TYPE_PERSON = 2;

    /**
     * Same as QuickAdapter#QuickAdapter(Context,int) but with
     * some initialization data.
     *
     * @param data A new list is created out of this one to avoid mutable list
     */
    public ExpandableItemAdapter(List<MultiItemEntity> data) {
        super(data);
        addItemType(TYPE_LEVEL_0, R.layout.item_expandable_lv0);
        addItemType(TYPE_LEVEL_1, R.layout.item_expandable_lv1);
        addItemType(TYPE_PERSON, R.layout.item_expandable_lv2);
    }

    @Override
    protected void convert(BaseViewHolder holder, MultiItemEntity item) {
        switch (holder.getItemViewType()) {
            case TYPE_LEVEL_0:
                switch (holder.getLayoutPosition() % 3) {
                    case 0:
                        holder.setImageResource(R.id.iv_head, R.drawable.head_img0);
                        break;
                    case 1:
                        holder.setImageResource(R.id.iv_head, R.drawable.head_img1);
                        break;
                    case 2:
                        holder.setImageResource(R.id.iv_head, R.drawable.head_img2);
                        break;
                }
                final Level0Item lv0 = (Level0Item) item;
                holder.setText(R.id.title, lv0.title)
                        .setText(R.id.sub_title, lv0.subTitle)
                        .setImageResource(R.id.iv, lv0.isExpanded() ? R.drawable.arrow_b : R.drawable.arrow_r);
                holder.itemView.setOnClickListener(v -> {
                    int pos = holder.getAdapterPosition();
                    LogUtils.INSTANCE.e(TAG, "Level 0 item pos: " + pos);
                    if (lv0.isExpanded()) {
                        collapse(pos);
                    } else {
                        expand(pos);
                    }
                });
                break;
            case TYPE_LEVEL_1:
                final Level1Item lv1 = (Level1Item) item;
                holder.setText(R.id.title, lv1.title)
                        .setText(R.id.sub_title, lv1.subTitle)
                        .setImageResource(R.id.iv, lv1.isExpanded() ? R.drawable.arrow_b : R.drawable.arrow_r);
                holder.itemView.setOnClickListener(v -> {
                    int pos = holder.getAdapterPosition();
                    Log.d(TAG, "Level 1 item pos: " + pos);
                    if (lv1.isExpanded()) {
                        collapse(pos, false);
                    } else {
                        expand(pos, false);
                    }
                });

                holder.itemView.setOnLongClickListener(v -> {
                    int pos = holder.getAdapterPosition();
                    remove(pos);
                    return true;
                });
                break;
            case TYPE_PERSON:
                final Person person = (Person) item;
                holder.setText(R.id.tv, person.name + " parent pos: " + getParentPosition(person));
                holder.itemView.setOnClickListener(view -> {
                    int pos = holder.getAdapterPosition();
                    remove(pos);
                });
                break;
        }
    }
}
