package com.tiny.demo.firstlinecode.brvah.adapter;

import com.chad.library.adapter.base.BaseMultiItemQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.tiny.demo.firstlinecode.R;
import com.tiny.demo.firstlinecode.brvah.entity.MultipleItem;

import java.util.List;

/**
 * Desc:
 * Created by tiny on 2017/12/10.
 * Version:
 */

public class MultipleItemQuickAdapter extends BaseMultiItemQuickAdapter<MultipleItem,BaseViewHolder>{
    /**
     * Same as QuickAdapter#QuickAdapter(Context,int) but with
     * some initialization data.
     *
     * @param data A new list is created out of this one to avoid mutable list
     */
    public MultipleItemQuickAdapter(List<MultipleItem> data) {
        super(data);
        addItemType(MultipleItem.TEXT, R.layout.item_text_view);
        addItemType(MultipleItem.IMG, R.layout.item_image_view);
        addItemType(MultipleItem.IMG_TEXT, R.layout.item_img_text_view);
    }

    @Override
    protected void convert(BaseViewHolder helper, MultipleItem item) {
        switch (helper.getItemViewType()) {
            case MultipleItem.TEXT:
                helper.setText(R.id.tv, item.getContent());
                break;
            case MultipleItem.IMG_TEXT:
                switch (helper.getLayoutPosition() % 2) {
                    case 0:
                        helper.setImageResource(R.id.iv, R.drawable.animation_img1);
                        break;
                    case 1:
                        helper.setImageResource(R.id.iv, R.drawable.animation_img2);
                        break;

                }
                break;
        }
    }
}
