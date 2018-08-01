package com.tiny.demo.firstlinecode.brvah.adapter;

import android.support.annotation.Nullable;
import android.text.TextPaint;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.view.View;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.tiny.demo.firstlinecode.R;
import com.tiny.demo.firstlinecode.brvah.entity.Status;
import com.tiny.demo.firstlinecode.brvah.util.SpannableStringUtils;
import com.tiny.demo.firstlinecode.brvah.util.ToastUtils;
import com.tiny.demo.firstlinecode.brvah.util.Utils;

import java.util.List;

/**
 * Desc:
 * Created by tiny on 2017/12/11.
 * Version:
 */

public class PullToRefreshAdapter extends BaseQuickAdapter<Status, BaseViewHolder> {
    public PullToRefreshAdapter(@Nullable List<Status> data) {
        super(R.layout.layout_animation, null);
    }

    @Override
    protected void convert(BaseViewHolder helper, Status item) {
        switch (helper.getLayoutPosition() % 3) {
            case 0:
                helper.setImageResource(R.id.img, R.drawable.animation_img1);
                break;
            case 1:
                helper.setImageResource(R.id.img, R.drawable.animation_img2);
                break;
            case 2:
                helper.setImageResource(R.id.img, R.drawable.animation_img3);
                break;
        }
        helper.setText(R.id.tweetName, "Hoteis in Rio de Janeiro");
        String msg = "\"He was one of Australia's most of distinguished artistes, renowned for his portraits\"";
        ((TextView) helper.getView(R.id.tweetText)).setText(SpannableStringUtils.getBuilder(msg).append("landscapes and nedes").setClickSpan(clickableSpan).create());
        ((TextView) helper.getView(R.id.tweetText)).setMovementMethod(LinkMovementMethod.getInstance());
    }

    ClickableSpan clickableSpan = new ClickableSpan() {
        @Override
        public void onClick(View widget) {
            ToastUtils.showShortToast("事件触发了 landscapes and nedes");
        }

        @Override
        public void updateDrawState(TextPaint ds) {
            ds.setColor(Utils.getContext().getResources().getColor(R.color.clickspan_color));
            ds.setUnderlineText(true);
        }
    };
}
