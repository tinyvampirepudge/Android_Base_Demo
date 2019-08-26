package com.tiny.demo.firstlinecode.adaptive;

import com.tiny.demo.firstlinecode.base.BaseActivity;
import com.tiny.demo.firstlinecode.R;
import com.tiny.demo.firstlinecode.common.utils.LogUtils;

/**
 * @Description: 布局适配
 *
 * @Author wangjianzhou@qding.me
 * @Date 2019-08-23 18:17
 * @Version TODO
 */
public class AdaptiveLayoutActivity extends BaseActivity {
    /**
     * Whether or not the activity is in two-pane mode, i.e. running on a tablet
     * device.
     */
    private boolean mTwoPane;

    @Override
    protected int setContentLayout() {
        return R.layout.activity_adaptive_layout;
    }

    @Override
    protected void buildContentView() {
        if (findViewById(R.id.btn_adaptive2) != null) {
            // The detail container view will be present only in the
            // large-screen layouts (res/values-w900dp).
            // If this view is present, then the
            // activity should be in two-pane mode.
            mTwoPane = true;
        }

        if (mTwoPane){
            LogUtils.e("这个是大屏");
        }else{
            LogUtils.e("这个是小屏");
        }
    }

    @Override
    protected void initViewData() {

    }
}
