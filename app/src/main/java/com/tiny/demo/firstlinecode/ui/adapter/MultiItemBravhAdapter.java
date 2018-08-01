package com.tiny.demo.firstlinecode.ui.adapter;

import com.chad.library.adapter.base.BaseMultiItemQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.tiny.demo.firstlinecode.R;
import com.tiny.demo.firstlinecode.ui.bean.MultiItemBravh;

import java.util.List;

/**
 * Desc:
 * Created by tiny on 2017/12/14.
 * Version:
 */

public class MultiItemBravhAdapter extends BaseMultiItemQuickAdapter<MultiItemBravh, BaseViewHolder> {
    /**
     * Same as QuickAdapter#QuickAdapter(Context,int) but with
     * some initialization data.
     *
     * @param data A new list is created out of this one to avoid mutable list
     */
    public MultiItemBravhAdapter(List<MultiItemBravh> data) {
        super(data);
        addItemType(MultiItemBravh.TYPE1, R.layout.adapter_rv_multi_item_bravh1);
        addItemType(MultiItemBravh.TYPE2, R.layout.adapter_rv_multi_item_bravh2);
        addItemType(MultiItemBravh.TYPE3, R.layout.adapter_rv_multi_item_bravh3);
    }

    @Override
    protected void convert(BaseViewHolder helper, MultiItemBravh item) {
        switch (helper.getItemViewType()) {
            case MultiItemBravh.TYPE1:
                helper.setText(R.id.txt1, item.getName());
                break;
            case MultiItemBravh.TYPE2:
                helper.setText(R.id.txt2, item.getName());
                break;
            case MultiItemBravh.TYPE3:
                helper.setText(R.id.txt3, item.getName());
                break;
            default:
                break;
        }
    }
}
