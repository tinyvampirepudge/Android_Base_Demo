package com.tiny.demo.firstlinecode.ui.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.TypedValue;
import android.view.View;

import com.tiny.demo.firstlinecode.ui.bean.CategoryBean;

import java.util.List;

/**
 * 打造title标题悬浮效果
 */

public class TitleItemDecoration extends RecyclerView.ItemDecoration {

    private Context context;
    private List<CategoryBean> lists;
    private Paint paint;//画悬浮栏的画笔
    private Rect rectBounds;

    private int titleHeight;//悬浮栏的高度
    private int mTextSize;//文字大小

    public TitleItemDecoration(Context context, List<CategoryBean> lists) {
        this.context = context;
        this.lists = lists;
        //设置悬浮栏高度以及文字大小
        titleHeight = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 35, context.getResources().getDisplayMetrics());
        mTextSize = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, 20, context.getResources().getDisplayMetrics());

        rectBounds = new Rect();
        //初始化画笔
        paint = new Paint();
        paint.setAntiAlias(true);//抗锯齿
        paint.setDither(true);//防抖动
        paint.setTextSize(mTextSize);
    }

    /**
     * 设置recyclerview的item的上下左右的padding间距值（这里利用设置的padding值来为分类的头部留出空间）
     *
     * @param outRect
     * @param view
     * @param parent
     * @param state
     */
    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        super.getItemOffsets(outRect, view, parent, state);
        //通过查看源码可知道获取position的方法
        int position = ((RecyclerView.LayoutParams) view.getLayoutParams()).getViewLayoutPosition();
        if (position > -1) {
            if (position == 0) {
                //默认第一个位置，肯定是要设置悬浮栏的，这里只需要上边top留出空间即可
                outRect.set(0, titleHeight, 0, 0);//里面参数表示：左上右下的内边距padding距离
            } else {
                //继续分情况判断，当滑动到某一个item时(position位置)得到tag标签，与上一个item对应的标签不一致( position-1 位置)，说明这是下一分组了
                if (lists.get(position).getTag() != null && !lists.get(position).getTag().equals(lists.get(position - 1).getTag())) {
                    //说明这是下一组了，需要留出空间好去绘制悬浮栏用
                    outRect.set(0, titleHeight, 0, 0);
                } else {
                    //标签相同说明是同一组的数据，比如都是 A 组下面的数据，那么就不需要再留出空间绘制悬浮栏了，共用同一个 A 组即可
                    outRect.set(0, 0, 0, 0);
                }
            }
        }
    }

    /**
     * onDraw：先调用绘制在底层，超出getItemOffsets中设置的距离时，会被item遮挡住，然后调用View（recyclerview）的onDraw，所以item绘制在中间层，最后是onDrawOver最后被调用，绘制在最上层，会遮挡住item的内容
     * 如果只是需要最顶层悬浮一个遮盖效果，那么只需要用到onDrawOver即可，因为本demo实现的效果是，在预留空间显示一个带title的矩形条，距离在getItemOffsets设置的距离之内
     * 所以，不会被item遮挡，给人的感觉是在同一层。当滑动到最顶部的时候，这个带title的矩形条需要悬浮在那里，那么，我们就需要重写onDrawOver方法了
     *
     * @param c
     * @param parent
     * @param state
     */
    @Override
    public void onDraw(Canvas c, RecyclerView parent, RecyclerView.State state) {
        super.onDraw(c, parent, state);
        //先画出带有背景颜色的矩形条悬浮栏，从哪个位置开始绘制到哪个位置结束，则需要先确定位置，再画文字（即：title）
        int left = parent.getPaddingLeft();
        int right = parent.getWidth() - parent.getPaddingRight();
        //父view（RecyclerView）有padding值，子view有margin值
        int childCount = parent.getChildCount();//得到的数据其实是一屏可见的item数量并非总item数，再复用
        for (int i = 0; i < childCount; i++) {
            View child = parent.getChildAt(i);
            //子view（即：item）有可能设置有margin值，所以需要parms来设置margin值
            RecyclerView.LayoutParams params = (RecyclerView.LayoutParams) child.getLayoutParams();
            //以及 获取 position 位置
            int position = params.getViewLayoutPosition();
            if (position > -1) {
                if (position == 0) {//肯定是要绘制一个悬浮栏 以及 悬浮栏内的文字
                    //画矩形悬浮条以及文字
                    drawRectAndText(c, left, right, child, params, position);
                } else {
                    if (lists.get(position).getTag() != null && !lists.get(position).getTag().equals(lists.get(position - 1).getTag())) {
                        //和上一个Tag不一样，说明是另一个新的分组
                        //画矩形悬浮条以及文字
                        drawRectAndText(c, left, right, child, params, position);
                    } else {
                        //说明是一组的，什么也不画，共用同一个Tag
                    }
                }
            }

        }
    }

    private void drawRectAndText(Canvas c, int left, int right, View child, RecyclerView.LayoutParams params, int position) {
        //1、画矩形悬浮栏
        //item可以有margin值不设置就默认为0，其中child.getTop()表示item距离父recyclerview的距离，params.topMargin表示item的外边距，悬浮栏在item上方，那么悬浮栏的bottom就是child.getTop() - params.topMargin
        paint.setColor(Color.parseColor("#FFDFDFDF"));
        c.drawRect(left, child.getTop() - params.topMargin - titleHeight, right, child.getTop() - params.topMargin, paint);
        //2、画文字
        paint.setColor(Color.parseColor("#88888888"));
        paint.getTextBounds(lists.get(position).getTag(), 0, lists.get(position).getTag().length(), rectBounds);//将文字放到矩形中，得到Rect的宽高
        c.drawText(lists.get(position).getTag(), child.getPaddingLeft(), child.getTop() - params.topMargin - (titleHeight / 2 - rectBounds.height() / 2), paint);
    }

    /**
     * 绘制悬停的头部 （分类的title布局）
     *
     * @param c
     * @param parent
     * @param state
     */
    @Override
    public void onDrawOver(Canvas c, RecyclerView parent, RecyclerView.State state) {
        super.onDrawOver(c, parent, state);
        //其实就是获取到每一个可见的位置的item时，执行画顶层悬浮栏
        int firstPosition = ((LinearLayoutManager) parent.getLayoutManager()).findFirstVisibleItemPosition();
        //    View child = parent.getChildAt(firstPosition);
        View child = parent.findViewHolderForLayoutPosition(firstPosition).itemView;
        //绘制悬浮栏，其实这里和上面onDraw()绘制方法差不多，只不过，这里面的绘制是在最上层，会悬浮
        paint.setColor(Color.parseColor("#FFDFDFDF"));
        c.drawRect(parent.getPaddingLeft(), parent.getPaddingTop(), parent.getRight() - parent.getPaddingRight(), parent.getPaddingTop() + titleHeight, paint);
        //绘制文字
        paint.setColor(Color.parseColor("#88888888"));
        paint.getTextBounds(lists.get(firstPosition).getTag(), 0, lists.get(firstPosition).getTag().length(), rectBounds);
        c.drawText(lists.get(firstPosition).getTag(), child.getPaddingLeft(), parent.getPaddingTop() + titleHeight - (titleHeight / 2 - rectBounds.height() / 2), paint);
    }


}
