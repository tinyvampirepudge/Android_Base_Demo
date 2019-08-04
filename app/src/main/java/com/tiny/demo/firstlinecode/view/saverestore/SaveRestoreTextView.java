package com.tiny.demo.firstlinecode.view.saverestore;

import android.content.Context;
import android.os.Build;
import android.os.Parcel;
import android.os.Parcelable;
import android.support.annotation.RequiresApi;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.Log;
import android.util.SparseArray;
import android.view.View;
import android.widget.TextView;

/**
 * @Description: 重写了 View#saveHierarchyState View#restoreHierarchyState方法的TextView
 * {@link android.view.View#saveHierarchyState(SparseArray)}
 * {@link android.view.View#restoreHierarchyState(SparseArray)}
 * @Author wangjianzhou@qding.me
 * @Date 2019-08-04 10:00
 * @Version
 */
public class SaveRestoreTextView extends TextView {
    public static final String TAG = SaveRestoreTextView.class.getSimpleName();

    public SaveRestoreTextView(Context context) {
        super(context);
    }

    public SaveRestoreTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public SaveRestoreTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public SaveRestoreTextView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    @Override
    public Parcelable onSaveInstanceState() {
        Log.e(TAG, "onSaveInstanceState");
        return super.onSaveInstanceState();
    }

    @Override
    public void onRestoreInstanceState(Parcelable state) {
        super.onRestoreInstanceState(state);
        Log.e(TAG, "onRestoreInstanceState");

    }
}
