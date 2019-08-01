package com.tiny.demo.firstlinecode.rxjava2.blog3;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.tiny.demo.firstlinecode.R;
import com.tinytongtong.tinyutils.ThreadUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Cancellable;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.functions.Predicate;
import io.reactivex.schedulers.Schedulers;

/**
 * Desc: rxjava2使用实战
 * https://www.jianshu.com/p/031745744bfa
 *
 * @author tiny
 * @date 2018/6/16 下午12:02
 */
public class Rxjava2Blog3EntryActivity extends AppCompatActivity {
    @BindView(R.id.et)
    EditText et;
    @BindView(R.id.btn_search)
    Button btnSearch;
    @BindView(R.id.rv)
    RecyclerView rv;
    private SearchEngine searchEngine;
    private ProgressDialog progressDialog;
    private Disposable disposable;

    public static void actionStart(Context context) {
        Intent starter = new Intent(context, Rxjava2Blog3EntryActivity.class);
        context.startActivity(starter);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rxjava2_blog3_entry);
        ButterKnife.bind(this);
        searchEngine = new SearchEngine();
        LinearLayoutManager llm = new LinearLayoutManager(this);
        rv.setLayoutManager(llm);
    }

    /**
     * 定义了一个方法会返回一个Observable，泛型是String类型。
     * 通过 Observable.create() 创建了一个observable ，并提供了一个ObservableOnSubscribe。
     * 在参数的内部类中覆写了 subscribe() 方法。
     * 给搜索按钮mSearchButton添加了一个点击事件。
     * 当点击事件触发时，调用emitter 的onNext 方法，并传递了当前mQueryEditText的值。
     * 在Java中保持引用容易造成内存泄漏，在不再需要的时候及时移除listeners是一个好习惯，那么这里怎么移除呢？ObservableEmitter 有一个 setCancellable() 方法。通过重写cancel()方法，然后当Observable 被处理的时候这个实现会被回调，比如已经结束或者是所有的观察者都解除了订阅。
     * 通过setOnClickListener(null) 来移除监听。
     * <p>
     * 作者：sheepm
     * 链接：https://www.jianshu.com/p/031745744bfa
     * 來源：简书
     * 简书著作权归作者所有，任何形式的转载都请联系作者获得授权并注明出处。
     *
     * @return
     */
    private Observable<String> createTextChangeObservable() {
        Observable<String> textChangeObservable = Observable.create(new ObservableOnSubscribe<String>() {
            @Override
            public void subscribe(ObservableEmitter<String> emitter) throws Exception {
                TextWatcher watcher = new TextWatcher() {
                    @Override
                    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                    }

                    @Override
                    public void onTextChanged(CharSequence s, int start, int before, int count) {
                        ThreadUtils.logCurrThreadName("et onTextChanged");
                        emitter.onNext(s.toString());
                    }

                    @Override
                    public void afterTextChanged(Editable s) {

                    }
                };
                et.addTextChangedListener(watcher);
                /**
                 * 最后在emitter的setCancellable中去移除这个监听，防止内存泄漏
                 */
                emitter.setCancellable(new Cancellable() {
                    @Override
                    public void cancel() throws Exception {
                        et.removeTextChangedListener(watcher);
                    }
                });
            }
        });

        return textChangeObservable
                //长度小于2时不发送搜索请求。
                .filter(new Predicate<String>() {
                    @Override
                    public boolean test(String s) throws Exception {
                        return s.length() >= 2;
                    }
                })
                //跟filter类似，也是一种拦截侧策略。
                /**
                 * 这个操作符是根据item被发射的时间来进行过滤。每次在一个item被发射后，debounce 会等待一段指定长度的时间，然后才去发射下一个item。
                 如果在这段时间内都没有一个item发生，那么上一个最后的item会被发射出去，这样能保证起码有一个item能被发射成功。
                 */
                .debounce(1000, TimeUnit.MILLISECONDS);

    }

    private Observable<String> createButtonClickObservable() {
        Observable<String> observable = Observable.create(new ObservableOnSubscribe<String>() {
            @Override
            public void subscribe(ObservableEmitter<String> emitter) throws Exception {
                btnSearch.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        ThreadUtils.logCurrThreadName("btnSearch clicked");
                        emitter.onNext(et.getText().toString());
                    }
                });

                emitter.setCancellable(new Cancellable() {
                    @Override
                    public void cancel() throws Exception {
                        //7
                        btnSearch.setOnClickListener(null);
                    }
                });
            }
        });

        return observable;
    }

    @Override
    protected void onStart() {
        super.onStart();
        Observable<String> buttonCLickStream = createButtonClickObservable();
        Observable<String> textChangeStream = createTextChangeObservable();

        Observable<String> searchTextObservable = Observable.merge(textChangeStream, buttonCLickStream);

        disposable = searchTextObservable
                .subscribeOn(AndroidSchedulers.mainThread())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnNext(new Consumer<String>() {
                    @Override
                    public void accept(String s) throws Exception {
                        showProgressBar();
                    }
                })
                .observeOn(Schedulers.io())
                .map(new Function<String, List<String>>() {
                    @Override
                    public List<String> apply(String s) throws Exception {
                        return searchEngine.search(s);
                    }
                })
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<List<String>>() {
                    @Override
                    public void accept(List<String> list) throws Exception {
                        hideProgressBar();
                        showResult(list);
                    }
                });
    }

    @Override
    protected void onStop() {
        super.onStop();
        /**
         * 防止内存泄漏
         */
        if (disposable != null && !disposable.isDisposed()) {
            disposable.dispose();
        }
    }

    private void showResult(List<String> list) {
        ThreadUtils.logCurrThreadName("showResult");
        SearchResultAdapter searchResultAdapter = new SearchResultAdapter(this, list);
        rv.setAdapter(searchResultAdapter);
    }

    private void showProgressBar() {
        if (progressDialog == null) {
            progressDialog = new ProgressDialog(this);
        }
        progressDialog.show();
    }

    private void hideProgressBar() {
        if (progressDialog != null && progressDialog.isShowing()) {
            progressDialog.dismiss();
        }
    }

    private class SearchEngine {
        private List<String> search(String s) {
            ThreadUtils.logCurrThreadName("SearchEngine#search");
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            List<String> result = new ArrayList<>();
            if (TextUtils.isEmpty(s)) {
                return result;
            }
            int size = new Random().nextInt(5) + 5;
            for (int j = 0; j < size; j++) {
                result.add(s + "--" + j);
            }
            return result;
        }
    }
}
