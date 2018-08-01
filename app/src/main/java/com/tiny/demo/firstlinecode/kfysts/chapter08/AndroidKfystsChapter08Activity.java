package com.tiny.demo.firstlinecode.kfysts.chapter08;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.PixelFormat;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.tiny.demo.firstlinecode.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Desc: 第八章 理解Window和WindowManager
 *
 * @author tiny
 * @date 2018/4/28 下午1:08
 */
public class AndroidKfystsChapter08Activity extends AppCompatActivity implements View.OnTouchListener {
    public static final int OVERLAY_PERMISSION_REQ_CODE = 100;

    @BindView(R.id.btn_test_01)
    Button btnTest01;
    @BindView(R.id.btn_test_02)
    Button btnTest02;
    private Button mFloatingButton;
    private WindowManager.LayoutParams mLayoutParam;
    private WindowManager mWindowManager;

    public static void actionStart(Context context) {
        Intent starter = new Intent(context, AndroidKfystsChapter08Activity.class);
        context.startActivity(starter);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_android_kfysts_chapter08);
        ButterKnife.bind(this);
        mWindowManager = (WindowManager) getSystemService(Context.WINDOW_SERVICE);
    }

    @OnClick(R.id.btn_test_01)
    public void onBtnTest01Clicked() {
        if (!hasPermission()) {
            return;
        }

        mFloatingButton = new Button(this);
        mFloatingButton.setText("button");
        mFloatingButton.setOnTouchListener(this);
        mLayoutParam = new WindowManager.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT, 0, 0, PixelFormat.TRANSLUCENT);
        mLayoutParam.flags = WindowManager.LayoutParams.FLAG_NOT_TOUCH_MODAL
                | WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE
                | WindowManager.LayoutParams.FLAG_LAYOUT_IN_SCREEN;
        //deprecated
        mLayoutParam.type = WindowManager.LayoutParams.TYPE_APPLICATION_OVERLAY;
        mLayoutParam.gravity = Gravity.LEFT | Gravity.TOP;
        mLayoutParam.x = 100;
        mLayoutParam.y = 300;
        mWindowManager.addView(mFloatingButton, mLayoutParam);
    }

    public boolean hasPermission() {
        boolean result = false;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (Settings.canDrawOverlays(this)) {
                //有悬浮窗权限开启服务绑定 绑定权限
                result = true;
            } else {
                //没有悬浮窗权限m,去开启悬浮窗权限
                try {
                    Intent intent = new Intent(Settings.ACTION_MANAGE_OVERLAY_PERMISSION,
                            Uri.parse("package:" + getPackageName()));
                    startActivityForResult(intent, OVERLAY_PERMISSION_REQ_CODE);
                    result = false;
                } catch (Exception e) {
                    e.printStackTrace();
                    result = false;
                }
            }
        }
        return result;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == OVERLAY_PERMISSION_REQ_CODE) {
            if (Build.VERSION.SDK_INT >= 23) {
                if (!Settings.canDrawOverlays(this)) {
                    Toast.makeText(this, "权限授予失败，无法开启悬浮窗", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(this, "权限授予成功！", Toast.LENGTH_SHORT).show();
                    //有悬浮窗权限开启服务绑定 绑定权限
                }
            }
        }
    }

    @OnClick(R.id.btn_test_02)
    public void onBtnTest02Clicked() {
        Dialog dialog = new Dialog(this);
        TextView textView = new TextView(this);
        textView.setText("This is Dialog!");
        dialog.getWindow().setType(WindowManager.LayoutParams.TYPE_APPLICATION_OVERLAY);
        dialog.setContentView(textView);
        dialog.show();
    }

    @Override
    protected void onDestroy() {
        try {
            mWindowManager.removeViewImmediate(mFloatingButton);
        } catch (Exception e) {
            e.printStackTrace();
        }
        super.onDestroy();
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        int rawX = (int) event.getRawX();
        int rawY = (int) event.getRawY();
        switch (event.getAction()) {
            case MotionEvent.ACTION_MOVE:
                mLayoutParam.x = rawX;
                mLayoutParam.y = rawY;
                mWindowManager.updateViewLayout(mFloatingButton, mLayoutParam);
                break;
            default:
                break;
        }
        return false;
    }
}
