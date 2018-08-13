package com.tiny.demo.firstlinecode.kotlin.primer.project05;

/**
 * desc Java中lambda语法
 *
 * @author wangjianzhou@qding.me
 * @version version
 * @date 2018/8/13 10:28 AM
 */
public class LambdaJava {
    public static void main(String[] args) {
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("Java中Runnable基本写法");
            }
        });
        thread.start();

        Thread thread1 = new Thread(() -> {
            System.out.println("Java中Runnable使用Lambda写法");
        });
        thread1.start();
    }
}
