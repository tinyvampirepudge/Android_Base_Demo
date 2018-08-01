package com.tiny.demo.firstlinecode.storage.greendao;

import android.content.Context;

import com.com.sky.downloader.greendao.DaoMaster;
import com.com.sky.downloader.greendao.DaoSession;

import org.greenrobot.greendao.database.Database;

/**
 * Desc:
 * Created by tiny on 2017/12/27.
 * Version:
 */

public class GreenDaoHelper {
    public static final String DATA_BASE_NAME = "maolegemi";
    private static DaoSession mDaoSession;

    private GreenDaoHelper() {
    }

    public static void setupDataBase(Context context) {
        DaoMaster.DevOpenHelper openHelper = new DaoMaster.DevOpenHelper(context, DATA_BASE_NAME);
        Database db = openHelper.getWritableDb();
        DaoMaster daoMaster = new DaoMaster(db);
        mDaoSession = daoMaster.newSession();
    }

    public static DaoSession getDaoSession() {
        return mDaoSession;
    }

}
