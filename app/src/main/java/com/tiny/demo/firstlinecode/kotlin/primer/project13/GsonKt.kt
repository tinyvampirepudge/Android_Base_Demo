package com.tiny.demo.firstlinecode.kotlin.primer.project13

import com.google.gson.Gson

/**
 * @Description: Gson
 *
 * @Author wangjianzhou@qding.me
 * @Version $version$
 * @Date 2018/8/23 2:44 PM
 */

/**
 * Gson的扩展函数
 * {@link com.google.gson.Gson.fromJson(String json, Class<T> classOfT)}
 */
inline fun <reified T> Gson.fromJson(json: String): T {
    return fromJson(json, T::class.java)
}

val jsonString = "{\n" +
        "\t\"name\":\"WangDanDan\",\n" +
        "\t\"gender\":\"female\",\n" +
        "\t\"age\":1\n" +
        "}";

fun main(args: Array<String>) {
    val gson1 = Gson()
    val danDanBean1 = gson1.fromJson(jsonString, DanDanBean::class.java)
    println("danDanBean1:" + danDanBean1)

    val gson2 = Gson()
    val danDanBean2 = gson2.fromJson<DanDanBean>(jsonString)
    println("danDanBean2:" + danDanBean2)
}