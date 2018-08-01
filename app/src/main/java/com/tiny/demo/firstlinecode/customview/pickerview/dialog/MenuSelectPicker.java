package com.tiny.demo.firstlinecode.customview.pickerview.dialog;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.graphics.Color;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.tiny.demo.firstlinecode.R;
import com.tiny.demo.firstlinecode.customview.pickerview.LoopListener;
import com.tiny.demo.firstlinecode.customview.pickerview.LoopView;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

/**
 * Created by zymydl on 7/27/16.
 */
public class MenuSelectPicker implements View.OnClickListener {
    private static final int DEFAULT_MIN_YEAR = 1900;
    public Button cancelBtn;
    public Button confirmBtn;
    public LoopView selectLoopView;
    private int selectPos = 0;

    public View pickerContainerV;
    public View contentView;//root view

    private Context mContext;
    private String textCancel;
    private String textConfirm;
    private int colorCancel;
    private int colorConfirm;
    private int btnTextsize;//text btnTextsize of cancel and confirm button
    private int viewTextSize;

    List<String> selectList = new ArrayList();
    private String dialogTitle = "";
    private boolean isScrolling = false;
    private LinearLayout loopviewParent;
    private RelativeLayout.LayoutParams paramsLoopviewParent;

    public static class Builder {

        //Required
        private Context context;
        private OnDataPickedListener listener;

        public Builder(Context context, OnDataPickedListener listener) {
            this.context = context;
            this.listener = listener;
        }


        private String textCancel = "取消";
        private String textConfirm = "确定";
        private String dialogTitle = "";
        private String currSelect = "";
        private ArrayList menuData = null;
        private int colorCancel = Color.parseColor("#333333");//@color/font_main1
        private int colorConfirm = Color.parseColor("#ff524f");//@color/red_1
        private int btnTextSize = 18;//text btnTextSize of cancel and confirm button
        private int viewTextSize = 16;

        public Builder textCancel(String textCancel) {
            this.textCancel = textCancel;
            return this;
        }

        public Builder textConfirm(String textConfirm) {
            this.textConfirm = textConfirm;
            return this;
        }

        public Builder setData(ArrayList dta) {
            this.menuData = dta;
            return this;
        }

        /**
         * 设置标题
         *
         * @param title
         * @return
         */
        public Builder setDialogTitle(String title) {
            this.dialogTitle = title;
            return this;
        }

        public Builder selectItem(String selector) {
            this.currSelect = selector;
            return this;
        }

        public Builder colorCancel(int colorCancel) {
            this.colorCancel = colorCancel;
            return this;
        }

        public Builder colorConfirm(int colorConfirm) {
            this.colorConfirm = colorConfirm;
            return this;
        }

        /**
         * set btn text btnTextSize
         *
         * @param textSize dp
         */
        public Builder btnTextSize(int textSize) {
            this.btnTextSize = textSize;
            return this;
        }

        public Builder viewTextSize(int textSize) {
            this.viewTextSize = textSize;
            return this;
        }

        public MenuSelectPicker build() {
//            if (minYear > maxYear) {
//                throw new IllegalArgumentException();
//            }
            return new MenuSelectPicker(this);
        }
    }

    public MenuSelectPicker(Builder builder) {
        this.textCancel = builder.textCancel;
        this.textConfirm = builder.textConfirm;
        this.mContext = builder.context;
        this.mListener = builder.listener;
        this.colorCancel = builder.colorCancel;
        this.colorConfirm = builder.colorConfirm;
        this.btnTextsize = builder.btnTextSize;
        this.viewTextSize = builder.viewTextSize;
        this.selectList = builder.menuData;
        this.dialogTitle = builder.dialogTitle;
        setSelectedDate(builder.currSelect);
        initView();
    }

    private OnDataPickedListener mListener;

    private void initView() {

        contentView = LayoutInflater.from(mContext).inflate(
                R.layout.layout_select_picker, null);
        cancelBtn = (Button) contentView.findViewById(R.id.btn_cancel);
        confirmBtn = (Button) contentView.findViewById(R.id.btn_confirm);
        selectLoopView = (LoopView) contentView.findViewById(R.id.picker_year);
        pickerContainerV = contentView.findViewById(R.id.container_picker);
        loopviewParent = (LinearLayout) contentView.findViewById(R.id.loopview_parent);
        ((TextView) contentView.findViewById(R.id.txt_title)).setText(this.dialogTitle);
        cancelBtn.setText(textCancel);
        confirmBtn.setText(textConfirm);
        cancelBtn.setTextColor(colorCancel);
        confirmBtn.setTextColor(colorConfirm);
        cancelBtn.setTextSize(btnTextsize);
        confirmBtn.setTextSize(btnTextsize);

        //do not loop,default can loop
        selectLoopView.setNotLoop();

        //set loopview text btnTextsize
        selectLoopView.setTextSize(viewTextSize);

        //set checked listener
        selectLoopView.setListener(new LoopListener() {
            @Override
            public void onItemSelect(int item) {
                selectPos = item;
                initDayPickerView();
            }
        });

        initPickerViews(); // init year and month loop view
        initDayPickerView(); //init day loop view

        cancelBtn.setOnClickListener(this);
        confirmBtn.setOnClickListener(this);
        contentView.setOnClickListener(this);
        //add by tiny  set loopView height dynamicly
        int loopViewHeight = selectLoopView.getMaxTextHeight();
        paramsLoopviewParent = (RelativeLayout.LayoutParams) loopviewParent.getLayoutParams();
        paramsLoopviewParent.height = loopViewHeight * 8;
        loopviewParent.setLayoutParams(paramsLoopviewParent);

        LinearLayout.LayoutParams paramsYear = (LinearLayout.LayoutParams) selectLoopView.getLayoutParams();
        paramsYear.height = loopViewHeight * 8;
        selectLoopView.setLayoutParams(paramsYear);
        selectLoopView.setPadding(0, dip2px(mContext, 7), 0, dip2px(mContext, 5));
    }

    /**
     * Init year and month loop view,
     * Let the day loop view be handled separately
     * modified by tiny
     * add setMinMonth and setMonthDay.
     */
    private void initPickerViews() {
        selectLoopView.setArrayList((ArrayList) selectList);
        selectLoopView.setInitPosition(selectPos);
    }

    /**
     * Init day item
     */
    private void initDayPickerView() {
    }

    /**
     * set selected date position value when initView.
     *
     * @param itemVal
     */
    public void setSelectedDate(String itemVal) {
        if (!TextUtils.isEmpty(itemVal)) {
            for (int i = 0; i < selectList.size(); i++) {
                if (itemVal.equals(selectList.get(i))) {
                    selectPos = i;
                    return;
                }
            }
        }
    }

    /**
     * get long from yyyy-MM-dd
     *
     * @param date
     * @return
     */
    public static long getLongFromyyyyMMdd(String date) {
        SimpleDateFormat mFormat = new SimpleDateFormat("yyyy.MM.dd", Locale.getDefault());
        Date parse = null;
        try {
            parse = mFormat.parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        if (parse != null) {
            return parse.getTime();
        } else {
            return -1;
        }
    }

    public String getDefSelect() {
        return selectList.get(0);
    }

    /**
     * Transform int to String with prefix "0" if less than 10
     *
     * @param num
     * @return
     */
    public static String format2LenStr(int num) {

        return (num < 10) ? "0" + num : String.valueOf(num);
    }

    public static int spToPx(Context context, int spValue) {
        final float fontScale = context.getResources().getDisplayMetrics().scaledDensity;
        return (int) (spValue * fontScale + 0.5f);
    }

    public interface OnDataPickedListener {

        /**
         * Listener when date has been checked
         */
        void onDataPickCompleted(String current);
    }

    @Override
    public void onClick(View v) {

        if (v == cancelBtn) {
            dismissDialog();
        } else if (v == confirmBtn) {
            if (null != mListener) {
                //get current date
                String val = selectList.get(selectPos);
                mListener.onDataPickCompleted(val);
            }
            dismissDialog();
        }
    }

    private AlertDialog dialog;

    /**
     * Show date picker popWindow
     *
     * @param activity
     */
    public void showDialog(Activity activity) {

        if (null != activity) {
            //弹出对话框
            if (dialog == null) {
                dialog = new AlertDialog.Builder(mContext).create();
                dialog.setCanceledOnTouchOutside(true);
            }
            dialog.show();
            Window window = dialog.getWindow();
            window.setGravity(Gravity.CENTER);
            window.setContentView(contentView);
//            window.setWindowAnimations(R.style.animation_dialog);
            //下方的代码无用，不需要设置宽和高，只需在dialog的布局中设置父窗体为包裹内容即可。
            int width = (int) (getScreenW(mContext) * 5 / 6f);
            window.setLayout(width, ViewGroup.LayoutParams.WRAP_CONTENT);
        }
    }

    /**
     * Dismiss date picker popWindow
     */
    public void dismissDialog() {
        if (dialog.isShowing()) {
            dialog.dismiss();
        }
    }

    /**
     * 获取屏幕宽度
     */
    public int getScreenW(Context context) {
        DisplayMetrics dm = context.getResources().getDisplayMetrics();
        int w = dm.widthPixels;
        return w;
    }

    /**
     * 获取屏幕高度
     */
    public static int getScreenH(Context context) {
        DisplayMetrics dm = context.getResources().getDisplayMetrics();
        int h = dm.heightPixels;
        return h;
    }

    /**
     * 根据手机的分辨率从 dp 的单位 转成为 px(像素)
     */
    public static int dip2px(Context context, float dpValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }

    /**
     * 根据手机的分辨率从 px(像素) 的单位 转成为 dp
     */
    public static int px2dip(Context context, float pxValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (pxValue / scale + 0.5f);
    }
}
