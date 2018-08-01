package com.tiny.demo.firstlinecode.storage;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;

import com.com.sky.downloader.greendao.DaoSession;
import com.tiny.demo.firstlinecode.R;
import com.tiny.demo.firstlinecode.common.utils.LogUtils;
import com.tiny.demo.firstlinecode.storage.greendao.GreenDaoHelper;
import com.tiny.demo.firstlinecode.storage.greendao.bean.User;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * GreenDao的demo
 */
public class GreedDaoTestActivity extends AppCompatActivity {

    @BindView(R.id.btn_dao_test1)
    Button btnDaoTest1;
    @BindView(R.id.btn_dao_test2)
    Button btnDaoTest2;
    @BindView(R.id.btn_dao_test3)
    Button btnDaoTest3;
    @BindView(R.id.btn_dao_test4)
    Button btnDaoTest4;
    private DaoSession mDaoSession;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_greed_dao_test);
        ButterKnife.bind(this);

        mDaoSession = GreenDaoHelper.getDaoSession();
    }

    private int count = 1;

    @OnClick(R.id.btn_dao_test1)
    public void onBtnDaoTest1Clicked() {
        User user = new User(null, "王蛋蛋 --> " + count, count + 32);
        LogUtils.e("add user --> " + user.toString());
        try {
            mDaoSession.getUserDao().insert(user);
        } catch (Exception e) {
            e.printStackTrace();
            LogUtils.e("插入数据库失败");
        }
        count++;
    }

    @OnClick(R.id.btn_dao_test2)
    public void onBtnDaoTest2Clicked() {
        LogUtils.e("delete id --> " + 1L);
        try {
            mDaoSession.getUserDao().deleteByKey(1L);
        } catch (Exception e) {
            e.printStackTrace();
            LogUtils.e("删除数据库失败");
        }
    }

    @OnClick(R.id.btn_dao_test3)
    public void onBtnDaoTest3Clicked() {
        try {
            List<User> userList = mDaoSession.getUserDao().loadAll();
            if (userList != null && userList.size() > 0) {
                User mUser = userList.get(0);
                mUser.setName("我是王蛋蛋的爸爸");
                mDaoSession.getUserDao().update(mUser);
            }
        } catch (Exception e) {
            e.printStackTrace();
            LogUtils.e("更新数据失败");
        }
    }

    @OnClick(R.id.btn_dao_test4)
    public void onBtnDaoTest4Clicked() {
        try {
            List<User> userList = mDaoSession.getUserDao().loadAll();
            for (User user : userList) {
                LogUtils.e("query: " + user.toString());
            }
        } catch (Exception e) {
            e.printStackTrace();
            LogUtils.e("查询数据失败");
        }
    }
}
