package com.tiny.demo.firstlinecode.rxjava2.blog4.module.map_2;

import com.tinytongtong.tinyutils.LogUtils;
import com.tiny.demo.firstlinecode.rxjava2.blog4.model.GankBeauty;
import com.tiny.demo.firstlinecode.rxjava2.blog4.model.GankBeautyResult;
import com.tiny.demo.firstlinecode.rxjava2.blog4.model.Item;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import io.reactivex.functions.Function;

/**
 * Desc:    map操作
 * GankBeautyResult --> List<Item>
 *
 * @author tiny
 * @date 2018/6/17 下午11:40
 */
public class GankBeautyResultToItemsMapper implements Function<GankBeautyResult, List<Item>> {
    private static GankBeautyResultToItemsMapper instance = new GankBeautyResultToItemsMapper();

    private GankBeautyResultToItemsMapper() {
    }

    public static GankBeautyResultToItemsMapper getInstance() {
        return instance;
    }

    @Override
    public List<Item> apply(GankBeautyResult gankBeautyResult) throws Exception {
        List<GankBeauty> gankBeauties = gankBeautyResult.beauties;
        List<Item> items = new ArrayList<>(gankBeauties.size());
        SimpleDateFormat inputFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SS'Z'");
        SimpleDateFormat outputFormat = new SimpleDateFormat("yy/MM/dd HH:mm:ss");
        for (GankBeauty gank : gankBeauties) {
            Item item = new Item();
            try {
                Date date = inputFormat.parse(gank.createdAt);
                item.description = outputFormat.format(date);
                LogUtils.INSTANCE.e("src date --> " + gank.createdAt);
                LogUtils.INSTANCE.e("dst date --> " + item.description);
            } catch (ParseException e) {
                e.printStackTrace();
                item.description = "unknown";
            }
            item.imageUrl = gank.url;
            items.add(item);
        }
        return items;
    }
}
