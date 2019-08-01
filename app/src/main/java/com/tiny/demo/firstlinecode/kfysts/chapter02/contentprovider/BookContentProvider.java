package com.tiny.demo.firstlinecode.kfysts.chapter02.contentprovider;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.Context;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.Process;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.tiny.demo.firstlinecode.common.utils.LogUtils;
import com.tiny.demo.firstlinecode.common.utils.ProcessUtil;
import com.tinytongtong.tinyutils.ThreadUtils;

/**
 * Desc:
 *
 * @author tiny
 * @date 2018/3/9 上午12:42
 */

public class BookContentProvider extends ContentProvider {
    public static final String TAG = "ContentProvider";

    /**
     * ContentProvider通过Uri来区分外界要访问的数据集合，在本例中支持外界对BOokProvider中的book表和user表进行访问，
     * 为了知道外界要访问的是哪个表，需要为它们定义单独的Uri和Uri_Code，并将Uri和对应的Uri_Code相关联，我们可以使用
     * UriMatcher的addURI方法将Uri和Uri_Code关联到一起。这样，当外界请求访问BookProvider时，我们就可以根据Uri来
     * 得到Uri_Code，有了Uri_Code我们就可以知道外界想要访问哪个表，然后就可以进行相应的数据操作了。
     */
    /**
     * 需要注意的是，query、update、insert、delete四大方法是存在多线程并发访问的，因此方法内部要做好线程同步。
     * 在本例中，由于采用的是SQLite并且只有一个SQLteDataBase的连接，所以可以正确应对多线程的情况。
     * 具体原因是SQLiteDataBase内部对数据库的操作是有同步处理的，但是通过多个SQLiteDataBase对象来操作数据库就
     * 无法保证线程同步，因为SQLiteDataBase对象之间无法进行线程同步。
     * 如果ContentProvider的底层数据集是一块内存的话，比如是List，在这种情况下同List的遍历、插入、删除操作就需要
     * 进行线程同步，否则就会引发并发错误，这点是尤其需要注意的。
     */
    /**
     * 跟AndroidManifest.xml中配置保持一致
     */
    public static final String AUTHORITY = "com.tiny.demo.firstlinecode.book.provider";
    /**
     * 分别为book表和user表置顶Uri.
     */
    public static final Uri BOOK_CONTENT_URI = Uri.parse("content://" + AUTHORITY + "/book");
    public static final Uri ISER_CONTENT_URI = Uri.parse("content://" + AUTHORITY + "/user");

    public static final int BOOK_URI_CODE = 0;
    public static final int USER_URI_CODE = 1;
    private static final UriMatcher sUriMatcher = new UriMatcher(UriMatcher.NO_MATCH);

    /**
     * 将Uri和Uri_Code关联起来。
     */
    static {
        sUriMatcher.addURI(AUTHORITY, "book", BOOK_URI_CODE);
        sUriMatcher.addURI(AUTHORITY, "user", USER_URI_CODE);
    }

    private Context mContext;
    private SQLiteDatabase mDb;

    /**
     * 根据Uri取出对应的Uri_Code。
     *
     * @param uri
     * @return
     */
    private String getTableName(Uri uri) {
        String tableName = null;
        switch (sUriMatcher.match(uri)) {
            case BOOK_URI_CODE:
                tableName = DBOpenHelper.BOOK_TABLE_NAME;
                break;
            case USER_URI_CODE:
                tableName = DBOpenHelper.USER_TABLE_NAME;
                break;
            default:
                break;
        }
        return tableName;
    }

    @Override
    public boolean onCreate() {
        //进程名称
        String processName = ProcessUtil.getProcessNameByCtx(getContext(), Process.myPid());
        ThreadUtils.logCurrThreadName(TAG + " processName:" + processName);
        //主线程
        ThreadUtils.logCurrThreadName(TAG + " onCreate");
        mContext = getContext();
        //ContentProvider创建时，初始化数据库。注意：这里仅仅是为了演示，实际使用中不推荐在主线程中进行耗时的数据库操作
        initProvider();
        return true;
    }

    private void initProvider() {
        mDb = new DBOpenHelper(mContext).getWritableDatabase();
        mDb.execSQL("delete from " + DBOpenHelper.BOOK_TABLE_NAME);
        mDb.execSQL("delete from " + DBOpenHelper.USER_TABLE_NAME);
        mDb.execSQL("insert into book values(3,'Android');");
        mDb.execSQL("insert into book values(4,'IOS');");
        mDb.execSQL("insert into book values(5,'Html5');");
        mDb.execSQL("insert into user values(1,'Jake',1);");
        mDb.execSQL("insert into user values(2,'jasmine',0);");
    }

    @Nullable
    @Override
    public Cursor query(@NonNull Uri uri, @Nullable String[] projection, @Nullable String selection, @Nullable String[] selectionArgs, @Nullable String sortOrder) {
        // 运行在Binder线程中。
        // ContentProvider query: sub Thread,name --> Binder:5991_2
        ThreadUtils.logCurrThreadName(TAG + " query");
        String table = getTableName(uri);
        if (table == null) {
            throw new IllegalArgumentException("Unsupported URI: " + uri);
        }
        Cursor result = mDb.query(table, projection, selection, selectionArgs, null, null, sortOrder, null);
        return result;
    }

    @Nullable
    @Override
    public String getType(@NonNull Uri uri) {
        LogUtils.e(TAG, "getType");
        return null;
    }

    @Nullable
    @Override
    public Uri insert(@NonNull Uri uri, @Nullable ContentValues values) {
        LogUtils.e(TAG, "insert");
        String table = getTableName(uri);
        if (table == null) {
            throw new IllegalArgumentException("Unsupported Uri: " + uri);
        }
        long result = mDb.insert(table, null, values);
        //通知数据库刷新
        mContext.getContentResolver().notifyChange(uri, null);
        return uri;
    }

    @Override
    public int delete(@NonNull Uri uri, @Nullable String selection, @Nullable String[] selectionArgs) {
        LogUtils.e(TAG, "delete");
        String table = getTableName(uri);
        if (table == null) {
            throw new IllegalArgumentException("Unsupported Uri: " + uri);
        }
        int result = mDb.delete(table, selection, selectionArgs);
        if (result > 0) {
            mContext.getContentResolver().notifyChange(uri, null);
        }
        return result;
    }

    @Override
    public int update(@NonNull Uri uri, @Nullable ContentValues values, @Nullable String selection, @Nullable String[] selectionArgs) {
        LogUtils.e(TAG, "update");
        String table = getTableName(uri);
        if (table == null) {
            throw new IllegalArgumentException("Unsupported Uri: " + uri);
        }
        int result = mDb.update(table, values, selection, selectionArgs);
        if (result > 0) {
            mContext.getContentResolver().notifyChange(uri, null);
        }
        return result;
    }
}
