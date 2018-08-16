package com.tiny.demo.firstlinecode.kotlin.primer.project10

/**
 * @Description: kotlin代码实现——wifi密码组合
 *
 * @Author wangjianzhou@qding.me
 * @Version $version$
 * @Date 2018/8/16 5:40 PM
 */
fun main(args: Array<String>) {
    val a: Array<String> = arrayOf("4", "0", "7", "i", "f", "w", "0", "9")
    val index: Array<Int> = arrayOf(5, 3, 9, 4, 8, 3, 1, 9, 2, 1, 7)

    index.filter { it < a.size }
            .map { a[it] }
            .reduce { acc, s -> "$acc$s" }
            .also {
                println("密码是:$it")
            }
}