package com.tiny.demo.firstlinecode.brvah.adapter;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.tiny.demo.firstlinecode.R;
import com.tiny.demo.firstlinecode.brvah.entity.HomeItem;

import java.util.List;

/**
 * Desc:
 * Created by tiny on 2017/12/10.
 * Version:
 */

public class HomeAdapter extends BaseQuickAdapter<HomeItem,BaseViewHolder>{
    public HomeAdapter(int layoutResId, @Nullable List<HomeItem> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, HomeItem item) {
        helper.setText(R.id.text, item.getTitle());
        helper.setImageResource(R.id.icon, item.getImageResource());
    }
}
