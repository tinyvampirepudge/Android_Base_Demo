package com.tiny.demo.firstlinecode.storage;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.view.View;
import android.widget.Button;

import com.tiny.demo.firstlinecode.base.BaseActivity;
import com.tiny.demo.firstlinecode.R;
import com.tiny.demo.firstlinecode.storage.database.MySQLitebaseHelper;
import com.tinytongtong.tinyutils.LogUtils;

import butterknife.BindView;
import butterknife.OnClick;

public class SQLiteActivity extends BaseActivity {

    @BindView(R.id.btn_sqlite_create)
    Button btnCreate;
    @BindView(R.id.btn_sqlite_add)
    Button btnAdd;
    @BindView(R.id.btn_sqlite_updage)
    Button btnUpdage;
    @BindView(R.id.btn_sqlite_delete)
    Button btnDelete;
    @BindView(R.id.btn_sqlite_query)
    Button btnQuery;
    @BindView(R.id.btn_sql_add)
    Button btnSqlAdd;
    @BindView(R.id.btn_sql_updage)
    Button btnSqlUpdage;
    @BindView(R.id.btn_sql_delete)
    Button btnSqlDelete;
    @BindView(R.id.btn_sql_query)
    Button btnSqlQuery;
    private MySQLitebaseHelper mySQLitebaseHelper;
    private SQLiteDatabase db;

    @Override
    protected int setContentLayout() {
        return R.layout.activity_sqlite;
    }

    @Override
    protected void buildContentView() {
        mySQLitebaseHelper = new MySQLitebaseHelper(mContext, "BookStore.db", null, 1);
        db = mySQLitebaseHelper.getWritableDatabase();
    }

    @Override
    protected void initViewData() {

    }

    @OnClick(R.id.btn_sqlite_create)
    public void onClick() {
        mySQLitebaseHelper.getReadableDatabase();
    }

    @OnClick({R.id.btn_sqlite_add, R.id.btn_sqlite_updage, R.id.btn_sqlite_delete,
            R.id.btn_sqlite_query, R.id.btn_sql_add, R.id.btn_sql_updage, R.id.btn_sql_delete,
            R.id.btn_sql_query})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_sqlite_add:
                addData();
                break;
            case R.id.btn_sqlite_updage:
                updateData();
                break;
            case R.id.btn_sqlite_delete:
                deleteData();
                break;
            case R.id.btn_sqlite_query:
                queryData();
                break;
            case R.id.btn_sql_add:
                db.execSQL("insert into Book (name, author, pages, price) values(?, ?, ?, ?)",
                        new String[]{
                                "tiny's book", "tiny", "600", "20.9"
                        });
                db.execSQL("insert into Book (name, author, pages, price) values(?, ?, ?, ?)",
                        new String[]{
                                "tongtong","tong","250","9.99"
                        });
                break;
            case R.id.btn_sql_updage:
                db.execSQL("update Book set price = ? where name = ?",
                        new String[]{
                                "8.88","tiny's book"
                        });
                break;
            case R.id.btn_sql_delete:
                db.execSQL("delete from Book Where id < ?",
                        new String[]{
                                "4"
                        });
                break;
            case R.id.btn_sql_query:
                Cursor cursor = db.rawQuery("select * from Book",null);
                if (cursor.moveToFirst()) {
                    do {
                        //遍历Cursor对象，取出数据并打印。
                        int id = cursor.getInt(cursor.getColumnIndex("id"));
                        String name = cursor.getString(cursor.getColumnIndex("name"));
                        String author = cursor.getString(cursor.getColumnIndex("author"));
                        int pages = cursor.getInt(cursor.getColumnIndex("pages"));
                        double price = cursor.getDouble(cursor.getColumnIndex("price"));
                        LogUtils.INSTANCE.e("book id is --> " + id);
                        LogUtils.INSTANCE.e("book name is --> " + name);
                        LogUtils.INSTANCE.e("book author is --> " + author);
                        LogUtils.INSTANCE.e("book pages is --> " + pages);
                        LogUtils.INSTANCE.e("book price is --> " + price);
                        LogUtils.INSTANCE.e("------------------------------");
                    } while (cursor.moveToNext());
                }
                cursor.close();
                break;
        }
    }

    private void queryData() {
        SQLiteDatabase database = mySQLitebaseHelper.getWritableDatabase();
        //查询Book表中所有的数据
        Cursor cursor = database.query("Book", null, null, null, null, null, null);
        if (cursor.moveToFirst()) {
            do {
                //遍历Cursor对象，取出数据并打印。
                int id = cursor.getInt(cursor.getColumnIndex("id"));
                String name = cursor.getString(cursor.getColumnIndex("name"));
                String author = cursor.getString(cursor.getColumnIndex("author"));
                int pages = cursor.getInt(cursor.getColumnIndex("pages"));
                double price = cursor.getDouble(cursor.getColumnIndex("price"));
                LogUtils.INSTANCE.e("book id is --> " + id);
                LogUtils.INSTANCE.e("book name is --> " + name);
                LogUtils.INSTANCE.e("book author is --> " + author);
                LogUtils.INSTANCE.e("book pages is --> " + pages);
                LogUtils.INSTANCE.e("book price is --> " + price);
                LogUtils.INSTANCE.e("------------------------------");
            } while (cursor.moveToNext());
        }
        cursor.close();
    }

    private void deleteData() {
        SQLiteDatabase database = mySQLitebaseHelper.getWritableDatabase();
        database.delete("Book", "pages > ?", new String[]{
                "500"
        });
    }

    private void updateData() {
        SQLiteDatabase database = mySQLitebaseHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("price", 10.99);
        database.update("Book", values, "name = ?", new String[]{
                "The Da Vinci Code"
        });
    }

    private void addData() {
        SQLiteDatabase database = mySQLitebaseHelper.getReadableDatabase();
        ContentValues values = new ContentValues();
        //开始组装第一条数据
        values.put("name", "The Da Vinci Code");
        values.put("author", "Dan Brown");
        values.put("pages", 454);
        values.put("price", 16.96);
        database.insert("Book", null, values);
        values.clear();
        //第二条数据
        values.put("name", "The Lost Symbol");
        values.put("author", "Dan Brown");
        values.put("pages", 510);
        values.put("price", 19.95);
        database.insert("Book", null, values);
    }
}
