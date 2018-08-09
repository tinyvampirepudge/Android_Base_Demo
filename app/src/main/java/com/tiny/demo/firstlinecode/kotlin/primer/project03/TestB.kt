package com.tiny.demo.firstlinecode.kotlin.primer.project03

/**
 * desc
 *
 * @author wangjianzhou@qding.me
 * @version version
 * @date 2018/8/9 11:56 AM
 */
fun main(args: Array<String>): Unit {
    function("")
}

fun function(str: String): Unit {
    // 类型自动推断
    var fm1 = B.format(str)
//    println(fm1.length) // Exception in thread "main" java.lang.NullPointerException
    // String 不可为空
//    var fm2: String = B.format(str) //Exception in thread "main" java.lang.IllegalStateException: B.format(str) must not be null
    // String 可为空
    var fm3: String? = B.format(str)
    println(fm3?.length) // 输出为null, 表示正常执行
}