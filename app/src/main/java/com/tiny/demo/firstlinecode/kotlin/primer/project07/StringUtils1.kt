package com.tiny.demo.firstlinecode.kotlin.primer.project07

/**
 * @Description:    实现类似静态方法的方式调用。
 *
 * @Author wangjianzhou@qding.me
 * @Version $version$
 * @Date 2018/8/14 3:45 PM
 */

/**
 * 使用jvmStatic注解
 */
object StringUtils1 {
    @JvmStatic
    fun isEmpty1(str: String?): Boolean {
        return null == str || "".equals(str)
    }
}
