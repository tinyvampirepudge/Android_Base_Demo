package com.tiny.demo.firstlinecode.kotlin.primer.project10

/**
 * @Description: $desc$
 *
 * @Author wangjianzhou@qding.me
 * @Version $version$
 * @Date 2018/8/16 12:00 PM
 */
fun main(args: Array<String>) {
    val jieGouUser = JieGouUser(1, "WangDanDan")
    //解构调用，age对应刚才定义的component1，name对应刚才定义的component2
    val (age, name) = jieGouUser
    println(age)
    println(name)

    println("下面是对数据类就行解构")

    var jieGouUser1 = JieGouUser1(1, "WangDanDan", 7.0)
    val (age1, name1, weight1) = jieGouUser1
    println(age1)
    println(name1)
    println(weight1)

    val (age2, name2) = jieGouUser1
    println(age2)
    println(name2)

    val map = mapOf<String, String>("111" to "222", "333" to "444")
    for ((k, v) in map) {
        println("$k ----- $v")
    }
}