package com.tiny.demo.firstlinecode.kotlin.primer.project06

/**
 * desc 构造函数
 *
 * @author wangjianzhou@qding.me
 * @version version
 * @date 2018/8/13 4:13 PM
 */
class Person {
    init {
        println(javaClass.simpleName + " inited")
    }

//    constructor() {
//        println(javaClass.simpleName + " 111")
//    }

    constructor(name: String) : super() {
        println(javaClass.simpleName + " 222")
    }
}