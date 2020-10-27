package com.tiny.demo.firstlinecode.kfysts.chapter02.contentprovider;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.tiny.demo.firstlinecode.R;
import com.tinytongtong.tinyutils.LogUtils;
import com.tiny.demo.firstlinecode.kfysts.chapter02.User;
import com.tiny.demo.firstlinecode.kfysts.chapter02.aidl.Book;

import java.util.Random;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Desc:    通过ContentProvider进行进程间通信
 *
 * @author tiny
 * @date 2018/3/9 上午12:39
 */
public class ContentProviderTestActivity extends AppCompatActivity {
    public static final String TAG = "ProviderActivity";

    @BindView(R.id.btn_check_book)
    Button btnCheckBook;
    @BindView(R.id.btn_check_user)
    Button btnCheckUser;

    public static void actionStart(Context context) {
        Intent starter = new Intent(context, ContentProviderTestActivity.class);
        context.startActivity(starter);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_content_provider_test);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.btn_check_book, R.id.btn_check_user})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_check_book:
                operateBookTable();
                break;
            case R.id.btn_check_user:
                operateUserTable();
                break;
        }
    }

    private void operateBookTable() {
        Uri bookUri = Uri.parse("content://com.tiny.demo.firstlinecode.book.provider/book");
        ContentValues values = new ContentValues();
        values.put("_id", new Random().nextInt());
        values.put("name", "程序设计的艺术");
        getContentResolver().insert(bookUri, values);

        Cursor bookCursor = getContentResolver().query(bookUri, new String[]{"_id", "name"}, null, null, null);
        while (bookCursor.moveToNext()) {
            Book book = new Book();
            book.setBookId(bookCursor.getInt(0));
            book.setBookName(bookCursor.getString(1));
            LogUtils.INSTANCE.e(TAG, "query book:" + book.toString());
        }
        bookCursor.close();
    }

    private void operateUserTable() {
        Uri userUri = Uri.parse("content://com.tiny.demo.firstlinecode.book.provider/user");
        Cursor userCursor = getContentResolver().query(userUri, new String[]{"_id", "name", "sex"}, null, null, null);
        while (userCursor.moveToNext()) {
            User user = new User();
            user.setAge(userCursor.getInt(0));
            user.setName(userCursor.getString(1));
            user.setGender(userCursor.getInt(2) == 1);
            LogUtils.INSTANCE.e(TAG, user.toString());
        }
        userCursor.close();
    }
}
