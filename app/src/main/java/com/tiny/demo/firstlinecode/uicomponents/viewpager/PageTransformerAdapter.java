package com.tiny.demo.firstlinecode.uicomponents.viewpager;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.tinytongtong.tinyutils.LogUtils;

import java.util.List;

/**
 * Desc:
 * Created by tiny on 2018/1/19.
 * Time: 16:36
 * Version:
 */

public class PageTransformerAdapter extends PagerAdapter {
    private Context context;
    private List<TextView> list;

    public PageTransformerAdapter(Context context, List<TextView> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public int getCount() {
        return Integer.MAX_VALUE;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        LogUtils.INSTANCE.e("instantiateItem");
        int actualPos = position % list.size();
        if (list.get(actualPos).getParent() != null) {
            ((ViewPager) list.get(actualPos).getParent()).removeView(list.get(actualPos));
        }
        container.addView(list.get(actualPos));
        return list.get(actualPos);
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        LogUtils.INSTANCE.e("destroyItem");
        TextView iv = (TextView) object;
        container.removeView(iv);
    }
}
