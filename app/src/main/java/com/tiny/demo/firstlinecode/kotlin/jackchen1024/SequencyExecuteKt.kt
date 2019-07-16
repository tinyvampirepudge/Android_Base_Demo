package com.tiny.demo.firstlinecode.kotlin.jackchen1024

import kotlinx.coroutines.*
import kotlin.system.measureTimeMillis

/**
 * @Description: 挂起函数的组合执行
 *
 * @Author wangjianzhou@qding.me
 * @Version $version$
 * @Date 2018/8/27 10:27 PM
 */
fun main(args: Array<String>) {
    testSequential()
//    testAsync()
}

suspend fun doJob1(): Int {
    println("Doing Job1 ...")
    delay(3000L)
    println("Job1 Done")
    return 10
}

suspend fun doJob2(): Int {
    println("Doing Job2 ...")
    delay(3000L)
    println("Job2 Done")
    return 20
}

fun testSequential() = runBlocking {
    val time = measureTimeMillis {
        val one = doJob1()
        val two = doJob2()
        println("[testSequential] 最终结果: ${one + two}")
    }
    println("[testSequential] Completed in $time ms")
}

fun testAsync() = runBlocking {
    val time = measureTimeMillis {
        val one = async { doJob1() }
        val two = async { doJob2() }
        println("最终结果: ${one.await() + two.await()}")
    }
    println("Completed in $time ms")
}