package com.tiny.demo.firstlinecode.common.view;

//import android.annotation.TargetApi;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.os.Build;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.tiny.demo.firstlinecode.R;
import com.tiny.demo.firstlinecode.common.interfaces.OnTitleBarClick;
import com.tiny.demo.firstlinecode.common.utils.ScreenUtils;

/**
 * 类描述：自定义标题栏
 *
 * @author wuhenzhizao
 * @creation 2014-4-25
 */
public class TitleBarLayout extends RelativeLayout {
    private RelativeLayout mMainLayout;
    private View mBottomLineView;
    // 标题栏左侧按钮
    private ImageButton mLeftImageBtn;
    // 标题栏左侧文字
    private TextView mLeftTextView;
    private RelativeLayout mCenterLayout;
    // 标题栏中部图标
    private ImageView mCenterImageView;
    // 标题栏中部文字
    private TextView mCenterTextView;
    // 中间小标题
    private TextView mCenterSubTextView;
    // 标题栏右侧按钮
    private ImageButton mRightImageBtn;
    // 标题栏右侧文字
    private TextView mRightTextView;

    // 右侧自定义布局
    private View mRightLayout;

    private int backgroundRes;
    private int textColor;
    private int leftImageRes;
    private String leftTextRes;
    private int leftTextDrawableRes;
    private int leftTextDrawablePaddingRes;
    private int centerImageRes;
    private String centerTextRes;
    private String centerSubTextRes;
    private int rightImageRes;
    private String rightTextRes;
    private int rightTextColorRes;
    private int rightTextDrawableRes;
    private int rightTextDrawablePaddingRes;
    private int rightLayoutRes;

    private boolean showBottomLine = false;
    private int mBottomLineDrawable;

    private boolean showBottomShadow = true;

    private OnTitleBarClick titleBarListener;

    private int leftType = TYPE_NONE;
    private int centerType = TYPE_NONE;
    private int rightType = TYPE_NONE;
    private static final int TYPE_NONE = 0;
    private static final int TYPE_IMAGE = 1;
    private static final int TYPE_TEXT = 2;
    private static final int TYPE_TEXT_MARQUEE = 3;
    private static final int TYPE_LAYOUT = 3;
    private static final int TYPE_TEXT_RED_POT = 4;

    private TranslateAnimation animation1;
    private TranslateAnimation animation2;

    public TitleBarLayout(Context context) {
        super(context);
        initView(context, null);
    }

    public TitleBarLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView(context, attrs);
    }

    public void initView(Context context, AttributeSet attrs) {
        initResource(context, attrs);
        initView(context);
        initLeftView(context, attrs);
        initCenterView(context, attrs);
        initRightView(context, attrs);
        initAnimation();
    }

    private void initResource(Context context, AttributeSet attrs) {
        int defaultDrawablePadding = 0;

        // 读取配置
        TypedArray array = context.obtainStyledAttributes(attrs, R.styleable.Flier_titlebar);

        backgroundRes = array.getColor(R.styleable.Flier_titlebar_back_ground, Color.WHITE);
        textColor = array.getColor(R.styleable.Flier_titlebar_text_color, Color.WHITE);

        leftType = array.getInt(R.styleable.Flier_titlebar_left_type, TYPE_NONE);
        if (leftType == TYPE_IMAGE) {
            leftImageRes = array.getResourceId(R.styleable.Flier_titlebar_left_image, 0);
        } else if (leftType == TYPE_TEXT) {
            leftTextRes = array.getString(R.styleable.Flier_titlebar_left_text);
            leftTextDrawableRes = array.getResourceId(R.styleable.Flier_titlebar_left_text_drawable, 0);
            leftTextDrawablePaddingRes = array.getDimensionPixelSize(R.styleable.Flier_titlebar_left_text_drawable_padding, defaultDrawablePadding);
        }

        centerType = array.getInt(R.styleable.Flier_titlebar_center_type, TYPE_NONE);
        if (centerType == TYPE_IMAGE) {
            centerImageRes = array.getResourceId(R.styleable.Flier_titlebar_center_image, 0);
        } else if (centerType == TYPE_TEXT) {
            centerTextRes = array.getString(R.styleable.Flier_titlebar_center_text);
            centerSubTextRes = array.getString(R.styleable.Flier_titlebar_center_sub_text);
        }

        rightType = array.getInt(R.styleable.Flier_titlebar_right_type, TYPE_NONE);
        if (rightType == TYPE_IMAGE) {
            rightImageRes = array.getResourceId(R.styleable.Flier_titlebar_right_image, 0);
        } else if (rightType == TYPE_TEXT) {
            rightTextRes = array.getString(R.styleable.Flier_titlebar_right_text);
            rightTextColorRes = array.getColor(R.styleable.Flier_titlebar_right_text_color, textColor);
            rightTextDrawableRes = array.getResourceId(R.styleable.Flier_titlebar_right_text_drawable, 0);
            rightTextDrawablePaddingRes = array.getDimensionPixelSize(R.styleable.Flier_titlebar_right_text_drawable_padding, defaultDrawablePadding);
        } else if (rightType == TYPE_LAYOUT) {
            rightLayoutRes = array.getResourceId(R.styleable.Flier_titlebar_right_view, 0);
        } else if (rightType == TYPE_TEXT_RED_POT) {
            rightTextRes = array.getString(R.styleable.Flier_titlebar_right_text);
            rightTextColorRes = array.getColor(R.styleable.Flier_titlebar_right_text_color, textColor);
            rightTextDrawableRes = array.getResourceId(R.styleable.Flier_titlebar_right_text_drawable, 0);
            rightTextDrawablePaddingRes = array.getDimensionPixelSize(R.styleable.Flier_titlebar_right_text_drawable_padding, defaultDrawablePadding);
        }

        showBottomShadow = array.getBoolean(R.styleable.Flier_titlebar_bottom_shadow_visible, false);

        if (showBottomShadow) {
            showBottomLine = array.getBoolean(R.styleable.Flier_titlebar_bottom_line_visiable, true);
            if (showBottomLine) {
                mBottomLineDrawable = array.getColor(R.styleable.Flier_titlebar_bottom_line_drawable, Color.GRAY);
            }
        }

        array.recycle();
    }

    private void initView(Context context) {
        setBackgroundColor(backgroundRes);

        mMainLayout = new RelativeLayout(context);
        LayoutParams mMainParams = new LayoutParams(LayoutParams.MATCH_PARENT, ScreenUtils.dip2px(context, 50));

        if (showBottomShadow) {
            View shadowView = new View(context);
            LayoutParams bottomParams = new LayoutParams(LayoutParams.MATCH_PARENT, ScreenUtils.dip2px(context, 3));
            bottomParams.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);
            shadowView.setLayoutParams(bottomParams);
            shadowView.setId(R.id.title_bar_bottom);
            int sdk = Build.VERSION.SDK_INT;
            if (sdk < Build.VERSION_CODES.JELLY_BEAN) {
//                shadowView.setBackgroundDrawable( getResources().getDrawable(R.drawable.shadow_of_title_bar) );
            } else {
//                shadowView.setBackground( getResources().getDrawable(R.drawable.shadow_of_title_bar));
            }
            addView(shadowView);

            mMainParams.addRule(RelativeLayout.ABOVE, R.id.title_bar_bottom);
        } else {
            mMainParams.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);
        }

        if (showBottomLine) {
            mBottomLineView = new View(context);
            LayoutParams bottomParams = new LayoutParams(LayoutParams.MATCH_PARENT, 1);
            bottomParams.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);
            mBottomLineView.setLayoutParams(bottomParams);
            mBottomLineView.setId(R.id.title_bar_bottom);
            mBottomLineView.setBackgroundColor(mBottomLineDrawable);
            addView(mBottomLineView);

            mMainParams.addRule(RelativeLayout.ABOVE, R.id.title_bar_bottom);
        } else {
            mMainParams.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);
        }

        mMainLayout.setLayoutParams(mMainParams);
        addView(mMainLayout);
    }

    //    @TargetApi(Build.VERSION_CODES.JELLY_BEAN_MR1)
    private void initLeftView(Context context, AttributeSet attrs) {
        if (leftType == TYPE_IMAGE) {
            mLeftImageBtn = new ImageButton(context);
            //int leftImageWidth = ScreenUtils.dip2px(context, 55);
            int leftImageHeight = ScreenUtils.dip2px(context, 42);
            LayoutParams mLeftImageParams = new LayoutParams(LayoutParams.WRAP_CONTENT, leftImageHeight);
            mLeftImageParams.addRule(RelativeLayout.ALIGN_PARENT_LEFT);
            mLeftImageParams.addRule(RelativeLayout.CENTER_VERTICAL);
            mLeftImageBtn.setLayoutParams(mLeftImageParams);
            mLeftImageBtn.setPadding((int) context.getResources().getDimension(R.dimen.activity_space_15),
                    3, (int) context.getResources().getDimension(R.dimen.activity_space_15), 5);
            mLeftImageBtn.setScaleType(ScaleType.CENTER_INSIDE);
            mLeftImageBtn.setOnClickListener(clickListener);
            mLeftImageBtn.setImageResource(leftImageRes);
            mLeftImageBtn.setBackgroundColor(context.getResources().getColor(android.R.color.transparent));
            mMainLayout.addView(mLeftImageBtn);
        } else if (leftType == TYPE_TEXT) {
            mLeftTextView = new TextView(context, attrs);
            mLeftTextView.setMaxWidth(ScreenUtils.dip2px(context, 100));
            mLeftTextView.setTextSize(TypedValue.COMPLEX_UNIT_SP, 16);
            mLeftTextView.setTextColor(getResources().getColor(R.color.white));
            mLeftTextView.setGravity(Gravity.CENTER_VERTICAL);
            mLeftTextView.setSingleLine(true);
            mLeftTextView.setEllipsize(TextUtils.TruncateAt.START);
            mLeftTextView.setOnClickListener(clickListener);
            mLeftTextView.setCompoundDrawablesWithIntrinsicBounds(leftTextDrawableRes, 0, 0, 0);
            mLeftTextView.setCompoundDrawablePadding(leftTextDrawablePaddingRes);
            mLeftTextView.setPadding(((int) context.getResources().getDimension(R.dimen.activity_space_15)), 0, ScreenUtils.dip2px(context, 10), 0);
            LayoutParams mLeftTextParams = new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
//            mLeftTextParams.setMargins(((int) context.getResources().getDimension(R.dimen.activity_space_15)), 0, 0, 0);
            mLeftTextParams.addRule(RelativeLayout.ALIGN_PARENT_LEFT);
            mLeftTextParams.addRule(RelativeLayout.CENTER_VERTICAL);
            mLeftTextView.setLayoutParams(mLeftTextParams);

            mLeftTextView.setText(leftTextRes);
            mLeftTextView.setCompoundDrawablePadding((int) leftTextDrawablePaddingRes);
            mLeftTextView.setCompoundDrawablesRelativeWithIntrinsicBounds(leftTextDrawableRes, 0, 0, 0);
            mMainLayout.addView(mLeftTextView);
        }
    }

    private void initCenterView(Context context, AttributeSet attrs) {
        mCenterLayout = new RelativeLayout(context);
        mCenterLayout.setMinimumWidth(ScreenUtils.dip2px(context, 150));
        LayoutParams mCenterParams = new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
        mCenterParams.addRule(RelativeLayout.CENTER_IN_PARENT);
        mCenterLayout.setLayoutParams(mCenterParams);

        LayoutParams centerChildParams = new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
        centerChildParams.addRule(RelativeLayout.CENTER_HORIZONTAL);
        if (centerType == TYPE_IMAGE) {
            mCenterImageView = new ImageView(context);
            mCenterImageView.setLayoutParams(centerChildParams);
            mCenterImageView.setImageResource(centerImageRes);
            mCenterImageView.setScaleType(ScaleType.FIT_CENTER);
            mCenterLayout.addView(mCenterImageView);
        } else if (centerType == TYPE_TEXT) {
            mCenterTextView = new TextView(context, attrs);
            mCenterTextView.setId(R.id.title_bar_center_title);
            mCenterTextView.setLayoutParams(centerChildParams);
            mCenterTextView.setMaxWidth(ScreenUtils.dip2px(context, 200));
            mCenterTextView.setTextSize(TypedValue.COMPLEX_UNIT_SP, 18);
            mCenterTextView.setTextColor(textColor);
            mCenterTextView.setGravity(Gravity.CENTER);
            mCenterTextView.setSingleLine(true);
            mCenterTextView.setEllipsize(TextUtils.TruncateAt.END);
            mCenterTextView.setText(centerTextRes);
            mCenterLayout.addView(mCenterTextView);

            addCenterSubTextView(context);
        } else if (centerType == TYPE_TEXT_MARQUEE) {
            mCenterTextView = new TextView(context, attrs);
            mCenterTextView.setId(R.id.title_bar_center_title);
            LayoutParams params = new LayoutParams(ScreenUtils.dip2px(context, 175), LayoutParams.WRAP_CONTENT);
            mCenterTextView.setLayoutParams(params);
            mCenterTextView.setGravity(CENTER_HORIZONTAL);
            mCenterTextView.setEllipsize(TextUtils.TruncateAt.MARQUEE);
            mCenterTextView.setMarqueeRepeatLimit(-1);
            mCenterTextView.setFocusable(true);
            mCenterTextView.setFocusableInTouchMode(true);
            mCenterTextView.setTextSize(TypedValue.COMPLEX_UNIT_SP, 18);
            mCenterTextView.setTextColor(textColor);
            mCenterTextView.setGravity(Gravity.CENTER);
            mCenterTextView.setSingleLine(true);
            mCenterTextView.setText(centerTextRes);
            mCenterLayout.addView(mCenterTextView);
        }
        mMainLayout.addView(mCenterLayout);
    }

    private void addCenterSubTextView(Context context) {
        if (mCenterSubTextView == null) {
            mCenterSubTextView = new TextView(context);
            LayoutParams centerSubParams = new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
            centerSubParams.addRule(RelativeLayout.CENTER_HORIZONTAL);
            centerSubParams.addRule(RelativeLayout.BELOW, R.id.title_bar_center_title);
            mCenterSubTextView.setLayoutParams(centerSubParams);
            mCenterSubTextView.setTextSize(TypedValue.COMPLEX_UNIT_SP, 13);
            mCenterSubTextView.setTextColor(textColor);
            mCenterSubTextView.setSingleLine(true);
            mCenterSubTextView.setText(centerSubTextRes);
            mCenterLayout.addView(mCenterSubTextView);
            if (!TextUtils.isEmpty(centerSubTextRes)) {
                mCenterSubTextView.setVisibility(View.VISIBLE);
            } else {
                mCenterSubTextView.setVisibility(View.GONE);
            }
        }
    }

    //    @TargetApi(Build.VERSION_CODES.JELLY_BEAN_MR1)
    private void initRightView(Context context, AttributeSet attrs) {
        if (rightType == TYPE_IMAGE) {
            mRightImageBtn = new ImageButton(context);
            int rightImageWidth = ScreenUtils.dip2px(context, 50);
            int rightImageHeight = ScreenUtils.dip2px(context, 40);
            LayoutParams mRightImageParams = new LayoutParams(rightImageWidth, rightImageHeight);
            mRightImageParams.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
            mRightImageParams.addRule(RelativeLayout.CENTER_VERTICAL);
            mRightImageBtn.setLayoutParams(mRightImageParams);
            mRightImageBtn.setScaleType(ScaleType.CENTER_INSIDE);
            mRightImageBtn.setOnClickListener(clickListener);

            mRightImageBtn.setImageResource(rightImageRes);
            mRightImageBtn.setBackgroundColor(context.getResources().getColor(android.R.color.transparent));
            mRightImageBtn.setVisibility(View.VISIBLE);
            mMainLayout.addView(mRightImageBtn);
        } else if (rightType == TYPE_TEXT) {
            mRightTextView = new TextView(context, attrs);
            mRightTextView.setMaxWidth(ScreenUtils.dip2px(context, 150));
            mRightTextView.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 16);
            mRightTextView.setTextColor(rightTextColorRes);
            mRightTextView.setGravity(Gravity.CENTER_VERTICAL);
            mRightTextView.setPadding(ScreenUtils.dip2px(context, 10), 0, ((int) context.getResources().getDimension(R.dimen.activity_space_15)), 0);
            mRightTextView.setSingleLine(true);
            mRightTextView.setEllipsize(TextUtils.TruncateAt.END);
            mRightTextView.setOnClickListener(clickListener);
            LayoutParams mRightTextParams = new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.MATCH_PARENT);
//            mRightTextParams.setMargins(0, 0, ((int) context.getResources().getDimension(R.dimen.activity_space_15)), 0);
            mRightTextParams.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
            mRightTextParams.addRule(RelativeLayout.CENTER_VERTICAL);
            mRightTextView.setLayoutParams(mRightTextParams);

            mRightTextView.setText(rightTextRes);
            mRightTextView.setCompoundDrawablePadding(rightTextDrawablePaddingRes);
            mRightTextView.setCompoundDrawablesRelativeWithIntrinsicBounds(rightTextDrawableRes, 0, 0, 0);
            mMainLayout.addView(mRightTextView);
        } else if (rightType == TYPE_LAYOUT) {
            mRightLayout = LayoutInflater.from(context).inflate(rightLayoutRes, null);
            LayoutParams mRightLayoutParams = new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.MATCH_PARENT);
            mRightLayoutParams.setMargins(0, 0, ((int) context.getResources().getDimension(R.dimen.activity_space_15)), 0);
            mRightLayoutParams.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
            mRightLayoutParams.addRule(RelativeLayout.CENTER_VERTICAL);
            mRightLayout.setLayoutParams(mRightLayoutParams);
            mMainLayout.addView(mRightLayout);
        } else if (rightType == TYPE_TEXT_RED_POT) {
            mRightTextView = new RedPotTextView(context, attrs);
            mRightTextView.setMaxWidth(ScreenUtils.dip2px(context, 100));
            mRightTextView.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 13);
            mRightTextView.setTextColor(rightTextColorRes);
            mRightTextView.setGravity(Gravity.CENTER_VERTICAL);
            mRightTextView.setPadding(ScreenUtils.dip2px(context, 10), 0, 0, 0);
            mRightTextView.setSingleLine(true);
            mRightTextView.setEllipsize(TextUtils.TruncateAt.START);
            mRightTextView.setOnClickListener(clickListener);
            LayoutParams mRightTextParams = new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.MATCH_PARENT);
            mRightTextParams.setMargins(0, 0, ((int) context.getResources().getDimension(R.dimen.activity_space_15)), 0);
            mRightTextParams.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
            mRightTextParams.addRule(RelativeLayout.CENTER_VERTICAL);
            mRightTextView.setLayoutParams(mRightTextParams);

            mRightTextView.setText(rightTextRes);
            mRightTextView.setCompoundDrawablePadding(rightTextDrawablePaddingRes);
            mRightTextView.setCompoundDrawablesRelativeWithIntrinsicBounds(rightTextDrawableRes, 0, 0, 0);
            mMainLayout.addView(mRightTextView);
        }
    }

    private void initAnimation() {
        animation1 = new TranslateAnimation(0, 0, 0, 20);
        animation1.setDuration(300);
        animation1.setInterpolator(new AccelerateDecelerateInterpolator());
        animation1.setAnimationListener(animationListener);
        animation2 = new TranslateAnimation(0, 0, -20, 0);
        animation2.setDuration(300);
        animation2.setInterpolator(new AccelerateDecelerateInterpolator());
    }

    public void playDismissAnimation() {
        mCenterSubTextView.startAnimation(animation1);
    }

    public void playComeInAnimation(String newSubTitle) {
        mCenterSubTextView.setText(newSubTitle);
        mCenterSubTextView.startAnimation(animation2);
    }

    public void setTitleBarListener(OnTitleBarClick titleBarListener) {
        this.titleBarListener = titleBarListener;
    }

    private OnClickListener clickListener = new OnClickListener() {
        public void onClick(View v) {
            if (titleBarListener == null) return;
            if (v.equals(mLeftImageBtn)) {
                titleBarListener.onLeftClicked(TYPE_IMAGE, mLeftImageBtn);
            } else if (v.equals(mLeftTextView)) {
                titleBarListener.onLeftClicked(TYPE_TEXT, mLeftTextView);
            } else if (v.equals(mRightImageBtn)) {
                titleBarListener.onRightClicked(TYPE_IMAGE, mRightImageBtn);
            } else if (v.equals(mRightTextView)) {
                titleBarListener.onRightClicked(TYPE_TEXT, mRightTextView);
            }
        }
    };

    Animation.AnimationListener animationListener = new Animation.AnimationListener() {
        @Override
        public void onAnimationStart(Animation arg0) {
        }

        @Override
        public void onAnimationRepeat(Animation arg0) {
        }

        @Override
        public void onAnimationEnd(Animation arg0) {
            playComeInAnimation(centerSubTextRes);
        }
    };

    public ImageButton getLeftImageBtn() {
        return mLeftImageBtn;
    }

    public ImageButton getRightImageBtn() {
        return mRightImageBtn;
    }

    public TextView getLeftTextView() {
        return mLeftTextView;
    }

    public TextView getRightTextView() {
        return mRightTextView;
    }

    public TextView getmCenterTextView() {
        return mCenterTextView;
    }

    public void setCenterText(String cTitle) {
        mCenterTextView.setText(cTitle);
    }

    public View getRightLayout() {
        return mRightLayout;
    }

    public void setCenterSubTextView(String subTitle) {
        if (!TextUtils.isEmpty(subTitle)) {
            mCenterSubTextView.setVisibility(View.VISIBLE);
            centerSubTextRes = subTitle;
            mCenterSubTextView.setText(subTitle);
        } else {
            mCenterSubTextView.setVisibility(View.GONE);
        }
        playDismissAnimation();
    }
}
