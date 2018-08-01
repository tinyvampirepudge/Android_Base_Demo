package com.tiny.demo.firstlinecode.designpattern.chapter13;

import com.tiny.demo.firstlinecode.common.utils.LogUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Desc:    产品类，由多个部件组成
 * Created by tiny on 2017/10/23.
 * Version:
 */

public class Product {
    List<String> parts = new ArrayList<>();

    /**
     * 添加产品部件
     *
     * @param part
     */
    public void add(String part) {
        parts.add(part);
    }

    public void show() {
        LogUtils.e("Product 创建 ------");
        //列举所有的产品部件
        for (String part : parts) {
            LogUtils.e("part --> " + part);
        }
    }
}
