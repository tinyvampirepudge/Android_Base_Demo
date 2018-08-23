package com.tiny.demo.firstlinecode.kotlin.primer.project12

/**
 * @Description: Java的编译期空安全
 * @Author wangjianzhou@qding.me
 * @Version $version$
 * @Date 2018/8/23 12:06 PM
 */
object JavaMain {
    @JvmStatic
    fun main(args: Array<String>) {
        test("")
        test2(null)
    }

    fun test(str: String) {
        println(str)
    }

    fun test2(string: String?) {
        println(string)
    }
}
