package com.tiny.demo.firstlinecode.designpattern.talkbig.chapter13;

/**
 * Desc:    构建者类
 * 确定产品由两个部件partA 和 partB组成，
 * 并声明一个得到产品构建后结果的方法getResult
 * Created by tiny on 2017/10/24.
 * Version:
 */

public interface Builder {
    void buildPartA();

    void buildPartB();

    Product getResult();
}
