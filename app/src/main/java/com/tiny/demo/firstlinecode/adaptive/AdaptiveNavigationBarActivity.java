package com.tiny.demo.firstlinecode.adaptive;

import android.graphics.Rect;
import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.ViewTreeObserver;

import com.tiny.demo.firstlinecode.R;
import com.tinytongtong.tinyutils.LogUtils;
import com.tinytongtong.tinyutils.ScreenUtils;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @Description: NavigationBar高度适配
 * @Author wangjianzhou@qding.me
 * @Date 2019-08-23 18:26
 * @Version
 */
public class AdaptiveNavigationBarActivity extends AppCompatActivity {
    public static final String TAG = AdaptiveNavigationBarActivity.class.getSimpleName();
    @BindView(R.id.rootView)
    ConstraintLayout rootView;
    private ViewTreeObserver.OnGlobalLayoutListener globalLayoutListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adaptive_navigation_bar);
        ButterKnife.bind(this);

        LogUtils.INSTANCE.e(TAG, "ScreenWidth:" + ScreenUtils.INSTANCE.getScreenW(this));
        LogUtils.INSTANCE.e(TAG, "ScreenHeight:" + ScreenUtils.INSTANCE.getScreenH(this));

        globalLayoutListener = () -> {
            LogUtils.INSTANCE.e(TAG, "ScreenWidth111:" + ScreenUtils.INSTANCE.getScreenW(AdaptiveNavigationBarActivity.this));
            LogUtils.INSTANCE.e(TAG, "ScreenHeight111:" + ScreenUtils.INSTANCE.getScreenH(AdaptiveNavigationBarActivity.this));

            Rect r = new Rect();
            //获取当前界面可视部分，如果NavigationBar可用，就包含其高度。
            getWindow().getDecorView().getWindowVisibleDisplayFrame(r);
            int realHeight = r.bottom;
            LogUtils.INSTANCE.e(TAG, "realHeight:" + realHeight);
        };
        rootView.getViewTreeObserver().addOnGlobalLayoutListener(globalLayoutListener);
    }

    @Override
    protected void onDestroy() {
        rootView.getViewTreeObserver().removeOnGlobalLayoutListener(globalLayoutListener);
        super.onDestroy();
    }
}
