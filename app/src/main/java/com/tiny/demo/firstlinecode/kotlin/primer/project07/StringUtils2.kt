package com.tiny.demo.firstlinecode.kotlin.primer.project07

/**
 * @Description: $desc$
 *
 * @Author wangjianzhou@qding.me
 * @Version $version$
 * @Date 2018/8/14 3:53 PM
 */
class StringUtils2 {
    companion object {
        fun isEmpty(str: String?): Boolean {
            return null == str || "".equals(str)
        }
    }
}