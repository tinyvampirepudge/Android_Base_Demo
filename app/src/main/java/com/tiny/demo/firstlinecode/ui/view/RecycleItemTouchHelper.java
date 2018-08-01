package com.tiny.demo.firstlinecode.ui.view;

import android.graphics.Canvas;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import com.tiny.demo.firstlinecode.common.utils.LogUtils;
import com.tiny.demo.firstlinecode.ui.adapter.OptionalEditAdapter;

/**
 * Desc:    自定义的ItemTouchHelper.Callback。
 * Created by tiny on 2017/10/26.
 * Version:
 */

public class RecycleItemTouchHelper extends ItemTouchHelper.Callback {
    private ItemTouchHelperCallback itemTouchHelperCallback;

    //限制ImageView长度所能增加的最大值
    private double ICON_MAX_SIZE = 50;
    //ImageView的初始长宽
    private int fixedWidth = 150;

    public RecycleItemTouchHelper(ItemTouchHelperCallback itemTouchHelperCallback) {
        this.itemTouchHelperCallback = itemTouchHelperCallback;
    }

    /**
     * 是否支持滑动，默认支持
     *
     * @return
     */
    @Override
    public boolean isItemViewSwipeEnabled() {
        return super.isItemViewSwipeEnabled();
    }

    /**
     * 是否支持长按拖动，默认支持。
     *
     * @return
     */
    @Override
    public boolean isLongPressDragEnabled() {
        return super.isLongPressDragEnabled();
    }

    /**
     * 设置滑动类型标记
     * 该方法用于返回可以滑动的方向，比如说允许从右到左侧滑，允许上下拖动等。我们一般使用makeMovementFlags(int,int)
     * 或makeFlag(int, int)来构造我们的返回值。
     * 例如：要使RecyclerView的Item可以上下拖动，同时允许从右到左侧滑，但不许允许从左到右的侧滑，我们可以这样写：
     *
     * @param recyclerView
     * @param viewHolder
     * @return
     */
    @Override
    public int getMovementFlags(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder) {
        LogUtils.e("getMovementFlags");
        int dragFlags = ItemTouchHelper.UP | ItemTouchHelper.DOWN;        //允许上下的拖动
//        int swipeFlags = ItemTouchHelper.LEFT;   //只允许从右向左侧滑
        int swipeFlags = ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT;   //只允许从右向左侧滑，从左向右滑动
        return makeMovementFlags(dragFlags, swipeFlags);
    }

    /**
     * 拖拽切换Item的回调。
     * 当用户拖动一个Item进行上下移动从旧的位置到新的位置的时候会调用该方法，在该方法内，我们可以调用Adapter的
     * notifyItemMoved方法来交换两个ViewHolder的位置，最后返回true，表示被拖动的ViewHolder已经移动到了目的位置。
     * 所以，如果要实现拖动交换位置，可以重写该方法（前提是支持上下拖动）：
     *
     * @param recyclerView
     * @param viewHolder
     * @param target
     * @return 如果Item切换了位置，返回true，反之返回false。
     */
    @Override
    public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
        itemTouchHelperCallback.onMove(viewHolder.getAdapterPosition(), target.getAdapterPosition());
        return false;
    }

    /**
     * 当用户左右滑动Item达到删除条件时，会调用该方法，一般手指触摸滑动的距离达到RecyclerView宽度的一半时，再松开手指，
     * 此时该Item会继续向原先滑动方向滑过去并且调用onSwiped方法进行删除，否则会反向滑回原来的位置。
     *
     * @param viewHolder
     * @param direction
     */
    @Override
    public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
        itemTouchHelperCallback.onItemDelete(viewHolder.getAdapterPosition());
    }

    /**
     * 从静止状态变为拖拽或者滑动的时候会回调该方法，参数actionState表示当前的状态。
     *
     * @param viewHolder
     * @param actionState
     */
    @Override
    public void onSelectedChanged(RecyclerView.ViewHolder viewHolder, int actionState) {
        super.onSelectedChanged(viewHolder, actionState);
    }

    /**
     * 当用户操作完毕某个item并且其动画也结束后会调用该方法，一般我们在该方法内恢复ItemView的初始状态，防止由于复用而产生的显示错乱问题。
     *
     * @param recyclerView
     * @param viewHolder
     */
    @Override
    public void clearView(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder) {
        super.clearView(recyclerView, viewHolder);
        //重置改变，防止由于复用而导致的显示问题
        viewHolder.itemView.setScrollX(0);
        ((OptionalEditAdapter.ViewHolder)viewHolder).tv.setText("左滑删除");
        FrameLayout.LayoutParams params = (FrameLayout.LayoutParams) ((OptionalEditAdapter.ViewHolder) viewHolder).iv.getLayoutParams();
        params.width = 150;
        params.height = 150;
        ((OptionalEditAdapter.ViewHolder) viewHolder).iv.setLayoutParams(params);
        ((OptionalEditAdapter.ViewHolder) viewHolder).iv.setVisibility(View.INVISIBLE);
    }

    /**
     * 我们可以在这个方法内实现我们自定义的交互规则或者自定义的动画效果
     *
     * @param c
     * @param recyclerView
     * @param viewHolder
     * @param dX
     * @param dY
     * @param actionState
     * @param isCurrentlyActive
     */
    @Override
    public void onChildDraw(Canvas c, RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, float dX, float dY, int actionState, boolean isCurrentlyActive) {
        //仅对侧滑状态下的效果做出改变
        if (actionState ==ItemTouchHelper.ACTION_STATE_SWIPE){
            //如果dX小于等于删除方块的宽度，那么我们把该方块滑出来
            if (Math.abs(dX) <= getSlideLimitation(viewHolder)){
                viewHolder.itemView.scrollTo(-(int) dX,0);
            }
            //如果dX还未达到能删除的距离，此时慢慢增加“眼睛”的大小，增加的最大值为ICON_MAX_SIZE
            else if (Math.abs(dX) <= recyclerView.getWidth() / 2){
                double distance = (recyclerView.getWidth() / 2 -getSlideLimitation(viewHolder));
                double factor = ICON_MAX_SIZE / distance;
                double diff =  (Math.abs(dX) - getSlideLimitation(viewHolder)) * factor;
                if (diff >= ICON_MAX_SIZE)
                    diff = ICON_MAX_SIZE;
                ((OptionalEditAdapter.ViewHolder)viewHolder).tv.setText("");   //把文字去掉
                ((OptionalEditAdapter.ViewHolder) viewHolder).iv.setVisibility(View.VISIBLE);  //显示眼睛
                FrameLayout.LayoutParams params = (FrameLayout.LayoutParams) ((OptionalEditAdapter.ViewHolder) viewHolder).iv.getLayoutParams();
                params.width = (int) (fixedWidth + diff);
                params.height = (int) (fixedWidth + diff);
                ((OptionalEditAdapter.ViewHolder) viewHolder).iv.setLayoutParams(params);
            }
        }else {
            //拖拽状态下不做改变，需要调用父类的方法
            super.onChildDraw(c,recyclerView,viewHolder,dX,dY,actionState,isCurrentlyActive);
        }
    }
    /**
     * 获取删除方块的宽度
     */
    public int getSlideLimitation(RecyclerView.ViewHolder viewHolder){
        ViewGroup viewGroup = (ViewGroup) viewHolder.itemView;
        return viewGroup.getChildAt(1).getLayoutParams().width;
    }

}
