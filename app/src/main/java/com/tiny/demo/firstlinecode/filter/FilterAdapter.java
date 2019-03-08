package com.tiny.demo.firstlinecode.filter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import com.tiny.demo.firstlinecode.R;

import java.util.List;

/**
 * @Description: Filter使用
 * @Author wangjianzhou@qding.me
 * @Date 2019/3/8 5:44 PM
 * @Version
 */
public class FilterAdapter extends BaseAdapter implements Filterable {
    private Context mContext;
    private List<FilterBean> list;

    public FilterAdapter(Context mContext, List<FilterBean> list) {
        this.mContext = mContext;
        this.list = list;
    }

    @Override
    public int getCount() {
        return list == null ? 0 : list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if (convertView == null) {
            convertView = View.inflate(mContext, R.layout.adapter_filter_test, null);
            viewHolder = new ViewHolder();
            viewHolder.tv = convertView.findViewById(R.id.adapter_filter_tv);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        // set data
        FilterBean bean = list.get(position);
        viewHolder.tv.setText(bean.getValue());

        return convertView;
    }

    @Override
    public Filter getFilter() {
        return ((FilterTestActivity) mContext).new ListFilter();
    }

    public void setData(List<FilterBean> list){
        this.list = list;
        this.notifyDataSetChanged();
    }

    static class ViewHolder {
        TextView tv;
    }
}
