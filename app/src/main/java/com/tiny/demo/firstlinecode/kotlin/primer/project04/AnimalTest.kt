package com.tiny.demo.firstlinecode.kotlin.primer.project04

/**
 * desc 拓展函数的静态分析
 *
 * @author wangjianzhou@qding.me
 * @version version
 * @date 2018/8/13 9:16 AM
 */
fun main(args: Array<String>): Unit {
    val a: Animal = Animal()
    a.printName(a)

    val d: Dog = Dog()
    d.printName(d)
}

/**
 * 给Animal和Dog拓展相同名称的方法。
 */
fun Animal.name() = "animal"

fun Dog.name() = "dog"

/**
 * 给Animal增加一个扩展函数
 */
fun Animal.printName(anim: Animal) {
    println(anim.name())
}

fun abc(s:String):String{
    return s
}