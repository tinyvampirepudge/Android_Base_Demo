package com.tiny.demo.firstlinecode.ui.operateoptional;

import android.text.TextUtils;
import android.view.View;

import com.tiny.demo.firstlinecode.base.BaseActivity;
import com.tiny.demo.firstlinecode.R;
import com.tiny.demo.firstlinecode.common.utils.DialogUtils;
import com.tiny.demo.firstlinecode.common.utils.ToastUtils;

import java.util.HashMap;
import java.util.Map;

/**
 * Desc:    增加、删除自选股
 * Created by tiny on 2017/10/16.
 * Version:
 */

public class OperateOptionalHandler implements OperateOptionalContract.IView {
    public static final String STOCK_CODE = "code";
    private BaseActivity mContext;
    private OperateOptionalInterface operateOptionalInterface;
    private OperateOptionalContract.IPresenter presenter;

    public OperateOptionalHandler(BaseActivity mContext, OperateOptionalInterface operateOptionalInterface) {
        this.mContext = mContext;
        this.operateOptionalInterface = operateOptionalInterface;
        presenter = new OperateOptionalPresenter(this);
    }

    /**
     * 获取当前自选股的状态
     */
    public void checkOptional(String stockCode) {
        if (TextUtils.isEmpty(stockCode)) {
            return;
        }
        HashMap<String, String> params = new HashMap<>();
        params.put(STOCK_CODE, stockCode);
        presenter.checkZiXuanFromNet(params);
    }

    /**
     * 添加自选股
     */
    public void addOptional(String stockCode) {
        if (TextUtils.isEmpty(stockCode)) {
            return;
        }
        HashMap<String, String> params = new HashMap<>();
        params.put(STOCK_CODE, stockCode);
        presenter.addZiXuanFromNet(params);
    }

    /**
     * 删除自选股
     */
    public void deleteOptional(final String stockCode, String stockName) {
        if (TextUtils.isEmpty(stockCode)) {
            return;
        }
        String deleteDesc;
        if (!TextUtils.isEmpty(stockName)) {
            deleteDesc = "确定删除" + stockName + "?";
        } else {
            deleteDesc = "确定删除" + stockCode + "?";
        }
        DialogUtils.showDeleteStockDialog(mContext, deleteDesc, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Map<String, String> p = new HashMap<>();
                p.put(STOCK_CODE, stockCode);
                presenter.delZiXuanFromNet(p);
            }
        }, deleteCancelListener, true);
    }

    /**
     * 批量删除自选股
     *
     * @param stockCodeStrs 股票或板块代码，按照顺序把code用英文逗号连接
     * @param deleteDesc    删除对话框中的提示信息
     */
    public void batchDeleteOptional(final String stockCodeStrs, String deleteDesc) {
        if (TextUtils.isEmpty(stockCodeStrs)) {
            return;
        }
        DialogUtils.showDeleteStockDialog(mContext, deleteDesc, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Map<String, String> p = new HashMap<>();
                p.put(STOCK_CODE, stockCodeStrs);
                presenter.delZiXuanFromNet(p);
            }
        }, deleteCancelListener, true);
    }

    private View.OnClickListener deleteCancelListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {

        }
    };

    @Override
    public void showLoadingDialog() {

    }

    @Override
    public void dismissLoadingDialog() {

    }

    @Override
    public void showErrorMsg(String errorMsg) {
        if (operateOptionalInterface != null) {
            operateOptionalInterface.showErrorMsg(errorMsg);
        }
    }

    @Override
    public void sendAddZixuanToView(Map<String, String> param) {
        if (param != null && param.size() > 0 && !TextUtils.isEmpty(param.get(STOCK_CODE))) {
            ToastUtils.showSingleToast(mContext.getString(R.string.string_add_optional_succ));
            if (operateOptionalInterface != null) {
                operateOptionalInterface.addOptionalSuccess();
            }
        }

    }

    @Override
    public void delZiXuanToView(Map<String, String> param) {
        if (param != null && param.size() > 0 && !TextUtils.isEmpty(param.get(STOCK_CODE))) {
            ToastUtils.showSingleToast(mContext.getString(R.string.string_delete_optional_succ));
            if (operateOptionalInterface != null) {
                operateOptionalInterface.deleteOptionalSuccess();
            }
        }
    }

    @Override
    public void checkZiXuanToView(String status) {
        if (!TextUtils.isEmpty(status)) {
            if (operateOptionalInterface != null) {
                operateOptionalInterface.optionAdded("1".equals(status));
            }
        } else {

        }
    }
}
