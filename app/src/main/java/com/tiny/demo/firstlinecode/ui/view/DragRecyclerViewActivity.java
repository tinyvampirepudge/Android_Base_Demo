package com.tiny.demo.firstlinecode.ui.view;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.text.TextUtils;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.tiny.demo.firstlinecode.base.BaseActivity;
import com.tiny.demo.firstlinecode.R;
import com.tiny.demo.firstlinecode.app.FLCApplication;
import com.tiny.demo.firstlinecode.common.constant.ResponseCode;
import com.tiny.demo.firstlinecode.common.interfaces.OnTitleBarClick;
import com.tiny.demo.firstlinecode.common.utils.DeviceInfo;
import com.tiny.demo.firstlinecode.common.utils.ListUtils;
import com.tiny.demo.firstlinecode.common.utils.LogUtils;
import com.tiny.demo.firstlinecode.common.utils.ToastUtils;
import com.tiny.demo.firstlinecode.common.view.TitleBarLayout;
import com.tiny.demo.firstlinecode.stetho.httphelper.UrlConfig;
import com.tiny.demo.firstlinecode.ui.adapter.OptionalEditAdapter;
import com.tiny.demo.firstlinecode.ui.bean.StockEditBean;
import com.tiny.demo.firstlinecode.ui.contract.OptionalResortContract;
import com.tiny.demo.firstlinecode.ui.operateoptional.OperateOptionalHandler;
import com.tiny.demo.firstlinecode.ui.operateoptional.OperateOptionalInterface;
import com.tiny.demo.firstlinecode.ui.presenter.OptionalResortPresenter;
import com.tiny.demo.firstlinecode.ui.widget.RecycleItemTouchHelper;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.OnClick;
import okhttp3.ResponseBody;

/**
 * Desc:    使用RecyclerView实现拖拽排序, 全选，反选，删除等操作,侧滑删除操作。
 * Created by tiny on 2017/10/25.
 * Version:
 * http://blog.csdn.net/a553181867/article/details/54799391
 */

public class DragRecyclerViewActivity extends BaseActivity implements OptionalResortContract.IView, OperateOptionalInterface {
    public static final String STOCK_LIST = "STOCK_LIST";
    @BindView(R.id.title_bar_zixuan_edit)
    TitleBarLayout titleBarZixuanEdit;
    @BindView(R.id.recycler_view_drag)
    RecyclerView recyclerViewDrag;
    @BindView(R.id.txt_check_all)
    TextView txtCheckAll;
    @BindView(R.id.layout_delete)
    RelativeLayout layoutDelete;

    private List<StockEditBean> stockList = new ArrayList<>();
    private List<StockEditBean> stockOriginList = new ArrayList<>();
    private OptionalEditAdapter editAdapter;

    //request
    private Map<String, String> resortParams;
    OptionalResortContract.IPresenter mPresenter;
    private OperateOptionalHandler operateOptionalHandler;

    @Override
    protected int setContentLayout() {
        return R.layout.activity_drag_recyclerview;
    }

    @Override
    protected void buildContentView() {
        if (getIntent() != null) {
            stockList = getIntent().getParcelableArrayListExtra(STOCK_LIST);
        }
        if (stockList == null) {
            stockList = new ArrayList<>();
        }
        for (int j = 0; j < stockList.size(); j++) {
            StockEditBean bean = new StockEditBean();
            bean.setName(stockList.get(j).getName());
            bean.setCode(stockList.get(j).getCode());
            bean.setChecked(stockList.get(j).isChecked());
            stockOriginList.add(bean);
        }

        titleBarZixuanEdit.setTitleBarListener(onTitleBarClick);

        recyclerViewDrag.setLayoutManager(new LinearLayoutManager(mContext, LinearLayoutManager.VERTICAL, false));
        editAdapter = new OptionalEditAdapter(stockList, mContext, updateAllCheckedStatus);
        recyclerViewDrag.setAdapter(editAdapter);
        //实现抽象类ItemTouchHelper.Callback，然后将其设置给ItemTouchHelper，
        //最后将itemTouchHelper与RecyclerView关联起来。
        ItemTouchHelper.Callback callback = new RecycleItemTouchHelper(editAdapter);
        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(callback);
        itemTouchHelper.attachToRecyclerView(recyclerViewDrag);

        //presenter
        resortParams = new HashMap<>();
        mPresenter = new OptionalResortPresenter(this);
        operateOptionalHandler = new OperateOptionalHandler(this, this);
    }

    private Runnable updateAllCheckedStatus = () -> updateAllCheckedStatus(false);

    @Override
    protected void initViewData() {

    }

    private OnTitleBarClick onTitleBarClick = new OnTitleBarClick() {
        @Override
        public void onLeftClicked(int type, View v) {
            //do nothing, just finish.
            finish();
        }

        @Override
        public void onRightClicked(int type, View v) {
            resortParams.clear();
            //原始列表和排序后的类别不同的时候再进行请求
            if (!ListUtils.isEmpty(stockList) && !ListUtils.isEmpty(stockOriginList) && !equalsList(stockList, stockOriginList)) {
                if (stockList != null && stockList.size() > 0) {
                    ListUtils.logList(stockList);
                    String codeStr = getCodeStr(stockList);
                    if (!TextUtils.isEmpty(codeStr)) {
                        resortParams.put("code", codeStr);
                        resortParams.put(UrlConfig.Params.DEVICE_ID, DeviceInfo.getDeviceId(FLCApplication.instance()));
                        mPresenter.getDataFromNet(resortParams);
                    }
                }
            } else {
                finish();
            }
        }
    };

    /**
     * 判断两个list是否相等。
     * 根据code
     *
     * @param list1
     * @param list2
     * @return
     */
    private boolean equalsList(List<StockEditBean> list1, List<StockEditBean> list2) {
        if (list1 == list2) {
            return true;
        }
        if (list1 == null || list2 == null) {
            return false;
        }
        if (list1.size() != list2.size()) {
            return false;
        }
        for (int i = 0; i < list1.size(); i++) {
            if (!list1.get(i).getCode().equals(list2.get(i).getCode())) {
                return false;
            }
        }
        return true;
    }

    private String getCodeStr(List<StockEditBean> list) {
        StringBuffer sb = new StringBuffer();
        for (StockEditBean bean : list) {
            sb.append(bean.getCode()).append(",");
        }
        String str = sb.toString();
        if (!TextUtils.isEmpty(str) && str.endsWith(",")) {
            str = str.substring(0, str.length() - 1);
        }
        return str;
    }

    @OnClick({R.id.txt_check_all, R.id.layout_delete})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.txt_check_all:
                updateAllCheckedStatus(true);
                break;
            case R.id.layout_delete:
                List<StockEditBean> list = getCheckedItem();
                if (ListUtils.isEmpty(list)) {
                    ToastUtils.showSingleToast(getString(R.string.string_please_select_stock));
                } else {
                    ListUtils.logList(list);
                    String stockCodeStr = getCodeStr(list);
                    if (!TextUtils.isEmpty(stockCodeStr)) {
                        operateOptionalHandler.batchDeleteOptional(stockCodeStr, getString(R.string.string_ensure_delete_all_optional));
                    }
                }
                break;
        }
    }

    /**
     * 更新全选框的按钮
     *
     * @param changeList 是否需要刷新list数据
     */
    private void updateAllCheckedStatus(boolean changeList) {
        if (stockList != null && stockList.size() > 0) {
            boolean isAllChecked = true;
            for (StockEditBean bean : stockList) {
                if (!bean.isChecked()) {
                    isAllChecked = false;
                    break;
                }
            }
            if (changeList) {
                isAllChecked = !isAllChecked;
                //更新list
                for (StockEditBean bean : stockList) {
                    bean.setChecked(isAllChecked);
                }
                editAdapter.notifyDataSetChanged();
            }
            txtCheckAll.setCompoundDrawablesWithIntrinsicBounds(isAllChecked ? R.drawable.ic_optional_select_box2 : R.drawable.ic_optional_select_box, 0, 0, 0);
        }
    }

    /**
     * 得到选中的数据
     *
     * @return
     */
    private List<StockEditBean> getCheckedItem() {
        List<StockEditBean> stockEditList = new ArrayList<>();
        if (stockList != null && stockList.size() > 0) {
            for (StockEditBean bean : stockList) {
                if (bean.isChecked()) {
                    stockEditList.add(bean);
                }
            }
        }
        for (StockEditBean bean : stockList) {
            LogUtils.e(bean.toString());
        }
        return stockEditList;
    }

    @Override
    public void showLoadingDialog() {
//        showProgressDialog(false);
    }

    @Override
    public void dismissLoadingDialog() {
//        dismissProgressDialog();
    }

    @Override
    public void addOptionalSuccess() {

    }

    @Override
    public void deleteOptionalSuccess() {

    }

    @Override
    public void optionAdded(boolean isAdded) {

    }

    @Override
    public void showErrorMsg(String errorMsg) {
        ToastUtils.showErrorToast(errorMsg, true);
    }

    @Override
    public void sendResultToView(ResponseBody responseBody, Map<String, String> params) {
        String responseString = null;
        try {
            responseString = responseBody.string();
            JSONObject jsonObject = new JSONObject(responseString);
            int code = jsonObject.getInt("code");
            String errorMsg = jsonObject.getString("msg");
            if (ResponseCode.SUCCESS == code) {
                ToastUtils.showSingleToast(errorMsg);
                finish();
            } else {
                ToastUtils.showErrorToast(errorMsg, true);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
