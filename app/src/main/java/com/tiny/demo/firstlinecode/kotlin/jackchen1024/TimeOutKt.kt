package com.tiny.demo.firstlinecode.kotlin.jackchen1024

import kotlinx.coroutines.TimeoutCancellationException
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withTimeout

/**
 * @Description: 设置协程超时时间
 *
 * @Author wangjianzhou@qding.me
 * @Version $version$
 * @Date 2018/8/27 6:43 PM
 */

fun main(args: Array<String>) {
    try {
        testTimeouts()
    } catch (e: TimeoutCancellationException) {
        println("I am timed out!")
    }
}

fun testTimeouts() = runBlocking {
    withTimeout(3000L) {
        repeat(100) { i ->
            println("I'm sleeping $i ...")
            delay(500)
        }
    }
}