package com.tiny.demo.firstlinecode.javareference;

import android.widget.Button;

import com.tiny.demo.firstlinecode.R;
import com.tiny.demo.firstlinecode.base.BaseActivity;
import com.tiny.demo.firstlinecode.common.utils.LogUtils;
import com.tiny.demo.firstlinecode.javareference.singleton.DataSourceEnum;
import com.tiny.demo.firstlinecode.javareference.singleton.Singleton;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 内部内实现单例
 * https://www.cnblogs.com/lchzls/p/7071891.html
 */
public class SingletonActivity extends BaseActivity {

    @BindView(R.id.btn_singleton_static_inner_class)
    Button btnSingletonStaticInnerClass;
    @BindView(R.id.btn_singleton_enum)
    Button btnSingletonEnum;

    @Override
    protected int setContentLayout() {
        return R.layout.activity_singleton2;
    }

    @Override
    protected void buildContentView() {

    }

    @Override
    protected void initViewData() {

    }

    @OnClick(R.id.btn_singleton_static_inner_class)
    public void onViewClicked() {
        Singleton.getInstance().showToast();
    }


    @OnClick(R.id.btn_singleton_enum)
    public void onViewEnumClicked() {
        LogUtils.e("" + DataSourceEnum.DATASOURCE.getConnection().hashCode());
        LogUtils.e("" + DataSourceEnum.DATASOURCE.getConnection().hashCode());
        LogUtils.e("" + DataSourceEnum.DATASOURCE.getConnection().hashCode());
    }
}
