package com.tiny.demo.firstlinecode.kotlin.jackchen1024

import kotlinx.coroutines.experimental.*

/**
 * @Description: 在Finally中的协程代码
 *
 * @Author wangjianzhou@qding.me
 * @Version $version$
 * @Date 2018/8/27 6:19 PM
 */

fun main(args: Array<String>) {
//    finallyCacheDemo()
//    finallyCacheDemo1()
    finallyCacheDemo2()
}

// try finally 语句
fun finallyCacheDemo() = runBlocking {
    val job = launch(CommonPool) {
        try {
            repeat(1000) { i ->
                println("I'm sleeping $i ...")
                delay(500L)
            }
        } finally {
            println("I'm running finally")
        }
    }
    delay(2000L)
    println("Before cancel, Job is alive: ${job.isActive}  Job is completed: ${job.isCompleted}")
    job.cancel()
    println("After cancel, Job is alive: ${job.isActive}  Job is completed: ${job.isCompleted}")
    delay(2000L)
    println("main: Now I can quit.")
}

// finally 语句中添加delay函数
fun finallyCacheDemo1() = runBlocking {
    val job = launch(CommonPool) {
        try {
            repeat(1000) { i ->
                println("I'm sleeping $i ...")
                delay(500L)
            }
        } finally {
            println("I'm running finally")
            delay(1000L)
            println("And I've delayed for 1 sec ?")
        }
    }
    delay(2000L)
    println("Before cancel, Job is alive: ${job.isActive}  Job is completed: ${job.isCompleted}")
    job.cancel()
    println("After cancel, Job is alive: ${job.isActive}  Job is completed: ${job.isCompleted}")
    delay(2000L)
    println("main: Now I can quit.")
}

fun finallyCacheDemo2() = runBlocking {
    val job = launch(CommonPool) {
        try {
            repeat(1000) { i ->
                println("I'm sleeping $i ...")
                delay(500L)
            }
        } finally {
            withContext(NonCancellable) {
                println("I'm running finally")
                delay(1000L)
                println("And I've delayed for 1 sec ?")
            }
        }
    }
    delay(2000L)
    println("Before cancel, Job is alive: ${job.isActive}  Job is completed: ${job.isCompleted}")
    job.cancel()
    println("After cancel, Job is alive: ${job.isActive}  Job is completed: ${job.isCompleted}")
    delay(2000L)
    println("main: Now I can quit.")
}