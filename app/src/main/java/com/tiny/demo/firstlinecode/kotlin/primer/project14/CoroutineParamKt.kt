package com.tiny.demo.firstlinecode.kotlin.primer.project14

import kotlinx.coroutines.*

/**
 * @Description: 协程参数
 *
 * @Author wangjianzhou@qding.me
 * @Version $version$
 * @Date 2018/8/23 8:39 PM
 */

fun main() = runBlocking<Unit> {
    var job = GlobalScope.launch {
        println("launch..." + Thread.currentThread().name)
    }

    val job2 = async {
        delay(500L)
        println("async..." + Thread.currentThread().name)
        return@async
    }

    println("job2的输出：" + job2.await())
    delay(1300L)
}