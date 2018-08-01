package com.tiny.demo.firstlinecode.rxjava2.blog4.module.zip_3;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.tiny.demo.firstlinecode.R;
import com.tiny.demo.firstlinecode.common.utils.ToastUtils;
import com.tiny.demo.firstlinecode.rxjava2.BaseRxFragment;
import com.tiny.demo.firstlinecode.rxjava2.blog4.adapter.ItemListAdapter;
import com.tiny.demo.firstlinecode.rxjava2.blog4.model.Item;
import com.tiny.demo.firstlinecode.rxjava2.blog4.model.ZhuangbiImage;
import com.tiny.demo.firstlinecode.rxjava2.blog4.module.map_2.GankBeautyResultToItemsMapper;
import com.tiny.demo.firstlinecode.rxjava2.blog4.network.NetWork;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.BiFunction;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

/**
 * Desc: zip操作符
 * 会将多个被观察者合并，根据各个被观察者发送事件的顺序一个个结合起来，
 * 最终发送的事件数量会与源 Observable 中最少事件的数量一样。
 *
 * @author tiny
 * @date 2018/6/18 上午12:17
 */
public class ZipFragment extends BaseRxFragment {
    @BindView(R.id.gridRv)
    RecyclerView gridRv;
    @BindView(R.id.swipeRefreshLayout)
    SwipeRefreshLayout swipeRefreshLayout;

    private ItemListAdapter adapter = new ItemListAdapter();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_zip, container, false);
        ButterKnife.bind(this, view);

        gridRv.setLayoutManager(new GridLayoutManager(getActivity(), 2));
        gridRv.setAdapter(adapter);
        swipeRefreshLayout.setColorSchemeColors(Color.BLUE, Color.GREEN, Color.RED, Color.YELLOW);
        swipeRefreshLayout.setRefreshing(false);

        return view;
    }

    @OnClick(R.id.zipLoadBt)
    public void onViewClicked() {
        swipeRefreshLayout.setRefreshing(false);
        unsubscribe();
        disposable = Observable.zip(NetWork.getGankApi().getBeauties(20, 1)
                        .map(GankBeautyResultToItemsMapper.getInstance()),
                NetWork.getZhuangbiApi().search("装逼"),
                new BiFunction<List<Item>, List<ZhuangbiImage>, List<Item>>() {

                    @Override
                    public List<Item> apply(List<Item> gankItems, List<ZhuangbiImage> zhuangbiImages) throws Exception {
                        List<Item> items = new ArrayList<>();
                        for (int j = 0; j < gankItems.size() / 2 && j < zhuangbiImages.size(); j++) {
                            items.add(gankItems.get(j * 2));
                            items.add(gankItems.get(j * 2 + 1));
                            Item zhuangbiItem = new Item();
                            ZhuangbiImage zhuangbiImage = zhuangbiImages.get(j);
                            zhuangbiItem.description = zhuangbiImage.description;
                            zhuangbiItem.imageUrl = zhuangbiImage.image_url;
                            items.add(zhuangbiItem);
                        }
                        return items;
                    }
                }).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<List<Item>>() {
                    @Override
                    public void accept(List<Item> items) throws Exception {
                        swipeRefreshLayout.setRefreshing(false);
                        adapter.setItems(items);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        swipeRefreshLayout.setRefreshing(false);
                        ToastUtils.showSingleToast(getString(R.string.loading_failed));
                    }
                });
    }

    @Override
    protected int getDialogRes() {
        return R.layout.dialog_zip;
    }

    @Override
    protected int getTitleRes() {
        return R.string.title_zip;
    }
}
