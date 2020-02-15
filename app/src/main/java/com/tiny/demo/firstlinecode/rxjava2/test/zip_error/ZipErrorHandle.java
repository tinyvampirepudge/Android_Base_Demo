package com.tiny.demo.firstlinecode.rxjava2.test.zip_error;

import android.util.Log;

import java.util.ArrayList;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.BiFunction;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

/**
 * @Description: zip操作符的错误处理
 * https://stackoverflow.com/questions/41342844/rxjava-how-to-handle-error-with-zip-operator
 * @Author wangjianzhou@qding.me
 * @Date 2020-02-15 22:36
 * @Version
 */
public class ZipErrorHandle {

    private static final String TAG = ZipErrorHandle.class.getSimpleName();

    public static void main(String[] args) {
        Observable<ZipOneBean> responseOneObservable = Observable.create(new ObservableOnSubscribe<ZipOneBean>() {
            @Override
            public void subscribe(ObservableEmitter<ZipOneBean> emitter) throws Exception {
                ZipOneBean zipOneBean = new ZipOneBean();
                zipOneBean.setName("zipOneBean");
                emitter.onNext(zipOneBean);
                emitter.onComplete();
            }
        }).onErrorReturn(new Function<Throwable, ZipOneBean>() {
            @Override
            public ZipOneBean apply(Throwable throwable) throws Exception {
                ZipOneBean zipOneBean = new ZipOneBean();
                zipOneBean.setName("大哥我错了，我是zipOneBean");
                return zipOneBean;
            }
        })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());

        Observable<ZipTwoBean> responseTwoObservable = Observable.create(new ObservableOnSubscribe<ZipTwoBean>() {
            @Override
            public void subscribe(ObservableEmitter<ZipTwoBean> emitter) throws Exception {
                ZipTwoBean zipTwoBean = new ZipTwoBean();
                zipTwoBean.setName("zipTwoBean");
                emitter.onError(new RuntimeException("阿西吧"));
            }
        }).onErrorReturn(new Function<Throwable, ZipTwoBean>() {
            @Override
            public ZipTwoBean apply(Throwable throwable) throws Exception {
                ZipTwoBean zipTwoBean = new ZipTwoBean();
                zipTwoBean.setName("大哥我错了，我是zipTwoBean");
                return zipTwoBean;
            }
        })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());

        Observable.zip(responseOneObservable, responseTwoObservable, new BiFunction<ZipOneBean, ZipTwoBean, ArrayList<ZipBaseData>>() {
            @Override
            public ArrayList<ZipBaseData> apply(ZipOneBean zipOneBean, ZipTwoBean zipTwoBean) throws Exception {
                ArrayList<ZipBaseData> testDataList = new ArrayList();
                // Add test data from response responseOne & responseTwo
                testDataList.add(zipOneBean);
                testDataList.add(zipTwoBean);
                return testDataList;
            }
        }).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<ArrayList<ZipBaseData>>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        Log.e(TAG, "onSubscribe");
                    }

                    @Override
                    public void onNext(ArrayList<ZipBaseData> zipBaseData) {
                        Log.e(TAG, "onNext zipBaseData:" + zipBaseData);
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.e(TAG, "onError e:" + e.getMessage());
                    }

                    @Override
                    public void onComplete() {
                        Log.e(TAG, "onComplete");
                    }
                });
    }
}
