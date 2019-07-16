package com.example.tinytongtong.kotlincoroutineapplication

import android.os.AsyncTask
import kotlinx.coroutines.CoroutineDispatcher
import kotlin.coroutines.CoroutineContext

/**
 * @Description: 服用AsyncTask的线程池
 *
 * @Author wangjianzhou@qding.me
 * @Version $version$
 * @Date 2018/8/24 12:20 PM
 */

object AndroidCommonPool : CoroutineDispatcher() {
    override fun dispatch(context: CoroutineContext, block: Runnable) {
        AsyncTask.THREAD_POOL_EXECUTOR.execute(block)
    }

}