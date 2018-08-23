package com.tiny.demo.firstlinecode.kotlin.primer.project12

import java.util.*

/**
 * @Description: var 和 val
 *
 * @Author wangjianzhou@qding.me
 * @Version $version$
 * @Date 2018/8/23 10:29 AM
 */
fun main(args: Array<String>) {
    var hello = Hello()
    println(hello.string)
    hello.string = "world"
    println(hello.string)

    println(hello.string2)

    val person = Person(1990)
    println(person.age)
    person.onYearlater()
    println(person.age)
}

class Hello {
    var string: String? = null
        get() {
            return "get" + field
        }
        set(value) {
            field = value + "set"
        }


    val string2: String? = null
        get() {
            return field + "val_get"
        }
}

class Person(var birthYear: Int) {
    val age: Int
        get() {
            return Calendar.getInstance().get(Calendar.YEAR) - birthYear
        }

    fun onYearlater() {
        birthYear--
    }
}