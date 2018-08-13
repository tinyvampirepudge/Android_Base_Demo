package com.tiny.demo.firstlinecode.kotlin.primer.project05

/**
 * desc 高阶函数
 *
 * @author wangjianzhou@qding.me
 * @version version
 * @date 2018/8/13 11:59 AM
 */

/**
 * 定义一个方法，接受两个参数
 * 第一个为boolean类型变量
 * 第二个为一个无参无返回值(Unit)的函数。
 */
inline fun onlyif(isDebug: Boolean, block: () -> Unit) {
    if (isDebug) block()
}

fun main(args: Array<String>): Unit {
    onlyif(true) {
        println("打印日志")
    }

    // 声明了一个Runnable类型的对象
    val runnable = Runnable {
        println("Runnable::run")
    }

    // 声明一个无参无返回值的函数对象
    val function: () -> Unit

    // 将Runnable的run方法赋值给前面声明好的函数
    function = runnable::run

    // 将函数作为参数传递给onlyif这个高阶函数
    onlyif(true, function)
}

