package com.tiny.demo.firstlinecode.ui.view;

import android.widget.ViewFlipper;

import com.tiny.demo.firstlinecode.base.BaseActivity;
import com.tiny.demo.firstlinecode.R;
import com.tiny.demo.firstlinecode.ui.widget.MyContentView;

/**
 * http://www.10tiao.com/html/169/201701/2650821911/1.html
 */
public class ViewFlipperActivity extends BaseActivity {

    private MyContentView m1;
    private MyContentView m2;
    private MyContentView m3;
    private ViewFlipper vf;

    @Override
    protected int setContentLayout() {
        return R.layout.activity_view_flipper;
    }

    @Override
    protected void buildContentView() {
        vf = (ViewFlipper) findViewById(R.id.view_flipper);
        m1 = new MyContentView(mContext);
        m1.getTxt1().setText("aaaaaaaaaaaaaaaaaaaaa");
        m1.getTxt2().setText("bbbbbbbbbbbbbbbbbbbbb");
        m2 = new MyContentView(mContext);
        m2.getTxt1().setText("ccccccccccccccccccccc");
        m2.getTxt2().setText("ddddddddddddddddddddd");
        m3 = new MyContentView(mContext);
        m3.getTxt1().setText("eeeeeeeeeeeeeeeeeeeee");
        m3.getTxt2().setText("fffffffffffffffffffff");
        vf.addView(m1);
        vf.addView(m2);
        vf.addView(m3);
        findViewById(R.id.btn_vp_start).setOnClickListener(v -> {
            if (vf.isFlipping()) {
                vf.stopFlipping();
                vf.removeAllViews();
            }
            vf.addView(m1);
            vf.addView(m2);
            vf.addView(m3);
            vf.startFlipping();
        });

        findViewById(R.id.btn_vp_stop).setOnClickListener(v -> {
            if (vf.isFlipping()) {
                vf.stopFlipping();
                vf.removeAllViews();
            }
        });
    }

    @Override
    protected void initViewData() {

    }
}
