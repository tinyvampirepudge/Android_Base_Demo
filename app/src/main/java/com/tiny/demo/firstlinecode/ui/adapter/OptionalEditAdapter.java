package com.tiny.demo.firstlinecode.ui.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.tiny.demo.firstlinecode.R;
import com.tiny.demo.firstlinecode.common.utils.ToastUtils;
import com.tiny.demo.firstlinecode.common.utils.ViewUtils;
import com.tiny.demo.firstlinecode.ui.bean.StockEditBean;
import com.tiny.demo.firstlinecode.ui.view.ItemTouchHelperCallback;

import java.util.Collections;
import java.util.List;

/**
 * Desc:    自选编辑的adapter
 * Created by tiny on 2017/10/25.
 * Version:
 */

public class OptionalEditAdapter extends RecyclerView.Adapter<OptionalEditAdapter.ViewHolder> implements ItemTouchHelperCallback {
    private List<StockEditBean> lists;
    private Context mContext;
    private Runnable runnable;

    public OptionalEditAdapter(List<StockEditBean> lists, Context mContext, Runnable runnable) {
        this.lists = lists;
        this.mContext = mContext;
        this.runnable = runnable;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.adapter_zixuan_edit, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        final StockEditBean sb = lists.get(position);
        holder.imgChecked.setImageResource(sb.isChecked() ? R.drawable.ic_optional_select_box2 : R.drawable.ic_optional_select_box);
        holder.txtName.setText(sb.getName());
        holder.txtCode.setText(sb.getCode());
        holder.imgChecked.setOnClickListener(v -> {
            int pos = 0;
            for (int j = 0; j < lists.size(); j++) {
                if (sb.getCode().equals(lists.get(j).getCode())) {
                    pos = j;
                    break;
                }
            }
            lists.get(pos).setChecked(!lists.get(pos).isChecked());
            notifyItemChanged(pos);
            //通知全选按钮更改状态。
            if (runnable != null) {
                runnable.run();
            }
        });
        holder.imgTop.setOnClickListener(v -> {
            //根据code获取实际的position。
            int pos = 0;
            for (int j = 0; j < lists.size(); j++) {
                if (sb.getCode().equals(lists.get(j).getCode())) {
                    pos = j;
                    break;
                }
            }
            //交换list
            lists.remove(pos);
            lists.add(0, sb);
            //更新list
            notifyItemMoved(pos, 0);
            ToastUtils.showSingleToast(mContext.getString(R.string.string_top_success));
        });
        ViewUtils.setTouchDelegate(mContext, holder.imgChecked);
        ViewUtils.setTouchDelegate(mContext, holder.imgTop);
        ViewUtils.setTouchDelegate(mContext, holder.imgDrag);
    }

    @Override
    public int getItemCount() {
        return lists == null ? 0 : lists.size();
    }

    @Override
    public void onItemDelete(int position) {
        //移除数据
        lists.remove(position);
        notifyItemRemoved(position);
    }

    @Override
    public void onMove(int fromPosition, int toPosition) {
        Collections.swap(lists, fromPosition, toPosition);
        notifyItemMoved(fromPosition, toPosition);
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        LinearLayout layoutAll;
        LinearLayout layoutChecked;
        ImageView imgChecked;
        TextView txtName;
        TextView txtCode;
        ImageView imgTop;
        ImageView imgDrag;
        public TextView tv;
        public ImageView iv;

        public ViewHolder(View itemView) {
            super(itemView);
            layoutAll = (LinearLayout) itemView.findViewById(R.id.layout_item_all);
            layoutChecked = (LinearLayout) itemView.findViewById(R.id.layout_checked);
            imgChecked = (ImageView) itemView.findViewById(R.id.img_checked);
            txtName = (TextView) itemView.findViewById(R.id.txt_name);
            txtCode = (TextView) itemView.findViewById(R.id.txt_code);
            imgTop = (ImageView) itemView.findViewById(R.id.img_top);
            imgDrag = (ImageView) itemView.findViewById(R.id.img_drag);
            tv = (TextView) itemView.findViewById(R.id.tv_text);
            iv = (ImageView) itemView.findViewById(R.id.iv_img);
        }
    }

}
