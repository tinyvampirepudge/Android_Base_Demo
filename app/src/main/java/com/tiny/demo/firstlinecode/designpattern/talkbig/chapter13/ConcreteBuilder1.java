package com.tiny.demo.firstlinecode.designpattern.talkbig.chapter13;


/**
 * Desc:    建造具体的两个部件是部件A和部件B。
 * Created by tiny on 2017/10/24.
 * Version:
 */

public class ConcreteBuilder1 implements Builder {
    private Product product = new Product();

    @Override
    public void buildPartA() {
        product.add("部件A");
    }

    @Override
    public void buildPartB() {
        product.add("部件B");
    }

    @Override
    public Product getResult() {
        return product;
    }
}
