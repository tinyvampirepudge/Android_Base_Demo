package com.tiny.demo.firstlinecode.kotlin.primer.project03;

/**
 * desc
 *
 * @author wangjianzhou@qding.me
 * @version version
 * @date 2018/8/9 11:55 AM
 */
public class B {
    /**
     * 假设传递进来的string不为空
     *
     * @param string
     * @return
     */
    public static String format(String string) {
        return string.isEmpty() ? null : string;
    }
}
