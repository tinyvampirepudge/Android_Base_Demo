package com.tiny.demo.firstlinecode.kotlin.primer.project14

import kotlinx.coroutines.experimental.delay
import kotlinx.coroutines.experimental.launch
import kotlinx.coroutines.experimental.runBlocking

/**
 * @Description: 协程
 *
 * @Author wangjianzhou@qding.me
 * @Version $version$
 * @Date 2018/8/23 5:13 PM
 */


fun main(args: Array<String>) = runBlocking<Unit> {
    val job = launch {
        repeat(1000) { i ->
            println("挂起中 $i ...")
            delay(500L)
        }
    }
    // 将当前协程挂起
    delay(1300L)
    println("main:: 主线程等待中")
    job.cancel()
    job.join()  // 这个协程的join是不起作用的，因为他已经启动、取消了。
    println("main:: 即将完成退出")
}