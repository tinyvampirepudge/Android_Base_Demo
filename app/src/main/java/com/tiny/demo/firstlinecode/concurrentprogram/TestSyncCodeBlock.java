package com.tiny.demo.firstlinecode.concurrentprogram;

import java.util.concurrent.TimeUnit;

/**
 * @Description:
 * @Author wangjianzhou@qding.me
 * @Date 2019-12-28 14:54
 * @Version
 */
public class TestSyncCodeBlock {
    private int index;

    public TestSyncCodeBlock(int index) {
        this.index = index;
    }

    public void abc() throws InterruptedException {
        synchronized (this) {
            System.out.println(String.format("开始执行对象的abc方法中的同步代码块了，当前时间:%d", System.currentTimeMillis()));
            TimeUnit.SECONDS.sleep(5);
            System.out.println(String.format("对象的abc方法中的同步代码块执行结束，当前时间:%d", System.currentTimeMillis()));
        }
        {
            System.out.println(String.format("开始执行对象的abc方法中的非同步代码块了，当前时间:%d", System.currentTimeMillis()));
            TimeUnit.SECONDS.sleep(5);
            System.out.println(String.format("对象的abc方法中的非同步代码块执行结束，当前时间:%d", System.currentTimeMillis()));

        }
    }

    public static void main(String[] args) {
        TestSyncCodeBlock testSync1 = new TestSyncCodeBlock(1);
        new Thread(() -> {
            try {
                testSync1.abc();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();

        new Thread(() -> {
            try {
                testSync1.abc();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();

        /**
         * 输出如下：总耗时大概15s多一点
         *
         * 开始执行对象的abc方法中的同步代码块了，当前时间:1577516689754
         * 对象的abc方法中的同步代码块执行结束，当前时间:1577516694773
         * 开始执行对象的abc方法中的非同步代码块了，当前时间:1577516694773
         * 开始执行对象的abc方法中的同步代码块了，当前时间:1577516694773
         * 对象的abc方法中的同步代码块执行结束，当前时间:1577516699778
         * 对象的abc方法中的非同步代码块执行结束，当前时间:1577516699778
         * 开始执行对象的abc方法中的非同步代码块了，当前时间:1577516699778
         * 对象的abc方法中的非同步代码块执行结束，当前时间:1577516704779
         *
         * Process finished with exit code 0
         *
         *
         */
    }
}
