package com.tiny.demo.firstlinecode.filter;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Filter;
import android.widget.LinearLayout;
import android.widget.ListView;

import com.example.tiny.tinymodule.util.ThreadUtils;
import com.tiny.demo.firstlinecode.R;
import com.tiny.demo.firstlinecode.base.BaseActivity;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import butterknife.BindView;

/**
 * @Description: Android中Filter的使用。
 * {link https://developer.android.com/reference/kotlin/android/widget/Filter}
 * @Author wangjianzhou@qding.me
 * @Date 2019/3/8 4:44 PM
 * @Version
 */
public class FilterTestActivity extends BaseActivity {

    @BindView(R.id.search_et)
    ClearEditText searchEt;
    @BindView(R.id.common_empty_layout_root_ll)
    LinearLayout commonEmptyLayoutRootLl;
    @BindView(R.id.search_result_lv)
    ListView searchResultLv;
    private FilterAdapter adapter;
    private List<FilterBean> mLists;
    private List<FilterBean> mSearchResults;

    public static void actionStart(Context context) {
        Intent starter = new Intent(context, FilterTestActivity.class);
        context.startActivity(starter);
    }

    @Override
    protected int setContentLayout() {
        return R.layout.activity_filter_test;
    }

    @Override
    protected void buildContentView() {

    }

    @Override
    protected void initViewData() {
        mLists = generateData();
        refreshAdapter(mLists);
    }

    @Override
    protected void onPostCreate(@Nullable Bundle savedInstanceState) {
        searchEt.addTextChangedListener(textWatcher);
        super.onPostCreate(savedInstanceState);
    }

    private TextWatcher textWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {

        }

        @Override
        public void afterTextChanged(Editable s) {
            String str = s.toString();
            if (str != null && adapter != null) {
                adapter.getFilter().filter(str);
            }
        }
    };

    private void refreshAdapter(List<FilterBean> list) {
        if (adapter == null) {
            adapter = new FilterAdapter(mContext, list);
            searchResultLv.setAdapter(adapter);
        } else {
            adapter.setData(list);
        }

        commonEmptyLayoutRootLl.setVisibility(list.isEmpty() ? View.VISIBLE : View.GONE);
        searchResultLv.setVisibility(list.isEmpty() ? View.GONE : View.VISIBLE);
    }

    private List<FilterBean> generateData() {
        List<FilterBean> list = new ArrayList<>();

        for (int i = 0; i < 1000; i++) {
            FilterBean bean = new FilterBean("猫了个咪" + i);
            list.add(bean);
        }

        return list;
    }

    public class ListFilter extends Filter {

        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            //运行在子线程
            ThreadUtils.logCurrThreadName("performFiltering");
            String constraintStr = constraint.toString().toLowerCase(Locale.getDefault());
            FilterResults results = new FilterResults();
            if (constraint != null && constraint.toString().length() > 0) {
                List<FilterBean> filterItems = new ArrayList<>();
                synchronized (this) {
                    for (FilterBean item : mLists) {
                        if (!TextUtils.isEmpty(item.getValue())) {
                            if (item.getValue().toLowerCase().contains(constraintStr.toLowerCase())) {
                                filterItems.add(item);
                            }
                        }
                    }
                    results.count = filterItems.size();
                    results.values = filterItems;
                }
            } else {
                synchronized (this) {
                    results.count = mLists.size();
                    results.values = mLists;
                }
            }
            return results;
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            ThreadUtils.logCurrThreadName("publishResults");
            List<FilterBean> filtered = (List<FilterBean>) results.values;
            // sort array and extract sections in background Thread
            synchronized (this) {
                new MyAsyncTask().execute(filtered);
            }
        }
    }

    private class MyAsyncTask extends AsyncTask<List<FilterBean>, Void, Void> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected Void doInBackground(List<FilterBean>... params) {
            try {
                mSearchResults = new ArrayList<>();
                List<FilterBean> items = params[0];
                if (items != null && items.size() > 0) {
                    for (FilterBean current_item : items) {
                        mSearchResults.add(current_item);
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }

            return null;
        }

        @Override
        protected void onPostExecute(Void result) {
            if (!isCancelled()) {
                if (!mSearchResults.isEmpty()) {
                    refreshAdapter(mSearchResults);
                    searchResultLv.setVisibility(View.VISIBLE);
                    commonEmptyLayoutRootLl.setVisibility(View.GONE);
                } else {
                    searchResultLv.setVisibility(View.GONE);
                    commonEmptyLayoutRootLl.setVisibility(View.VISIBLE);
                }
            }
            super.onPostExecute(result);
        }

    }
}
