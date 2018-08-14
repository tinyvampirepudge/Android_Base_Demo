package com.tiny.demo.firstlinecode.kotlin.primer.project07

/**
 * @Description: $desc$
 *
 * @Author wangjianzhou@qding.me
 * @Version $version$
 * @Date 2018/8/14 3:46 PM
 */
fun main(args: Array<String>) {
    println("jvmStatic注释的静态方法调用")
    println(StringUtils1.isEmpty1(null))
    println(StringUtils1.isEmpty1(""))
    println(StringUtils1.isEmpty1("abc"))

    println("伴生对象的静态方法调用")
    println(StringUtils2.isEmpty(null))
    println(StringUtils2.isEmpty(""))
    println(StringUtils2.isEmpty("abc"))
}