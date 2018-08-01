package com.tiny.demo.firstlinecode.brvah.adapter;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.tiny.demo.firstlinecode.R;
import com.tiny.demo.firstlinecode.brvah.data.DataServer;
import com.tiny.demo.firstlinecode.brvah.entity.Status;

import java.util.List;

/**
 * Desc:
 * Created by tiny on 2017/12/11.
 * Version:
 */

public class HeaderAndFooterAdapter extends BaseQuickAdapter<Status, BaseViewHolder> {

    public HeaderAndFooterAdapter(int dataSize) {
        super(R.layout.item_header_and_footer, DataServer.getSampleData(dataSize));
    }

    @Override
    protected void convert(BaseViewHolder helper, Status item) {
        switch (helper.getLayoutPosition() %
                3) {
            case 0:
                helper.setImageResource(R.id.iv, R.drawable.animation_img1);
                break;
            case 1:
                helper.setImageResource(R.id.iv, R.drawable.animation_img2);
                break;
            case 2:
                helper.setImageResource(R.id.iv, R.drawable.animation_img3);
                break;
        }
    }
}
