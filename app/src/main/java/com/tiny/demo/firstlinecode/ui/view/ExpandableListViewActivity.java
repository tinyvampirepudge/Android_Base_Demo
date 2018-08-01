package com.tiny.demo.firstlinecode.ui.view;

import android.view.View;
import android.widget.ExpandableListView;

import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.tiny.demo.firstlinecode.R;
import com.tiny.demo.firstlinecode.base.BaseActivity;
import com.tiny.demo.firstlinecode.common.utils.ToastUtils;
import com.tiny.demo.firstlinecode.ui.adapter.MyExpandableListAdapter;

import java.util.Map;

import butterknife.BindView;

public class ExpandableListViewActivity extends BaseActivity {

    @BindView(R.id.list_train)
    ExpandableListView mExpandableListview;
    @BindView(R.id.refreshLayout)
    SmartRefreshLayout mRefreshLayout;

    @Override
    protected int setContentLayout() {
        return R.layout.activity_expandable_list_view;
    }

    @Override
    protected void buildContentView() {
        final String[] classes = new String[]{"一班", "二班", "三班", "四班", "五班"};
        final String[][] students = new String[][]{{"张三1", "李四1", "王五1", "赵六1", "钱七1", "高八1"}, {"张三1", "李四1", "王五1",
                "赵六1", "钱七1", "高八1"}, {"张三1", "李四1", "王五1", "赵六1", "钱七1", "高八1"}, {"张三1", "李四1", "王五1", "赵六1", "钱七1",
                "高八1"}, {"张三1", "李四1", "王五1", "赵六1", "钱七1", "高八1"}};

        //ExpandableListView + adapter
        MyExpandableListAdapter mAdapter = new MyExpandableListAdapter(classes, students, mContext, ivClickListener);
        mExpandableListview.setAdapter(mAdapter);
        //默认展开第一个分组
        mExpandableListview.expandGroup(0);

        //展开某个分组时，并关闭其他分组。注意这里设置的是 ExpandListener
        mExpandableListview.setOnGroupExpandListener(groupPosition -> {
            //遍历 group 的数组（或集合），判断当前被点击的位置与哪个组索引一致，不一致就合并起来。
            for (int j = 0; j < classes.length; j++) {
                if (j != groupPosition) {
                    mExpandableListview.collapseGroup(j);
                }
            }
        });
        //点击某个分组时，跳转到指定Activity
        mExpandableListview.setOnGroupClickListener((parent, v, groupPosition, id) -> {
            ToastUtils.showSingleToast("组被点击了，跳转到具体的Activity");
            return true;    //拦截点击事件，不再处理展开或者收起
//                return false;
        });
        ////某个分组中的子View被点击时的事件
        mExpandableListview.setOnChildClickListener((parent, v, groupPosition, childPosition, id) -> {
            ToastUtils.showSingleToast("组中的条目被点击：" + classes[groupPosition] + "的" +
                    students[groupPosition][childPosition] + "放学后到校长办公室");
            return false;
        });

        //refreshLayout
        mRefreshLayout.setOnRefreshListener(refreshlayout -> mRefreshLayout.getLayout().postDelayed(() -> {
            ToastUtils.showSingleToast("下拉刷新成功");
            mRefreshLayout.finishRefresh();
            mRefreshLayout.resetNoMoreData();
        }, 2000));

        mRefreshLayout.setOnLoadmoreListener(refreshlayout -> mRefreshLayout.getLayout().postDelayed(() -> {
            ToastUtils.showSingleToast("加载更多成功！");
            mRefreshLayout.finishRefresh();
        }, 2000));

        mRefreshLayout.autoRefresh();
    }

    private View.OnClickListener ivClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            //获取被点击图标所在的group的索引
            Map<String, Object> map = (Map<String, Object>) v.getTag();
            int groupPosition = (int) map.get("groupPosition");
//            boolean isExpand = (boolean) map.get("isExpanded");
            boolean isExpand = mExpandableListview.isGroupExpanded(groupPosition);
            if (isExpand) {
                mExpandableListview.collapseGroup(groupPosition);
            } else {
                mExpandableListview.expandGroup(groupPosition);
            }
        }
    };

    @Override
    protected void initViewData() {

    }
}
