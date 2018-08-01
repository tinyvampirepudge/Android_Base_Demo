package com.tiny.demo.firstlinecode.rxjava2;

import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;

import com.tiny.demo.firstlinecode.R;

import butterknife.OnClick;
import io.reactivex.disposables.Disposable;

/**
 * Desc:
 *
 * @author tiny
 * @date 2018/6/17 下午5:55
 */
public abstract class BaseRxFragment extends Fragment {
    protected Disposable disposable;

    @OnClick(R.id.tipBt)
    void tip() {
        new AlertDialog.Builder(getActivity())
                .setTitle(getTitleRes())
                .setView(getActivity().getLayoutInflater().inflate(getDialogRes(), null))
                .show();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unsubscribe();
    }

    /**
     * 取消订阅，防止内存泄漏
     */
    protected void unsubscribe() {
        if (disposable != null && !disposable.isDisposed()) {
            disposable.dispose();
        }
    }

    protected abstract int getDialogRes();

    protected abstract int getTitleRes();
}
