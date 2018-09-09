package com.tiny.demo.firstlinecode.storage.database

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.tiny.demo.firstlinecode.R

/**
 * @Description: 数据库升级代码模板
 *
 * @Author wangjianzhou@qding.me
 * @Date 2018/9/7 10:05 PM
 * @Version TODO
 */
class SqliteUpgradeActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sqlite_upgrade)

        var sqliteHelper = MySqliteHelper(this, 5);
        var db = sqliteHelper.writableDatabase

        db.execSQL("insert into Test (name, author, pages, price) values(?, ?, ?, ?)",
                arrayOf("tiny's book", "tiny", "600", "20.9"))
        db.execSQL("insert into Test (name, author, pages, price) values(?, ?, ?, ?)",
                arrayOf("tongtong", "tong", "250", "9.99"))


    }
}
