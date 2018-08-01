package com.tiny.demo.firstlinecode.rxjava2.blog4.module.map_2;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.tiny.demo.firstlinecode.R;
import com.tiny.demo.firstlinecode.common.utils.ToastUtils;
import com.tiny.demo.firstlinecode.rxjava2.BaseRxFragment;
import com.tiny.demo.firstlinecode.rxjava2.blog4.adapter.ItemListAdapter;
import com.tiny.demo.firstlinecode.rxjava2.blog4.model.Item;
import com.tiny.demo.firstlinecode.rxjava2.blog4.network.NetWork;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

/**
 * Desc:    map操作符实战。
 *
 * @author tiny
 * @date 2018/6/17 下午11:22
 */
public class MapFragment extends BaseRxFragment {
    @BindView(R.id.pageTv)
    TextView pageTv;
    @BindView(R.id.previousPageBt)
    AppCompatButton previousPageBt;
    @BindView(R.id.nextPageBt)
    AppCompatButton nextPageBt;
    @BindView(R.id.swipeRefreshLayout)
    SwipeRefreshLayout swipeRefreshLayout;
    @BindView(R.id.gridRv)
    RecyclerView gridRv;

    private int page = 1;

    private ItemListAdapter adapter = new ItemListAdapter();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_map, container, false);
        ButterKnife.bind(this, view);

        gridRv.setLayoutManager(new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL));
        gridRv.setAdapter(adapter);
        swipeRefreshLayout.setColorSchemeColors(Color.BLUE, Color.GREEN, Color.RED, Color.YELLOW);
        swipeRefreshLayout.setRefreshing(false);

        loadPage(page);

        return view;
    }

    @Override
    protected int getDialogRes() {
        return R.layout.dialog_map;
    }

    @Override
    protected int getTitleRes() {
        return R.string.title_map;
    }

    @OnClick(R.id.previousPageBt)
    public void onPreviousPageBtClicked() {
        if (page > 1) {
            loadPage(--page);
        }
    }

    @OnClick(R.id.nextPageBt)
    public void onNextPageBtClicked() {
        loadPage(++page);
    }

    private void loadPage(int page) {
        swipeRefreshLayout.setRefreshing(false);
        unsubscribe();
        disposable = NetWork.getGankApi()
                .getBeauties(10, page)
                .map(GankBeautyResultToItemsMapper.getInstance())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<List<Item>>() {
                    @Override
                    public void accept(List<Item> items) throws Exception {
                        previousPageBt.setEnabled(page > 1);
                        swipeRefreshLayout.setRefreshing(false);
                        pageTv.setText(getString(R.string.page_with_number, page));
                        adapter.setItems(items);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        swipeRefreshLayout.setRefreshing(false);
                        ToastUtils.showSingleToast(getResources().getString(R.string.loading_failed));
                    }
                });
    }
}
