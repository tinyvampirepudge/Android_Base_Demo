package com.tiny.demo.firstlinecode.kotlin.primer.project07

/**
 * @Description: 利用伴生对象实现单例模式
 *
 * @Author wangjianzhou@qding.me
 * @Version $version$
 * @Date 2018/8/14 4:20 PM
 */
class SingleKt private constructor() {
    init {
        println("SingleKt init")
    }

    companion object {
        fun get(): SingleKt {
            return Holder.instance
        }
    }

    private object Holder {
        val instance = SingleKt()
    }
}