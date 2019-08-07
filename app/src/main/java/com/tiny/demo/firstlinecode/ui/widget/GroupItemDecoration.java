package com.tiny.demo.firstlinecode.ui.widget;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.support.annotation.ColorInt;
import android.support.annotation.NonNull;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextPaint;
import android.text.TextUtils;
import android.view.View;

import com.tiny.demo.firstlinecode.ui.listener.GroupListener;
import com.tiny.demo.firstlinecode.ui.listener.OnGroupClickListener;

/**
 * @Description: 分组悬停，悬停布局直接在Item上层绘制，不是复用Item的View
 * 参考： https://www.jianshu.com/p/b335b620af39   StickyDecoration
 * 预留空间 {@link GroupItemDecoration#getItemOffsets(Rect, View, RecyclerView, RecyclerView.State)}
 * 在Item上层绘制 {@link GroupItemDecoration#onDrawOver(Canvas, RecyclerView, RecyclerView.State)}
 * @Author wangjianzhou@qding.me
 * @Date 2019-08-07 14:11
 * @Version
 */
public class GroupItemDecoration extends RecyclerView.ItemDecoration {
    public static final String TAG = GroupItemDecoration.class.getSimpleName();

    /**
     * group背景色，默认透明
     */
    @ColorInt
    int mGroupBackground = Color.parseColor("#48BDFF");
    /**
     * 悬浮栏高度
     */
    int mGroupHeight = 120;

    protected OnGroupClickListener mOnGroupClickListener;

    private GroupListener mGroupListener;

    private TextPaint mTextPaint;
    private Paint mGroupPaint;
    @ColorInt
    private int mGroupTextColor = Color.WHITE;//字体颜色，默认黑色
    private int mSideMargin = 32;   //边距 左边距
    private int mTextSize = 50;     //字体大小

    public GroupItemDecoration(GroupListener mGroupListener) {
        this.mGroupListener = mGroupListener;
        //设置悬浮栏的画笔---mGroupPaint
        mGroupPaint = new Paint();
        mGroupPaint.setColor(mGroupBackground);
        //设置悬浮栏中文本的画笔
        mTextPaint = new TextPaint();
        mTextPaint.setAntiAlias(true);
        mTextPaint.setTextSize(mTextSize);
        mTextPaint.setColor(mGroupTextColor);
        mTextPaint.setTextAlign(Paint.Align.LEFT);
    }

    @Override
    public void getItemOffsets(@NonNull Rect outRect, @NonNull View view, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
        super.getItemOffsets(outRect, view, parent, state);
        int position = parent.getChildAdapterPosition(view);
        //其他的默认为线性布局
        //只有是同一组的第一个才显示悬浮栏
        if (isFirstInGroup(position)) {
            //为悬浮view预留空间
            outRect.top = mGroupHeight;
        } else {
            // 不需要预留空间
        }
    }

    @Override
    public void onDrawOver(@NonNull Canvas c, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
        super.onDrawOver(c, parent, state);
        if (parent == null || parent.getAdapter() == null || parent.getAdapter().getItemCount() <= 0) {
            return;
        }

        // 获取adapter里面item个数
        final int itemCount = parent.getAdapter().getItemCount();
        // 获取当前可见的item数量
        final int childCount = parent.getChildCount();

        final int left = parent.getPaddingLeft();
        final int right = parent.getWidth() - parent.getPaddingRight();

        for (int i = 0; i < childCount; i++) {
            View childView = parent.getChildAt(i);
            // 根据View获取实际的position
            int position = parent.getChildAdapterPosition(childView);
            // 每个分组的第一个Item或者可见的第一个Item
            if (isFirstInGroup(position) || i == 0) {
                //绘制悬浮

                //决定当前顶部第一个悬浮Group的bottom
                int bottom = Math.max(mGroupHeight, (childView.getTop() + parent.getPaddingTop()));
                if (position + 1 < itemCount) {
                    //下一组的第一个View接近头部
                    int viewBottom = childView.getBottom();
                    if (isLastLineInGroup(parent, position) && viewBottom < bottom) {
                        bottom = viewBottom;
                    }
                }
                drawDecoration(c, position, left, right, bottom);
            }
        }
    }

    /**
     * 绘制悬浮框
     *
     * @param c
     * @param position
     * @param left
     * @param right
     * @param bottom
     */
    private void drawDecoration(Canvas c, int position, int left, int right, int bottom) {
        String curGroupName;       //当前item对应的Group
        int firstPositionInGroup = getFirstInGroupWithCash(position);
        curGroupName = getGroupName(firstPositionInGroup);
        //根据top绘制group背景
        c.drawRect(left, bottom - mGroupHeight, right, bottom, mGroupPaint);
        if (curGroupName == null) {
            return;
        }
        Paint.FontMetrics fm = mTextPaint.getFontMetrics();
        //文字竖直居中显示
        float baseLine = bottom - (mGroupHeight - (fm.bottom - fm.top)) / 2 - fm.bottom;
        //获取文字宽度
        mSideMargin = Math.abs(mSideMargin);
        c.drawText(curGroupName, left + mSideMargin, baseLine, mTextPaint);
    }

    /**
     * 设置点击事件
     *
     * @param listener
     */
    protected void setOnGroupClickListener(OnGroupClickListener listener) {
        this.mOnGroupClickListener = listener;
    }

    /**
     * 判断是不是组中的第一个位置
     * 根据前一个组名，判断当前是否为新的组
     * 当前为groupId为null时，则与上一个为同一组
     */
    protected boolean isFirstInGroup(int position) {
        int realPosition = position;
        if (realPosition < 0) {
            //小于header数量，不是第一个
            return false;
        } else if (realPosition == 0) {
            //等于header数量，为第一个
            return true;
        }
        String preGroupId;
        if (realPosition <= 0) {
            preGroupId = null;
        } else {
            preGroupId = getGroupName(realPosition - 1);
        }
        String curGroupId = getGroupName(realPosition);
        if (curGroupId == null) {
            return false;
        }
        return !TextUtils.equals(preGroupId, curGroupId);
    }

    /**
     * 获取groupName
     *
     * @param position
     * @return
     */
    String getGroupName(int position) {
        if (mGroupListener != null) {
            return mGroupListener.getGroupName(position);
        } else {
            return null;
        }
    }

    /**
     * 判断自己是否为group的最后一行
     *
     * @param recyclerView recyclerView
     * @param position     position
     * @return
     */
    protected boolean isLastLineInGroup(RecyclerView recyclerView, int position) {
        int realPosition = position;
        if (realPosition < 0) {
            return true;
        } else {
            String curGroupName = getGroupName(realPosition);
            String nextGroupName;
            RecyclerView.LayoutManager manager = recyclerView.getLayoutManager();
            //默认往下查找的数量
            int findCount = 1;
            if (manager instanceof GridLayoutManager) {
                int spanCount = ((GridLayoutManager) manager).getSpanCount();
                int firstPositionInGroup = getFirstInGroupWithCash(realPosition);
                findCount = spanCount - (realPosition - firstPositionInGroup) % spanCount;
            }
            try {
                nextGroupName = getGroupName(realPosition + findCount);
            } catch (Exception e) {
                nextGroupName = curGroupName;
            }
            if (nextGroupName == null) {
                return false;
            }
            return !TextUtils.equals(curGroupName, nextGroupName);
        }
    }

    /**
     * 得到当前分组第一个item的position
     *
     * @param position position
     */
    protected int getFirstInGroupWithCash(int position) {
        return getFirstInGroup(position);
    }

    /**
     * 得到当前分组第一个item的position
     *
     * @param position position
     */
    private int getFirstInGroup(int position) {
        if (position <= 0) {
            return 0;
        } else {
            if (isFirstInGroup(position)) {
                return position;
            } else {
                return getFirstInGroup(position - 1);
            }
        }
    }
}
