package com.tiny.demo.firstlinecode.kotlin.primer.project05

/**
 * desc 高阶函数
 *
 * @author wangjianzhou@qding.me
 * @version version
 * @date 2018/8/13 11:59 AM
 */

fun onlyif(isDebug: Boolean, block: () -> Unit) {
    if (isDebug) block()
}

fun main(args: Array<String>): Unit {
    onlyif(true) {
        println("打印日志")
    }
}