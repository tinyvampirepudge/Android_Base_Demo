package com.tiny.demo.firstlinecode.kotlin.primer.project05

import com.tiny.demo.firstlinecode.kotlin.primer.project03.A.a

/**
 * desc Lambda闭包声明
 *
 * @author wangjianzhou@qding.me
 * @version version
 * @date 2018/8/13 10:49 AM
 */
val echo = { name: String ->
    println(name)
    println("我就打个酱油")
}

val print = { name: String ->
    println(name)
}

val lambdaA = { a: Int, b: Int, c: Int, d: Int, e: Int, f: Int, g: Int, h: Int,
                i: Int, j: Int, k: Int, l: Int, m: Int, n: Int, o: Int, p: Int,
                q: Int, r: Int, s: Int, t: Int, u: Int, v: Int, w: Int ->
    print("DanDan")
}

fun main(args: Array<String>) {
//    print("hello dandan ")
    lambdaA(1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1)
}