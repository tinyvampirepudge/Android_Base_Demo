package com.tiny.demo.firstlinecode.test.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.tiny.demo.firstlinecode.R;
import com.tinytongtong.tinyutils.LogUtils;

import java.util.List;

/**
 * Created by tiny on 17/9/5.
 */

public class TestAdapter extends BaseAdapter {
    private List<String> strs;
    private Context mContext;

    public TestAdapter(List<String> strs, Context mContext) {
        this.strs = strs;
        this.mContext = mContext;
    }

    @Override
    public int getCount() {
        return strs == null ? 0 : strs.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if (null == convertView) {
            LogUtils.INSTANCE.e("null == convertView pos --> " + position);
            convertView = LayoutInflater.from(mContext).inflate(R.layout.adapter_test_lv, null);
            viewHolder = new ViewHolder();
            viewHolder.txt = (TextView) convertView.findViewById(R.id.txt);
            convertView.setTag(viewHolder);
        } else {
            LogUtils.INSTANCE.e("else pos --> " + position);
            viewHolder = (ViewHolder) convertView.getTag();
        }
        viewHolder.txt.setText(strs.get(position));
        return convertView;
    }

    public static class ViewHolder {
        TextView txt;
    }

}
