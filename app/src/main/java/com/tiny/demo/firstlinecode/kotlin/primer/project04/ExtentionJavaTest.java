package com.tiny.demo.firstlinecode.kotlin.primer.project04;

import java.io.File;

import kotlin.io.FilesKt;
import kotlin.text.Charsets;
import okio.Utf8;

/**
 * desc Java中调用扩展函数
 *
 * @author wangjianzhou@qding.me
 * @version version
 * @date 2018/8/11 12:38 AM
 */
public class ExtentionJavaTest {

    public static void main(String[] args) {
        File file = new File("app/src/main/java/com/tiny/demo/firstlinecode/kotlin/primer/project04/Main.kt");

        // 调用类名是定义扩展函数的那个文件，
        // 方法的第一个参数是被扩展的那个类的对象
        // 另外，扩展方法参数中的默认值不能省略。
        String content = ExtentionKtTestKt.readTextTest(file, Charsets.UTF_8);
        System.out.println(content);
    }
}
