package com.tiny.demo.firstlinecode.kotlin.primer.project10

/**
 * @Description: 解构
 *
 * @Author wangjianzhou@qding.me
 * @Version $version$
 * @Date 2018/8/16 11:55 AM
 */

class JieGouUser(var age: Int, var name: String) {
    /**
     * 使用operator操作符, 将一个函数标记为重载一个操作符或者实现一个约定。
     * fun 后面的方法名命名有规范，必须是componentX类型，X表示数字
     */
    operator fun component1() = age

    operator fun component2() = name
}

data class JieGouUser1(var age: Int, var name: String, var weight: Double)