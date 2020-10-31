package com.tiny.demo.firstlinecode.javareference.reference;

import java.lang.ref.PhantomReference;
import java.lang.ref.ReferenceQueue;
import java.lang.ref.WeakReference;

/**
 * @Description: WeakReference和引用队列测试
 * @Author tinytongtong
 * @Date 2020/10/28 11:28 AM
 * @Version
 */
public class WeakReferenceTest {
    public static void main(String[] args) {
        Object o = new Object();
        ReferenceQueue<Object> referenceQueue = new ReferenceQueue<>();
        WeakReference<Object> weakReference = new WeakReference<>(new Object(), referenceQueue);

        System.out.println("----------------------");
        System.out.println(weakReference.get());
        sass(referenceQueue);

        System.gc();

        System.out.println("----------------------");
        System.out.println(weakReference.get());
        sass(referenceQueue);


        ReferenceQueue<Object> referenceQueue1 = new ReferenceQueue<>();
        PhantomReference<Object> phantomReference = new PhantomReference<>(new Object(), referenceQueue1);
        System.out.println("----------------------");
        System.out.println(phantomReference.get());
        sass1(referenceQueue1);

        System.gc();

        System.out.println("----------------------");
        System.out.println(phantomReference.get());
        sass1(referenceQueue1);
    }

    private static void sass(ReferenceQueue<Object> referenceQueue) {
        System.out.println("sass");
        WeakReference ref;
        while (referenceQueue != null && (ref = (WeakReference) referenceQueue.poll()) != null) {
            System.out.println(ref + "," + ref.get());
        }
    }

    private static void sass1(ReferenceQueue<Object> referenceQueue) {
        System.out.println("sass1");
        PhantomReference ref;
        while (referenceQueue != null && (ref = (PhantomReference) referenceQueue.poll()) != null) {
            System.out.println(ref + "," + ref.get());
        }
    }
}
