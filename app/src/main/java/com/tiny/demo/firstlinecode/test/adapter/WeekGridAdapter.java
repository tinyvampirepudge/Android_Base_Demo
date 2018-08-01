/*
 *  autor：OrandNot
 *  email：orandnot@qq.com
 *  time: 2016 - 1 - 13
 *
 */
package com.tiny.demo.firstlinecode.test.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.tiny.demo.firstlinecode.R;
import com.tiny.demo.firstlinecode.common.utils.LogUtils;


public class WeekGridAdapter extends BaseAdapter {

    private Context context;
    private OnAdapterChangedListener onAdapterChangedListener;
    public static String[] weekStr = {"日", "一", "二", "三", "四", "五", "六"};
    private int selected = 0;

    public WeekGridAdapter(Context context, OnAdapterChangedListener onAdapterChangedListener) {
        this.context = context;
        this.onAdapterChangedListener = onAdapterChangedListener;
    }

    /**
     * 更新adapter
     *
     * @param selected
     */
    public void updateSelected(int selected) {
        this.selected = selected;
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return weekStr.length;
    }

    @Override
    public Object getItem(int item) {
        return weekStr[item];
    }

    @Override
    public long getItemId(int id) {
        return id;
    }


    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.adapter_week_item, null);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        } else {
            holder = new ViewHolder(convertView);
        }

        holder.text.setText(weekStr[position]);
        if ((selected >> position) % 2 == 1) {
            holder.text.setSelected(true);
        } else {
            holder.text.setSelected(false);
        }
        holder.text.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                LogUtils.e("selected", "" + selected);
                if (v.isSelected()) {
                    LogUtils.e("selected1", "" + selected);
                    selected = selected - (1 << position);
                    //至少选一个
                    if (selected <= 0) {
                        selected = selected + (1 << position);
                        LogUtils.e("selected2", "" + selected);
                        return;
                    }
                    v.setSelected(false);
                } else {
                    LogUtils.e("selected3", "" + selected);
                    selected = selected + (1 << position);
                    v.setSelected(true);
                }
                if (onAdapterChangedListener != null) {
                    onAdapterChangedListener.onChange(selected);
                }
            }
        });
        return convertView;
    }

    class ViewHolder {
        TextView text;

        public ViewHolder(View view) {
            text = (TextView) view.findViewById(R.id.week_item_text);
        }
    }

    public interface OnAdapterChangedListener {
        void onChange(int selected);
    }

}

