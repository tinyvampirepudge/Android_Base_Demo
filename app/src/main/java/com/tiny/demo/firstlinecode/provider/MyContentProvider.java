package com.tiny.demo.firstlinecode.provider;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.net.Uri;
import android.support.annotation.Nullable;

import com.tiny.demo.firstlinecode.R;
import com.tiny.demo.firstlinecode.app.FLCApplication;

/**
 * Created by 87959 on 2017/5/7.
 */

public class MyContentProvider extends ContentProvider {
    private static String authority;
    private static final int TABLE1_DIR = 0;
    private static final int TABLE1_ITEM = 1;
    private static final int TABLE2_DIR = 2;
    private static final int TABLE2_ITEM = 3;
    private static UriMatcher uriMatcher;

    static {
        authority = FLCApplication.instance().getResources().getString(R.string.data_base_provider);
        uriMatcher = new UriMatcher(UriMatcher.NO_MATCH);
        uriMatcher.addURI(authority, "table1", TABLE1_DIR);
        uriMatcher.addURI(authority, "table1/#", TABLE1_ITEM);
        uriMatcher.addURI(authority, "table2", TABLE2_DIR);
        uriMatcher.addURI(authority, "table2/#", TABLE2_ITEM);
    }

    @Override
    public boolean onCreate() {
//        authority = MyApplication.instance().getResources().getString(R.string.data_base_provider);
//        uriMatcher = new UriMatcher(UriMatcher.NO_MATCH);
//        uriMatcher.addURI(authority, "table1", TABLE1_DIR);
//        uriMatcher.addURI(authority, "table1/#", TABLE1_ITEM);
//        uriMatcher.addURI(authority, "table2", TABLE2_DIR);
//        uriMatcher.addURI(authority, "table2/#", TABLE2_ITEM);
        return false;
    }

    @Nullable
    @Override
    public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder) {
        switch (uriMatcher.match(uri)) {
            case TABLE1_DIR:
                //查询table1表中的所有数据
                break;
            case TABLE1_ITEM:
                //查询table1中的单条数据
                break;
            case TABLE2_DIR:
                //查询table2表中的所有数据
                break;
            case TABLE2_ITEM:
                //查询你table2中的单条数据
                break;
        }
        return null;
    }

    @Nullable
    @Override
    public String getType(Uri uri) {
        switch (uriMatcher.match(uri)) {
            case TABLE1_DIR:
                return "vnd.android.cursor.id/vnd." + authority + ".table1";
            case TABLE1_ITEM:
                return "vnd.android.cursor.item/vnd." + authority + ".table1";
            case TABLE2_DIR:
                return "vnd.android.cursor.id/vnd." + authority + ".table2";
            case TABLE2_ITEM:
                return "vnd.android.cursor.item/vnd." + authority + ".table2";
            default:
                break;
        }
        return null;
    }

    @Nullable
    @Override
    public Uri insert(Uri uri, ContentValues values) {

        return null;
    }

    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        return 0;
    }

    @Override
    public int update(Uri uri, ContentValues values, String selection, String[] selectionArgs) {
        return 0;
    }
}
