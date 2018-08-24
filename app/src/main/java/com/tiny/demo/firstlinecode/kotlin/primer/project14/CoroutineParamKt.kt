package com.tiny.demo.firstlinecode.kotlin.primer.project14

import kotlinx.coroutines.experimental.*
import org.jetbrains.anko.custom.async

/**
 * @Description: 协程参数
 *
 * @Author wangjianzhou@qding.me
 * @Version $version$
 * @Date 2018/8/23 8:39 PM
 */

fun main(args: Array<String>) = runBlocking<Unit> {
    var job = launch {
        println("launch..." + Thread.currentThread().name)
    }

    val job2 = async(CommonPool) {
        delay(500L)
        println("async..." + Thread.currentThread().name)
        return@async
    }

    println("job2的输出：" + job2.await())
    delay(1300L)
}