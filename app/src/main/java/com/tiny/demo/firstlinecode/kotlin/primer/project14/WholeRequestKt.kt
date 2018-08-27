package com.tiny.demo.firstlinecode.kotlin.primer.project14

import android.widget.TextView
import com.example.tinytongtong.kotlincoroutineapplication.AndroidCommonPool
import kotlinx.coroutines.experimental.android.UI
import kotlinx.coroutines.experimental.async
import kotlinx.coroutines.experimental.launch
import kotlinx.coroutines.experimental.runBlocking
import okhttp3.OkHttpClient
import okhttp3.Request

/**
 * @Description: $desc$
 *
 * @Author wangjianzhou@qding.me
 * @Version $version$
 * @Date 2018/8/24 12:17 PM
 */

private val mOkHttpClient = OkHttpClient()
private val mRequest = Request.Builder().url("https://www.baidu.com").get().build()

fun displayContent(textview: TextView) = runBlocking {
    launch(UI) {
        textview.text = async(AndroidCommonPool) {
            // 不考虑异常的情况
            mOkHttpClient.newCall(mRequest).execute().body()?.string()
        }.await()
    }
}