package com.tiny.demo.firstlinecode.provider;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;

import com.tiny.demo.firstlinecode.R;
import com.tiny.demo.firstlinecode.app.FLCApplication;
import com.tiny.demo.firstlinecode.storage.database.MySQLitebaseHelper;

public class DataBaseProvider extends ContentProvider {
    public static final int BOOK_DIR = 0;
    public static final int BOOK_ITEM = 1;
    public static final int CATEGORY_DIR = 2;
    public static final int CATEGORY_ITEM = 3;
    public static final String BOOK_TABLE_NAME = "Book";
    public static final String CATEGORY_TABLE_NAME = "Category";
    public static String AUTHORITY;
    public static final String PREFIX_DIR = "vnd.android.cursor.dir/vnd.";
    public static final String PREFIX_ITEM = "vnd.android.cursor.item/vnd.";
    public static final String DOT = ".";
    public static UriMatcher uriMatcher;

    private MySQLitebaseHelper mySQLitebaseHelper;

    static {
        AUTHORITY = FLCApplication.instance().getResources().getString(R.string.data_base_provider);
        uriMatcher = new UriMatcher(UriMatcher.NO_MATCH);
        uriMatcher.addURI(AUTHORITY, "book", BOOK_DIR);
        uriMatcher.addURI(AUTHORITY, "book/#", BOOK_ITEM);
        uriMatcher.addURI(AUTHORITY, "category", CATEGORY_DIR);
        uriMatcher.addURI(AUTHORITY, "category/#", CATEGORY_ITEM);
    }

    public DataBaseProvider() {

    }

    @Override
    public boolean onCreate() {
        // Implement this to initialize your content provider on startup.
//        AUTHORITY = MyApplication.instance().getResources().getString(R.string.data_base_provider);
//        uriMatcher = new UriMatcher(UriMatcher.NO_MATCH);
//        uriMatcher.addURI(AUTHORITY, "book", BOOK_DIR);
//        uriMatcher.addURI(AUTHORITY, "book/#", BOOK_ITEM);
//        uriMatcher.addURI(AUTHORITY, "category", CATEGORY_DIR);
//        uriMatcher.addURI(AUTHORITY, "category/#", CATEGORY_ITEM);
//        mySQLitebaseHelper = new MySQLitebaseHelper(getContext(), "BookStore.db", null, 2);
        return true;
    }

    @Override
    public Uri insert(Uri uri, ContentValues values) {
        // Implement this to handle requests to insert a new row.
//        throw new UnsupportedOperationException("Not yet implemented");
        //添加数据
        SQLiteDatabase db = mySQLitebaseHelper.getWritableDatabase();
        Uri uriReturn = null;
        switch (uriMatcher.match(uri)) {
            case BOOK_DIR:
            case BOOK_ITEM:
                long newBookId = db.insert(BOOK_TABLE_NAME, null, values);
                uriReturn = Uri.parse("content://" + AUTHORITY + "/book/" + newBookId);
                break;
            case CATEGORY_DIR:
            case CATEGORY_ITEM:
                long newCategoryId = db.insert(CATEGORY_TABLE_NAME, null, values);
                uriReturn = Uri.parse("content://" + AUTHORITY + "/category/" + newCategoryId);
                break;
            default:
                break;
        }
        return uriReturn;
    }

    @Override
    public Cursor query(Uri uri, String[] projection, String selection,
                        String[] selectionArgs, String sortOrder) {
        // Implement this to handle query requests from clients.
//        throw new UnsupportedOperationException("Not yet implemented");
        //查询数据
        SQLiteDatabase database = mySQLitebaseHelper.getReadableDatabase();
        Cursor cursor = null;
        switch (uriMatcher.match(uri)) {
            case BOOK_DIR:
                cursor = database.query(BOOK_TABLE_NAME, projection, selection, selectionArgs, null, null, sortOrder);
                break;
            case BOOK_ITEM:
                String bookId = uri.getPathSegments().get(1);
                cursor = database.query(BOOK_TABLE_NAME, projection, "id = ?", new String[]{bookId}, null, null, sortOrder);
                break;
            case CATEGORY_DIR:
                cursor = database.query(CATEGORY_TABLE_NAME, projection, selection, selectionArgs, null, null, sortOrder);
                break;
            case CATEGORY_ITEM:
                String categoryId = uri.getPathSegments().get(1);
                cursor = database.query(CATEGORY_TABLE_NAME, projection, "id = ?", new String[]{categoryId}, null, null, sortOrder);
                break;
            default:
                break;
        }
        return cursor;
    }

    @Override
    public int update(Uri uri, ContentValues values, String selection,
                      String[] selectionArgs) {
        // Implement this to handle requests to update one or more rows.
//        throw new UnsupportedOperationException("Not yet implemented");
        //更新数据
        SQLiteDatabase db = mySQLitebaseHelper.getWritableDatabase();
        int updatedRows = 0;
        switch (uriMatcher.match(uri)) {
            case BOOK_DIR:
                updatedRows = db.update(BOOK_TABLE_NAME, values, selection, selectionArgs);
                break;
            case BOOK_ITEM:
                String bookId = uri.getPathSegments().get(1);
                updatedRows = db.update(BOOK_TABLE_NAME, values, "id = ?", new String[]{bookId});
                break;
            case CATEGORY_DIR:
                updatedRows = db.update(CATEGORY_TABLE_NAME, values, selection, selectionArgs);
                break;
            case CATEGORY_ITEM:
                String categoryId = uri.getPathSegments().get(1);
                updatedRows = db.update(CATEGORY_TABLE_NAME, values, "id = ?", new String[]{categoryId});
                break;
            default:
                break;
        }
        return updatedRows;
    }

    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        // Implement this to handle requests to delete one or more rows.
//        throw new UnsupportedOperationException("Not yet implemented");
        //删除数据
        SQLiteDatabase db = mySQLitebaseHelper.getWritableDatabase();
        int deleteRows = 0;
        switch (uriMatcher.match(uri)) {
            case BOOK_DIR:
                deleteRows = db.delete(BOOK_TABLE_NAME, selection, selectionArgs);
                break;
            case BOOK_ITEM:
                String bookId = uri.getPathSegments().get(1);
                deleteRows = db.delete(BOOK_TABLE_NAME, "id = ?", new String[]{bookId});
                break;
            case CATEGORY_DIR:
                deleteRows = db.delete(CATEGORY_TABLE_NAME, selection, selectionArgs);
                break;
            case CATEGORY_ITEM:
                String categoryId = uri.getPathSegments().get(1);
                deleteRows = db.delete(CATEGORY_TABLE_NAME, "id = ?", new String[]{categoryId});
                break;
            default:
                break;
        }
        return deleteRows;
    }

    @Override
    public String getType(Uri uri) {
        // Implement this to handle requests for the MIME type of the data
        // at the given URI.
//        throw new UnsupportedOperationException("Not yet implemented");
        ///////////////////////////////////////////////////////////////////////////
        // getType 的规则：
        // 必须以vnd开头
        // 如果内容uri以路径结尾，则前接android.cursor.dir/,
        // 如果内容uri以id结尾，则前接android.cursor.item/,
        // 最后节省vnd.<authority>.<path>
        ///////////////////////////////////////////////////////////////////////////
        switch (uriMatcher.match(uri)) {
            case BOOK_DIR:
                return PREFIX_DIR + AUTHORITY + DOT + "book";
            case BOOK_ITEM:
                return PREFIX_ITEM + AUTHORITY + DOT + "book";
            case CATEGORY_DIR:
                return PREFIX_DIR + AUTHORITY + DOT + "category";
            case CATEGORY_ITEM:
                return PREFIX_ITEM + AUTHORITY + DOT + "category";
            default:
                break;
        }
        return null;
    }
}
