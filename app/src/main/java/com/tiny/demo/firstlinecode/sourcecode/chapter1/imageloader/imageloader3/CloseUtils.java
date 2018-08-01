package com.tiny.demo.firstlinecode.sourcecode.chapter1.imageloader.imageloader3;

import java.io.Closeable;
import java.io.IOException;

/**
 * Desc:    关闭Closeable接口，释放资源。
 * Created by tiny on 2017/10/6.
 * Version:
 */

public class CloseUtils {
    public static void closeQuietly(Closeable closeable) {
        if (null != closeable) {
            try {
                closeable.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
