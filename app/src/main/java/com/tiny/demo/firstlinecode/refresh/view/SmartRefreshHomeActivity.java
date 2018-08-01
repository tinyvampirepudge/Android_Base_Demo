package com.tiny.demo.firstlinecode.refresh.view;

import android.support.annotation.IdRes;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.MenuItem;

import com.tiny.demo.firstlinecode.R;
import com.tiny.demo.firstlinecode.base.BaseActivity;
import com.tiny.demo.firstlinecode.refresh.util.StatusBarUtil;

import butterknife.BindView;

public class SmartRefreshHomeActivity extends BaseActivity implements BottomNavigationView.OnNavigationItemSelectedListener {

    @BindView(R.id.navigation)
    BottomNavigationView navigation;

    @Override
    protected int setContentLayout() {
        return R.layout.activity_smart_refresh_home;
    }

    @Override
    protected void buildContentView() {
        //设置选中的监听器
        navigation.setOnNavigationItemSelectedListener(this);
        //默认选中style
        navigation.setSelectedItemId(R.id.navigation_style);

        //状态栏透明和间距处理
        StatusBarUtil.immersive(this, 0xff000000, 0.1f);
    }

    @Override
    protected void initViewData() {

    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        getSupportFragmentManager()
                .beginTransaction()
                .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                .replace(R.id.content, TabFragment.from(item.getItemId()).fragment())
                .commit();
        return true;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        TabFragment.onDestroy();
    }

    private enum TabFragment {
        practice(R.id.navigation_practice, RefreshPracticeFragment.class),
        style(R.id.navigation_style, RefreshStyleFragment.class),
        using(R.id.navigation_using, RefreshUsingFragment.class);

        private Fragment fragment;
        private int menuId;
        private Class<? extends Fragment> clazz;

        TabFragment(@IdRes int menuId, Class<? extends Fragment> clazz) {
            this.menuId = menuId;
            this.clazz = clazz;
        }

        @NonNull
        public Fragment fragment() {
            if (fragment == null) {
                try {
                    fragment = clazz.newInstance();
                } catch (Exception e) {
                    e.printStackTrace();
                    fragment = new Fragment();
                }
            }
            return fragment;
        }

        public static TabFragment from(int menuId) {
            for (TabFragment fragment : values()) {
                if (fragment.menuId == menuId) {
                    return fragment;
                }
            }
            return style;
        }

        public static void onDestroy() {
            for (TabFragment fragment : values()) {
                fragment.fragment = null;
            }
        }
    }
}
