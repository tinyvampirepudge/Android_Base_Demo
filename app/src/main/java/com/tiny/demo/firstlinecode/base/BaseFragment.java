package com.tiny.demo.firstlinecode.base;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.os.Build;
import android.os.Bundle;
import android.os.IBinder;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.ImageView;

import com.tiny.demo.firstlinecode.common.utils.ToastUtils;

import java.util.HashMap;
import java.util.Map;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Fragment基类
 * <p>
 * Created by tiny on 2015/3/9.
 */
public abstract class BaseFragment extends Fragment {
    protected View mContentView;
    protected BaseActivity mContext;
    Unbinder unbinder;
    protected Map<String, BaseFragment> subFmTags = new HashMap<String, BaseFragment>();

    protected LayoutInflater mInflater;
    public boolean hasChanged;


    public BaseActivity getContext() {
        return mContext;
    }

    public BaseFragment() {
    }

    protected View findViewById(int id) {
        return mContentView.findViewById(id);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mInflater = inflater;
        mContentView = mInflater.inflate(setContentLayout(), container, false);
        unbinder = ButterKnife.bind(this, mContentView);
        buildContentView(mContentView, savedInstanceState);
        hasChanged = true;
        return mContentView;
    }

    public boolean onBackPressed() {
        return false;
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        mContext = (BaseActivity) activity;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        String tag = this.getTag();
        BaseFragment parentFm = (BaseFragment) this.getParentFragment();
        if (parentFm != null) {
            parentFm.subFmTags.put(tag, this);
        }
        mContext = (BaseActivity) context;
        //第一次还未成功attach,调用补一个 hidden 回调。4.4特殊，第一次就成功attach了。
        if ((!this.isAdded() ||
                Build.VERSION.SDK_INT <= Build.VERSION_CODES.KITKAT) //4.4上isAdded必为true
                && (this.getTag() == null || (this.getTag().contains("android:switcher:")))) {
            if (getUserVisibleHint()) {
                onHiddenChanged(false);
            } else {
                onHiddenChanged(true);
            }
        }

    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        if (isAdded()) mContentView.postDelayed(
                () -> initViewData(), 200);
    }

    // 设置布局资源
    protected abstract int setContentLayout();

    // 完成一些初始化工作
    protected abstract void buildContentView(View contentView, Bundle savedInstanceState);

    // 执行界面创建后的数据交互
    protected abstract void initViewData();

    protected FragmentManager getSupportFragmentManager() {
        return getChildFragmentManager();
    }

    public void showToast(String message) {
        showErrorMessage(message, true);
    }

    public void showBlankToast(String msg) {
        showErrorMessage(msg, true);
    }

    protected void showToast(int res) {
        if (isAdded()) showToast(getString(res));
    }

    /**
     * 显示软键盘
     */
    protected void showSoftInputKeyboard(View v) {

        InputMethodManager imm = (InputMethodManager) v.getContext().getSystemService(Context.INPUT_METHOD_SERVICE);

        imm.showSoftInput(v, InputMethodManager.SHOW_FORCED);

    }

    // 隐藏软键盘
    protected void hideSoftInputKeyboard() {
        InputMethodManager inputMethodManager =
                (InputMethodManager) mContext.getSystemService(Context.INPUT_METHOD_SERVICE);
        View focusView = getActivity().getCurrentFocus();
        if (focusView != null) {
            IBinder binder = focusView.getWindowToken();
            if (binder != null && inputMethodManager.isActive()) {
                inputMethodManager.hideSoftInputFromWindow(binder, 0);
            }
        }
    }

    // 页面切换方法，不带参数
    protected void activitySwitch(Class<?> cls) {
        Intent intent = new Intent(mContext, cls);
        startActivity(intent);
    }

    // 页面切换方法，带参数
    protected void activitySwitchWithBundle(Class<?> cls, Bundle bundle) {
        Intent intent = new Intent(mContext, cls);
        intent.putExtras(bundle);
        startActivity(intent);
    }

    protected void activitySwitchForResult(Class<?> cls, int requestCode) {
        Intent intent = new Intent(mContext, cls);
        startActivityForResult(intent, requestCode);
    }

    protected void activitySwitchForResultWithBundle(Class<?> cls, int requestCode, Bundle args) {
        Intent intent = new Intent(mContext, cls);
        intent.putExtras(args);
        ((Activity) mContext).startActivityForResult(intent, requestCode);
    }

    protected void showErrorMessage(String err) {
        showErrorMessage(err, true);
    }

    /**
     * 显示错误dialog
     *
     * @param err
     * @param hidError
     */
    public void showErrorMessage(String err, boolean hidError) {
        ToastUtils.showErrorToast(err, hidError);
    }

    public AnimationDrawable getAnimationDrawable(ImageView imageView) {
        return (AnimationDrawable) imageView.getDrawable();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @Override
    public void setRetainInstance(boolean retain) {
        super.setRetainInstance(retain);
    }
}
