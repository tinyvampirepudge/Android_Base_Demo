package com.tiny.demo.firstlinecode.leakcanary;

import android.content.Context;
import android.widget.TextView;

/**
 * Desc:
 * Created by tiny on 2017/12/10.
 * Version:
 */

public class LeakHelper {
    private Context mCtx;
    private TextView mTextView;

    private static LeakHelper ourInstance = null;

    public static LeakHelper getInstance(Context context) {
        if (ourInstance == null) {
            ourInstance = new LeakHelper(context);
        }
        return ourInstance;
    }

    public void setRetainedTextView(TextView tv) {
        this.mTextView = tv;
        mTextView.setText(mCtx.getString(android.R.string.ok));
    }

    private LeakHelper() {
    }

    private LeakHelper(Context context) {
        this.mCtx = context;
    }
}
