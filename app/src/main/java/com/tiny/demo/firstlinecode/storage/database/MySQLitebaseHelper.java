package com.tiny.demo.firstlinecode.storage.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.tiny.demo.firstlinecode.common.utils.LogUtils;

/**
 * Created by 87959 on 2017/3/28.
 */

public class MySQLitebaseHelper extends SQLiteOpenHelper {

    public static final String CREATE_BOOK = "create table Book (" +
            "id integer primary key autoincrement, " +
            "author text, " +
            "price real, " +
            "pages integer, " +
            "name text)";

    public static final String CREATE_CATEGORY = "create table Category (" +
            "id integer primary key autoincrement, " +
            "category_name text, " +
            "category_price integer)";

    private Context mContext;

    public MySQLitebaseHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
        mContext = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        LogUtils.e("MySQLitebaseHelper onCreate");
        db.execSQL(CREATE_BOOK);
        db.execSQL(CREATE_CATEGORY);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        LogUtils.e("MySQLitebaseHelper onUpgrade");
        db.execSQL("drop table if exists Book");
        db.execSQL("drop table if exists Category");
        onCreate(db);
    }
}
