package com.tiny.demo.firstlinecode.kotlin.primer.project13

/**
 * @Description: reified只能修饰函数，不能修饰类
 *
 * @Author wangjianzhou@qding.me
 * @Version $version$
 * @Date 2018/8/23 3:06 PM
 */

// 运行时直接拿到class类型的对象
class View<T>(val clazz: Class<T>) {
    val presenter by lazy {
        clazz.newInstance()
    }

    // 伴生对象会在构造器初始化之前调用
    // 重载了invoke操作符
    companion object {
        inline operator fun <reified T> invoke() = View(T::class.java)
    }
}

class Presenter {
    override fun toString(): String {
        return "Presenter"
    }
}

fun main(args: Array<String>) {
    // View 层持有Presenter的引用
    val p = View<Presenter>().presenter
    // 实际上相当于下面这段代码
    val p1 = View.Companion.invoke<Presenter>().presenter

    println(p)
    println(p1)
}