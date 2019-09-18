package com.tiny.demo.firstlinecode.concurrentprogram.chapter08;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**
 * @Description: CyclicBarrier测试
 * @Author wangjianzhou@qding.me
 * @Date 2019-09-18 15:01
 * @Version TODO
 */
public class CyclicBarrierTest {
    Runnable barrierAction;
    static CyclicBarrier cyclicBarrier = new CyclicBarrier(2, new A());

    public static void main(String[] args) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    System.out.println(0);
                    cyclicBarrier.await();
                } catch (BrokenBarrierException e) {
                    e.printStackTrace();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(1);
            }
        }).start();

        try {
            System.out.println(2);
            cyclicBarrier.await();
        } catch (BrokenBarrierException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(4);
    }

    static class A implements Runnable {

        @Override
        public void run() {
            System.out.println(3);
        }
    }


}
