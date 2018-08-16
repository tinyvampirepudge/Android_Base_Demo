package com.tiny.demo.firstlinecode.kotlin.primer.project10;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.functions.BiFunction;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.functions.Predicate;

/**
 * @Description: rxJava操作符示例——wifi密码组合
 * @Author wangjianzhou@qding.me
 * @Version $version$
 * @Date 2018/8/16 5:28 PM
 */
public class RxJavaDemo {
    public static void main(String[] args) {
        final String[] a = new String[]{"4", "0", "7", "i", "f", "w", "0", "9"};
        final Integer[] index = new Integer[]{5, 3, 9, 4, 8, 3, 1, 9, 2, 1, 7};

        /**
         * flatMap: 做一次型变，将集合类型的数据源拆分单个的数据源，然后返回
         * filter: 对数据进行筛选, 过滤
         * map: 数据类型转换，Integer --> String
         * reduce: 表示一次合并操作，将两个元素合并成一个元素。它一次可以获取到两个元素。
         */
        Observable.just(index)
                .flatMap(new Function<Integer[], ObservableSource<Integer>>() {
                    @Override
                    public ObservableSource<Integer> apply(Integer[] integers) throws Exception {
                        return Observable.fromArray(integers);
                    }
                })
                .filter(new Predicate<Integer>() {
                    @Override
                    public boolean test(Integer integer) throws Exception {
                        return integer < a.length;
                    }
                })
                .map(new Function<Integer, String>() {
                    @Override
                    public String apply(Integer integer) throws Exception {
                        return a[integer];
                    }
                })
                .reduce(new BiFunction<String, String, String>() {
                    @Override
                    public String apply(String s, String s2) throws Exception {
                        return s + s2;
                    }
                })
                .subscribe(new Consumer<String>() {
                    @Override
                    public void accept(String s) throws Exception {
                        System.out.println("密码是:" + s);
                    }
                });
    }
}
