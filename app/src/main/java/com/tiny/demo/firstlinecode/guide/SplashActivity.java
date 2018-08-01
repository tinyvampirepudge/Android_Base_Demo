package com.tiny.demo.firstlinecode.guide;

import android.os.Handler;
import android.support.annotation.NonNull;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.RelativeLayout;

import com.tiny.demo.firstlinecode.base.BaseActivity;
import com.tiny.demo.firstlinecode.MainActivity;
import com.tiny.demo.firstlinecode.R;
import com.tiny.demo.firstlinecode.common.utils.PermissionUtils;
import com.tiny.demo.firstlinecode.common.utils.PreferencesUtils;

import butterknife.BindView;

/**
 * Desc:    SplashActivity, for espresso test
 * Created by tiny on 2017/10/10.
 * Version:
 */

public class SplashActivity extends BaseActivity {
    public static final String SHOW_GUIDE_PAGE = "show_guide_page";
    private Animation animation;
    private Handler handler = new Handler();
    private boolean isPermission;//true代表需要请求权限，false代表不需要请求权限。
    @BindView(R.id.layout_splash)
    RelativeLayout layoutSplash;

    @Override
    protected int setContentLayout() {
        return R.layout.activity_splash;
    }

    @Override
    protected void buildContentView() {
        animation = new AlphaAnimation(0.3f, 1.0f);
        animation.setDuration(2000);
        animation.setFillAfter(true);
        animation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                if (!PermissionUtils.canMakeSmores()) {
                    toNextActivity();
                }
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
        layoutSplash.startAnimation(animation);
        if (PermissionUtils.canMakeSmores()) {
            isPermission = PermissionUtils.requestMultiPermissions(this, mPermissionGrant);
        }
    }

    private void toNextActivity() {
        //直接跳转mainActivity界面。
        handler.postDelayed(() -> {
            boolean showPageGuide = PreferencesUtils.getBoolean(SHOW_GUIDE_PAGE, true);
            if (showPageGuide) {
                activitySwitch(GuidePageActivity.class);
            } else {
                activitySwitch(MainActivity.class);
            }
            animation.setAnimationListener(null);
            SplashActivity.this.finish();
        }, 3000);
    }

    @Override
    protected void initViewData() {

    }

    /**
     * Callback received when a permissions request has been completed.
     */
    @Override
    public void onRequestPermissionsResult(final int requestCode, @NonNull String[] permissions,
                                           @NonNull int[] grantResults) {
        if (PermissionUtils.canMakeSmores()) {
            PermissionUtils.requestPermissionsResult(this, requestCode, permissions, grantResults, mPermissionGrant);
        }
    }

    private PermissionUtils.PermissionGrant mPermissionGrant = requestCode -> {
        switch (requestCode) {
            case PermissionUtils.CODE_MULTI_PERMISSION:
                toNextActivity();
                break;
            default:
                break;
        }
    };
}
