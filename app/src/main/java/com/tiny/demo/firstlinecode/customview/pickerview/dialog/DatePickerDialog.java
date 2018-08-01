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

import com.tiny.demo.firstlinecode.R;
import com.tiny.demo.firstlinecode.customview.pickerview.LoopListener;
import com.tiny.demo.firstlinecode.customview.pickerview.LoopView;
import com.tiny.demo.firstlinecode.common.utils.DateUtils;
import com.tiny.demo.firstlinecode.common.utils.LogUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.TimeZone;

/**
 * Created by tiny on 16/3/2.
 */
public class DatePickerDialog implements View.OnClickListener {
    private static final String TAG = "DatePickerDialog";
    private static final int DEFAULT_MIN_YEAR = 1900;
    public Button cancelBtn;
    public Button confirmBtn;
    public LoopView yearLoopView;
    public LoopView monthLoopView;
    public LoopView dayLoopView;
    public View pickerContainerV;
    public View contentView;//root view

    private int minYear; // min year
    private int maxYear; // max year
    private int minMonth;
    private int maxMonth;
    private int minDay;
    private int maxDay;
    private int yearPos = 0;//从零开始
    private int monthPos = 0;//从零开始
    private int dayPos = 0;//从零开始
    private Context mContext;
    private String textCancel;
    private String textConfirm;
    private int colorCancel;
    private int colorConfirm;
    private int btnTextsize;//text btnTextsize of cancel and confirm button
    private int viewTextSize;

    private DatePickerMode mode;

    List<String> yearList = new ArrayList();
    List<String> monthList = new ArrayList();
    List<String> dayList = new ArrayList();
    private int yearCount;

    private boolean isScrolling = false;
    private LinearLayout loopviewParent;
    private RelativeLayout.LayoutParams paramsLoopviewParent;

    public enum DatePickerMode {
        YEAR,//以年为基本单位，默认模式
        DAY//以天为基本单位，新增模式。
    }

    public static class Builder {

        //Required
        private Context context;
        private OnDatePickedListener listener;

        public Builder(Context context, OnDatePickedListener listener) {
            this.context = context;
            this.listener = listener;
        }

        //set default value
        private int minYear = DEFAULT_MIN_YEAR;
        private int maxYear = Calendar.getInstance().get(Calendar.YEAR) + 1;
        //起始日期和结束日期
        private int minMonth = 1;
        private int maxMonth = 1;
        private int minDay = 1;
        private int maxDay = 1;
        private String textCancel = "取消";
        private String textConfirm = "确定";
        private String dateChose = getStrDate();
        private int colorCancel = Color.parseColor("#333333");//@color/font_main1
        private int colorConfirm = Color.parseColor("#ff524f");//@color/red_1
        private int btnTextSize = 18;//text btnTextSize of cancel and confirm button
        private int viewTextSize = 16;

        private DatePickerMode mode = DatePickerMode.YEAR;

        public Builder minYear(int minYear) {
            this.minYear = minYear;
            return this;
        }

        public Builder maxYear(int maxYear) {
            this.maxYear = maxYear;
            return this;
        }

        public Builder minMonth(int minMonth) {
            this.minMonth = minMonth;
            return this;
        }

        public Builder maxMonth(int maxMonth) {
            this.maxMonth = maxMonth;
            return this;
        }

        public Builder minDay(int minDay) {
            this.minDay = minDay;
            return this;
        }

        public Builder maxDay(int maxDay) {
            this.maxDay = maxDay;
            return this;
        }

        public Builder textCancel(String textCancel) {
            this.textCancel = textCancel;
            return this;
        }

        public Builder textConfirm(String textConfirm) {
            this.textConfirm = textConfirm;
            return this;
        }

        public Builder dateChose(String dateChose) {
            this.dateChose = dateChose;
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

        public Builder setMode(DatePickerMode mode) {
            this.mode = mode;
            return this;
        }

        public DatePickerDialog build() {
            if (minYear > maxYear) {
                throw new IllegalArgumentException();
            }
            return new DatePickerDialog(this);
        }
    }

    public DatePickerDialog(Builder builder) {
        this.minYear = builder.minYear;
        this.maxYear = builder.maxYear;
        //modify
        this.minMonth = builder.minMonth;
        this.maxMonth = builder.maxMonth;
        this.minDay = builder.minDay;
        this.maxDay = builder.maxDay;
        this.textCancel = builder.textCancel;
        this.textConfirm = builder.textConfirm;
        this.mContext = builder.context;
        this.mListener = builder.listener;
        this.colorCancel = builder.colorCancel;
        this.colorConfirm = builder.colorConfirm;
        this.btnTextsize = builder.btnTextSize;
        this.viewTextSize = builder.viewTextSize;
        this.mode = builder.mode;

        setSelectedDate(builder.dateChose);//默认选择当前日期。
        initView();
    }

    private OnDatePickedListener mListener;

    private void initView() {
        contentView = LayoutInflater.from(mContext).inflate(
                R.layout.layout_date_picker, null);
        cancelBtn = (Button) contentView.findViewById(R.id.btn_cancel);
        confirmBtn = (Button) contentView.findViewById(R.id.btn_confirm);
        yearLoopView = (LoopView) contentView.findViewById(R.id.picker_year);
        monthLoopView = (LoopView) contentView.findViewById(R.id.picker_month);
        dayLoopView = (LoopView) contentView.findViewById(R.id.picker_day);
        pickerContainerV = contentView.findViewById(R.id.container_picker);
        loopviewParent = (LinearLayout) contentView.findViewById(R.id.loopview_parent);

        cancelBtn.setText(textCancel);
        confirmBtn.setText(textConfirm);
        cancelBtn.setTextColor(colorCancel);
        confirmBtn.setTextColor(colorConfirm);
        cancelBtn.setTextSize(btnTextsize);
        confirmBtn.setTextSize(btnTextsize);

        //do not loop,default can loop
        yearLoopView.setNotLoop();
        monthLoopView.setNotLoop();
        dayLoopView.setNotLoop();

        //set loopview text btnTextsize
        yearLoopView.setTextSize(viewTextSize);
        monthLoopView.setTextSize(viewTextSize);
        dayLoopView.setTextSize(viewTextSize);

        //set checked listener
        yearLoopView.setListener(new LoopListener() {
            @Override
            public void onItemSelect(int item) {
                yearPos = item;
                monthLoopView.setTotalScrollY(0);
                dayLoopView.setTotalScrollY(0);
                initMonthPickerViews();
            }
        });
        monthLoopView.setListener(new LoopListener() {
            @Override
            public void onItemSelect(int item) {
                monthPos = item;
                dayLoopView.setTotalScrollY(0);
                initDayPickerView();
            }
        });
        dayLoopView.setListener(new LoopListener() {
            @Override
            public void onItemSelect(int item) {
                dayPos = item;
            }
        });
        getValidYears();// check years valid.
        initYearPickerViews(); // init year, month and day loop view

        cancelBtn.setOnClickListener(this);
        confirmBtn.setOnClickListener(this);
        contentView.setOnClickListener(this);
        //add by tiny  set loopView height dynamicly
        int loopViewHeight = yearLoopView.getMaxTextHeight();
        paramsLoopviewParent = (RelativeLayout.LayoutParams) loopviewParent.getLayoutParams();
        paramsLoopviewParent.height = loopViewHeight * 8;
        loopviewParent.setLayoutParams(paramsLoopviewParent);

        LinearLayout.LayoutParams paramsYear = (LinearLayout.LayoutParams) yearLoopView.getLayoutParams();
        paramsYear.height = loopViewHeight * 8;
        yearLoopView.setLayoutParams(paramsYear);
        yearLoopView.setPadding(0, dip2px(mContext, 7), 0, dip2px(mContext, 5));
        LinearLayout.LayoutParams paramsMonth = (LinearLayout.LayoutParams) monthLoopView.getLayoutParams();
        paramsMonth.height = loopViewHeight * 8;
        monthLoopView.setLayoutParams(paramsMonth);
        monthLoopView.setPadding(0, dip2px(mContext, 7), 0, dip2px(mContext, 5));
        LinearLayout.LayoutParams paramsDay = (LinearLayout.LayoutParams) dayLoopView.getLayoutParams();
        paramsDay.height = loopViewHeight * 8;
        dayLoopView.setLayoutParams(paramsDay);
        dayLoopView.setPadding(0, dip2px(mContext, 7), 0, dip2px(mContext, 5));
    }

    private void getValidYears() {
        if (maxYear < minYear) {
            LogUtils.e(TAG, "DatePickerDialog Exception: maxYear < minYear");
            return;
        }
        yearCount = maxYear - minYear;
    }

    /**
     * Init year and month loop view,
     * Let the day loop view be handled separately
     * modified by tiny
     * add setMinMonth and setMonthDay.
     */
    private void initYearPickerViews() {
        yearList = new ArrayList<>();
        for (int i = 0; i < yearCount; i++) {
            yearList.add(format2LenStr(minYear + i) + "年");
        }

        yearLoopView.setArrayList((ArrayList) yearList);
        yearLoopView.setInitPosition(yearPos);

        initMonthPickerViews();
    }

    /**
     * Init month item
     */
    private void initMonthPickerViews() {
        monthList = new ArrayList<>();
        if (mode == DatePickerMode.DAY) {//start date and end date
            if (yearCount == 1) {//不跨年，从minMonth到maxMonth.
                int start = minMonth;
                if (start <= 0) {
                    start = 1;
                }
                if (start > 12) {
                    start = 12;
                }
                int end = maxMonth;
                if (end <= 0) {
                    end = 1;
                }
                if (end > 12) {
                    end = 12;
                }
                if (start > end) {
                    return;
                }
                for (int i = start; i <= end; i++) {
                    monthList.add(format2LenStr(i) + "月");
                }
            } else if (0 == yearPos) {// minMonth of minYear.
                int start = minMonth;
                if (start <= 0) {
                    start = 1;
                }
                if (start > 12) {
                    start = 12;
                }
                for (int i = start; i <= 12; i++) {
                    monthList.add(format2LenStr(i) + "月");
                }
            } else if (yearCount - 1 == yearPos) {// maxMonth of MaxYear.
                int end = maxMonth;
                if (end <= 0) {
                    end = 1;
                }
                if (end > 12) {
                    end = 12;
                }
                for (int i = 1; i <= end; i++) {
                    monthList.add(format2LenStr(i) + "月");
                }
            } else {// normal --> 12.
                for (int j = 0; j < 12; j++) {
                    monthList.add(format2LenStr(j + 1) + "月");
                }
            }
        } else if (mode == DatePickerMode.YEAR) {//default
            for (int j = 0; j < 12; j++) {
                monthList.add(format2LenStr(j + 1) + "月");
            }
        }

        monthLoopView.setArrayList((ArrayList) monthList);
        if (monthPos > monthList.size() - 1) {
            monthPos = monthList.size() - 1;
        }
        monthLoopView.setInitPosition(monthPos);
        initDayPickerView();
    }

    /**
     * Init day item
     */
    private void initDayPickerView() {
        int dayMaxInMonth;
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeZone(TimeZone.getTimeZone("GMT+8:00"));
        dayList = new ArrayList<String>();
        if (DatePickerMode.DAY == mode) {
            if ((yearCount == 1) && (maxMonth == minMonth)) {//同一个月。
                dayMaxInMonth = DateUtils.getDaysOfMonth(minYear, minMonth);//获取本月最大天数
                int start = minDay;
                if (start <= 0) {
                    start = 1;
                }
                if (start > dayMaxInMonth) {
                    start = dayMaxInMonth;
                }
                int end = maxDay;
                if (end <= 0) {
                    end = 1;
                }
                if (end > dayMaxInMonth) {
                    end = dayMaxInMonth;
                }
                if (start > end) {
                    return;
                }
                for (int i = start; i <= end; i++) {
                    dayList.add(format2LenStr(i) + "日");
                }
            } else if (yearCount == 1) {//不跨年, 超过一个月
                if (((maxMonth - minMonth) == monthPos)) {//不跨年，最后一个月
                    dayMaxInMonth = DateUtils.getDaysOfMonth(minYear, maxMonth);//获取本月最大天数
                    int end = maxDay;
                    if (end <= 0) {
                        end = 1;
                    }
                    if (end > dayMaxInMonth) {
                        end = dayMaxInMonth;
                    }
                    for (int i = 1; i <= end; i++) {
                        dayList.add(format2LenStr(i) + "日");
                    }
                } else if (monthPos == 0) {//不跨年，第一个月
                    dayMaxInMonth = DateUtils.getDaysOfMonth(minYear + yearPos, minMonth + monthPos);//获取本月最大天数
                    int start = minDay;
                    if (start <= 0) {
                        start = 1;
                    }
                    if (start > dayMaxInMonth) {
                        start = dayMaxInMonth;
                    }
                    for (int i = start; i <= dayMaxInMonth; i++) {
                        dayList.add(format2LenStr(i) + "日");
                    }
                } else {//不跨年，中间的月份。
                    dayMaxInMonth = DateUtils.getDaysOfMonth(minYear, minMonth + monthPos);//获取本月最大天数
                    for (int i = 1; i <= dayMaxInMonth; i++) {
                        dayList.add(format2LenStr(i) + "日");
                    }
                }

            } else if (0 == yearPos) {//跨年，第一个月和中间的月份。
                if (0 == monthPos) {
                    //第一个月，从minDay到该月最大天数
                    dayMaxInMonth = DateUtils.getDaysOfMonth(minYear + yearPos, monthPos + 1);//获取本月最大天数
                    int start = minDay;
                    if (start <= 0) {
                        start = 1;
                    }
                    if (start > dayMaxInMonth) {
                        start = dayMaxInMonth;
                    }
                    for (int i = start; i <= dayMaxInMonth; i++) {
                        dayList.add(format2LenStr(i) + "日");
                    }
                } else {//中间的月份
                    dayMaxInMonth = DateUtils.getDaysOfMonth(minYear, minMonth + monthPos);//获取本月最大天数
                    for (int i = 1; i <= dayMaxInMonth; i++) {
                        dayList.add(format2LenStr(i) + "日");
                    }
                }
            } else if (yearCount - 1 == yearPos && maxMonth - 1 == monthPos) {//跨年，最后一个月，从1到maxDay.
                dayMaxInMonth = DateUtils.getDaysOfMonth(minYear + yearPos, monthPos + 1);//获取本月最大天数
                int end = maxDay;
                if (end <= 0) {
                    end = 1;
                }
                if (end > dayMaxInMonth) {
                    end = dayMaxInMonth;
                }
                for (int i = 1; i <= end; i++) {
                    dayList.add(format2LenStr(i) + "日");
                }
            } else {
                dayMaxInMonth = DateUtils.getDaysOfMonth(minYear + yearPos, monthPos + 1);//获取本月最大天数
                for (int i = 0; i < dayMaxInMonth; i++) {
                    dayList.add(format2LenStr(i + 1) + "日");
                }
            }
        } else if (DatePickerMode.YEAR == mode) {//default
            dayMaxInMonth = DateUtils.getDaysOfMonth(minYear + yearPos, monthPos + 1);//获取本月最大天数
            for (int i = 0; i < dayMaxInMonth; i++) {
                dayList.add(format2LenStr(i + 1) + "日");
            }
        }

        dayLoopView.setArrayList((ArrayList) dayList);
        if (dayPos > dayList.size() - 1) {
            dayPos = dayList.size() - 1;
        }
        dayLoopView.setInitPosition(dayPos);
    }

    /**
     * set selected date position value when initView.
     *
     * @param dateStr
     */
    public void setSelectedDate(String dateStr) {
        if (!TextUtils.isEmpty(dateStr)) {

            long milliseconds = getLongFromyyyyMMdd(dateStr);
            Calendar calendar = Calendar.getInstance(Locale.CHINA);

            if (milliseconds != -1) {
                //init calendar data.
                calendar.setTimeInMillis(milliseconds);
                yearPos = calendar.get(Calendar.YEAR) - minYear;
                if (mode == DatePickerMode.DAY) {
                    //月份设置
                    //不跨年、跨年的第一个月或最后一个月
                    if (yearCount == 1 || 0 == yearPos || yearCount - 1 == yearPos) {
                        monthPos = calendar.get(Calendar.MONTH) - minMonth + 1;
                    } else {// normal --> 12.
                        monthPos = calendar.get(Calendar.MONTH);
                    }

                    //日期设置：
                    if ((yearCount == 1) && (maxMonth == minMonth)) {//同一个月。
                        dayPos = calendar.get(Calendar.DAY_OF_MONTH) - 1 - minDay;
                    } else if (yearPos == 0 && monthPos == 0) {
                        dayPos = calendar.get(Calendar.DAY_OF_MONTH) - minDay;
                    } else {
                        dayPos = calendar.get(Calendar.DAY_OF_MONTH) - 1;
                    }
                } else if (mode == DatePickerMode.YEAR) {
                    monthPos = calendar.get(Calendar.MONTH);
                    dayPos = calendar.get(Calendar.DAY_OF_MONTH) - 1;
                }
            } else {
                return;
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

    public static String getStrDate() {
        SimpleDateFormat dd = new SimpleDateFormat("yyyy-MM-dd", Locale.CHINA);
        return dd.format(new Date());
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

    public interface OnDatePickedListener {

        /**
         * Listener when date has been checked
         *
         * @param year
         * @param month
         * @param day
         * @param dateDesc yyyy.MM.dd
         */
        void onDatePickCompleted(int year, int month, int day,
                                 String dateDesc);
    }

    @Override
    public void onClick(View v) {

        if (v == cancelBtn) {
            dismissDialog();
        } else if (v == confirmBtn) {
            if (null != mListener) {
//                int dayMaxInMonth = DateUtils.getDaysOfMonth(minYear + yearPos, monthPos + 1);//获取本月最大天数
//                if (dayPos > dayMaxInMonth - 1) {
//                    dayPos = dayMaxInMonth - 1;
//                }

                //get current date
                int year;
                int month;
                int day;
                year = minYear + yearPos;
                if (mode == DatePickerMode.DAY) {
                    if (0 == yearPos) {
                        month = monthPos + minMonth;
                        if (0 == monthPos) {
                            day = dayPos + minDay;
                        } else {
                            day = dayPos + 1;
                        }
                    } else {
                        month = monthPos + 1;
                        day = dayPos + 1;
                    }
                } else {
                    month = monthPos + 1;
                    day = dayPos + 1;
                }

                StringBuffer sb = new StringBuffer();
                sb.append(String.valueOf(year));
                sb.append(".");
                sb.append(format2LenStr(month));
                sb.append(".");
                sb.append(format2LenStr(day));
                mListener.onDatePickCompleted(year, month, day, sb.toString());
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
//            int height = (int) (getScreenH(mContext) * 1 / 2f);
//            int height = dip2px(mContext, px2dip(mContext, paramsLoopviewParent.height) + 45 + 40 + 30);
//            int height = dip2px(mContext, px2dip(mContext, (int) (paramsLoopviewParent.height / 8) * 3));
//            window.setLayout(width, height);
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
