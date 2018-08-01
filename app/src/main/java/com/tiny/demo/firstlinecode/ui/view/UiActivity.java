package com.tiny.demo.firstlinecode.ui.view;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;

import com.tiny.demo.firstlinecode.R;
import com.tiny.demo.firstlinecode.base.BaseActivity;
import com.tiny.demo.firstlinecode.ui.bean.StockEditBean;
import com.tiny.demo.firstlinecode.ui.swipe.SwipeDeleteRecyclerViewActivity;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 常用控件的基本使用
 */
public class UiActivity extends BaseActivity {

    @BindView(R.id.btn_recycler_view_drag_and_delete)
    Button btnRecyclerViewDragAndDelete;
    @BindView(R.id.btn_recycler_view_swipe)
    Button btnRecyclerViewSwipe;
    private Button btnDefault;
    private Button btnH;
    private ProgressBar pH;
    private ProgressBar pD;

    public static void actionStart(Context context) {
        Intent intent = new Intent(context, UiActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected int setContentLayout() {
        return R.layout.activity_ui;
    }

    @Override
    protected void buildContentView() {
        btnDefault = findViewById(R.id.btn_progress_default);
        pD = findViewById(R.id.progress_default);
        btnDefault.setOnClickListener(v -> {
            if (pD.getVisibility() == View.VISIBLE) {
                pD.setVisibility(View.GONE);
            } else {
                pD.setVisibility(View.VISIBLE);
            }
        });
        btnH = findViewById(R.id.btn_progress_horizontal);
        pH = findViewById(R.id.progress_horizontal);
        btnH.setOnClickListener(v -> {
            int progress = pH.getProgress();
            progress = progress + 10;
            pH.setProgress(progress);
        });

        //alertDialog
        findViewById(R.id.btn_alert_dialog).setOnClickListener(v -> {
            AlertDialog.Builder dialog = new AlertDialog.Builder(UiActivity.this);
            dialog.setTitle("This is a alert dialog");
            dialog.setMessage("Something important");
            dialog.setCancelable(true);
            dialog.setPositiveButton("ok", (dialog12, which) -> dialog12.dismiss());
            dialog.setNegativeButton("cancel", (dialog1, which) -> dialog1.dismiss());
            dialog.show();
        });

        //progress dialog
        findViewById(R.id.btn_progress_dialog).setOnClickListener(v -> {
            ProgressDialog progressDialog = new ProgressDialog(UiActivity.this);
            progressDialog.setTitle("This is a progress diialog!");
            progressDialog.setMessage("Loading...");
            progressDialog.setCancelable(true);
            progressDialog.show();
        });

        //百分比布局
        findViewById(R.id.btn_percent_layout).setOnClickListener(v -> startActivity(new Intent(UiActivity.this, PercentLayoutActivity.class)));

        //listview的简单使用
        findViewById(R.id.btn_listview).setOnClickListener(v -> ListViewActivity.actionStart(mContext));

        findViewById(R.id.btn_recycle_view_vertical).setOnClickListener(v -> RecyclerViewVerticalActivity.actionStart(mContext));
        findViewById(R.id.btn_recycle_view_horizontal).setOnClickListener(v -> RecyclerViewHorizontalActivity.actionStart(mContext));
        findViewById(R.id.btn_recycle_view_flow).setOnClickListener(v -> RecyclerViewFlowActivity.actionStart(mContext));
        findViewById(R.id.btn_recycle_view_grid).setOnClickListener(v -> startActivity(new Intent(mContext, RecycleViewGridActivity.class)));
        //ViewFipper
        findViewById(R.id.btn_view_fipper).setOnClickListener(v -> startActivity(new Intent(mContext, ViewFlipperActivity.class)));

        findViewById(R.id.btn_recycler_view_multiple_item).setOnClickListener(v -> startActivity(new Intent(mContext, RecyclerViewMultiItemActivity1.class)));

        findViewById(R.id.btn_recycler_view_multiple_item_bravh).setOnClickListener(v -> startActivity(new Intent(mContext, RecyclerViewMultiItemBravhActivity.class)));
    }

    @Override
    protected void initViewData() {

    }

    @OnClick(R.id.btn_recycler_view_drag_and_delete)
    public void onViewClicked() {
        ArrayList<StockEditBean> list = new ArrayList<>();
        for (int j = 0; j < 100; j++) {
            StockEditBean bean = new StockEditBean();
            bean.setCode("60000" + j);
            bean.setName("猫了个咪" + j);
            bean.setChecked(false);
            list.add(bean);
        }
        Bundle bundle = new Bundle();
        bundle.putParcelableArrayList(DragRecyclerViewActivity.STOCK_LIST, list);
        activitySwitchWithBundle(DragRecyclerViewActivity.class, bundle);
    }

    @OnClick(R.id.btn_recycler_view_swipe)
    public void onRecyclerViewSwipeClicked() {
        activitySwitch(SwipeDeleteRecyclerViewActivity.class);
    }

    @OnClick(R.id.btn_expandable_listview)
    public void onExpandableListViewClicked() {
        activitySwitch(ExpandableListViewActivity.class);
    }
}
