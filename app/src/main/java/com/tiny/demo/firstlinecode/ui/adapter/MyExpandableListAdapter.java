package com.tiny.demo.firstlinecode.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.BaseExpandableListAdapter;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import com.tiny.demo.firstlinecode.R;
import com.tiny.demo.firstlinecode.common.utils.ToastUtils;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Desc:
 * Created by tiny on 2017/12/20.
 * Version:
 */

public class MyExpandableListAdapter extends BaseExpandableListAdapter {
    private String[] classes;
    private String[][] students;
    private Context context;
    View.OnClickListener ivGoToChildClickListener;

    public MyExpandableListAdapter() {
    }

    public MyExpandableListAdapter(String[] classes, String[][] students, Context context, View.OnClickListener ivGoToChildClickListener) {
        this.classes = classes;
        this.students = students;
        this.context = context;
        this.ivGoToChildClickListener = ivGoToChildClickListener;
    }

    @Override
    public int getGroupCount() {
        return classes.length;
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return classes[groupPosition].length();
    }

    @Override
    public Object getGroup(int groupPosition) {
        return classes[groupPosition];
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return students[groupPosition][childPosition];
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    /**
     * Indicates whether the child and group IDs are stable across changes to the
     * underlying data.
     *
     * @return whether or not the same ID always refers to the same object
     * @see Adapter#hasStableIds()
     */
    @Override
    public boolean hasStableIds() {
        return true;
    }

    /**
     * 默认返回false,改成true表示组中的子条目可以被点击选中
     *
     * @param groupPosition
     * @param childPosition
     * @return
     */
    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        GroupViewHolder viewHolder;
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.item_elv_group, null);
            viewHolder = new GroupViewHolder(convertView);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (GroupViewHolder) convertView.getTag();
        }

        viewHolder.tvGroupName.setText(classes[groupPosition]);
        //取消默认的groupIndicator后根据方法中传入的isExpand判断组是否展开并动态自定义指示器
        viewHolder.ivGoToChildLV.setImageResource(isExpanded ? R.drawable.ic_arrow_right : R.drawable.ic_arrow_down);

        //setTag() 方法接收的类型是object ，所以可将position和converView先封装在Map中。Bundle中无法封装view,所以不用bundle
        Map<String, Object> tagMap = new HashMap<>();
        tagMap.put("groupPosition", groupPosition);
        tagMap.put("isExpanded", isExpanded);
        viewHolder.ivGoToChildLV.setTag(tagMap);

        //图标的点击事件
        viewHolder.ivGoToChildLV.setOnClickListener(ivGoToChildClickListener);

        return convertView;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        ChildViewHolder viewHolder;
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.item_elv_child, null);
            viewHolder = new ChildViewHolder(convertView);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ChildViewHolder) convertView.getTag();
        }

        viewHolder.tvChildName.setText(students[groupPosition][childPosition]);
        viewHolder.cbElvChild.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (isChecked) {
                ToastUtils.showSingleToast(classes[groupPosition] + "的" +
                        students[groupPosition][childPosition] + "被选中了");
            } else {
                ToastUtils.showSingleToast(classes[groupPosition] + "的" +
                        students[groupPosition][childPosition] + "被取消选中了");
            }
        });
        return convertView;
    }

    static class GroupViewHolder {
        @BindView(R.id.tv_groupName)
        TextView tvGroupName;
        @BindView(R.id.iv_goToChildLV)
        ImageView ivGoToChildLV;

        public GroupViewHolder(View itemView) {
            ButterKnife.bind(this, itemView);
        }
    }

    static class ChildViewHolder {
        @BindView(R.id.tv_elv_childName)
        TextView tvChildName;
        @BindView(R.id.cb_elv_child)
        CheckBox cbElvChild;

        public ChildViewHolder(View itemView) {
            ButterKnife.bind(this, itemView);
        }
    }

}
