package com.tiny.demo.firstlinecode.concurrentprogram.chapter08;

import java.util.concurrent.Exchanger;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @Description: Exchanger用于线程间交换数据
 * @Author wangjianzhou@qding.me
 * @Date 2019-09-18 16:02
 * @Version
 */
public class ExchangerTest {
    private static final Exchanger<String> exchanger = new Exchanger<>();
    private static ExecutorService threadPool = Executors.newFixedThreadPool(2);

    public static void main(String[] args) {
        threadPool.execute(new Runnable() {
            @Override
            public void run() {
                try {
                    String A = "银行流水A";
                    Thread.sleep(3000);
                    String result = exchanger.exchange(A);
                    System.out.println("A和B数据是否一致111：" + A.equals(result) + ",A录入的是：" + A + ",B录入的是：" + result);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        threadPool.execute(new Runnable() {
            @Override
            public void run() {
                try {
                    String B = "银行流水B";
                    String result = exchanger.exchange(B);
                    System.out.println("A和B数据是否一致222：" + result.equals(B) + ",A录入的是：" + result + ",B录入的是：" + B);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        threadPool.shutdown();
    }
}
