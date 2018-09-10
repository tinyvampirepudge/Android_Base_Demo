package com.tiny.demo.firstlinecode.storage.database

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.util.Log

/**
 * @Description: TODO
 *
 * @Author wangjianzhou@qding.me
 * @Date 2018/9/7 10:07 PM
 * @Version TODO
 */
// 表的名字
const val sqlite_name = "MySqliteHelper.db"

internal class MySqliteHelper(context: Context, version: Int) : SQLiteOpenHelper(context, sqlite_name, null, version) {
    val TAG = "MySqliteHelper"

    val TABLE_NAME = "Test"

    //  创建语句
    val sqlCreate = "create table $TABLE_NAME (" +
            "id integer primary key autoincrement, " +
            "author text, " +
            "price real, " +
            "pages integer, " +
            "name text, " +
            "ver2 text, " +
            "ver3 text, " +
            "ver4 text, " +
            "ver5 text)"

    // 新增字段，数据库版本升级为2
    // sqlite 不支持一次增加多列，只能一次增加一列。
    val sqlsV2 = arrayOf("alter table ${TABLE_NAME} add column age VARCHAR(255)",
            "alter table ${TABLE_NAME} add column gender VARCHAR(255)")

    override fun onCreate(db: SQLiteDatabase?) {
        Log.e(TAG, "onCreate")
        db?.execSQL(sqlCreate)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        Log.e(TAG, "onUpgrade oldVersion:$oldVersion newVersion:$newVersion")
        // 1 升级到 2
        if (oldVersion < 2) {
            Log.e(TAG, "onUpgrade 1~2")
            sqlsV2.forEach {
                db?.execSQL(it)
            }
        }

        // 2 升级到 3
        if (oldVersion < 3) {
            Log.e(TAG, "onUpgrade 2~3")
        }

        // 3 升级到 4
        if (oldVersion < 4) {
            Log.e(TAG, "onUpgrade 3~4")
        }

        // 4 升级到 5
        if (oldVersion < 5) {
            Log.e(TAG, "onUpgrade 4~5")
        }
    }


}