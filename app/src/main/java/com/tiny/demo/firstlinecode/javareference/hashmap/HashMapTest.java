package com.tiny.demo.firstlinecode.javareference.hashmap;

/**
 * @Description: TODO
 * @Author wangjianzhou@qding.me
 * @Date 2018/12/14 10:37 AM
 * @Version TODO
 */
public class HashMapTest {
    static final int MAXIMUM_CAPACITY = 1 << 30;

    public static void main(String[] args) {
        for (int i = 0; i < 10; i++) {
            System.out.println("tableSizeFor(" + i + "):" + tableSizeFor(i));
        }
    }

    static int tableSizeFor(int cap) {
        int n = cap - 1;
        n |= n >>> 1;
        n |= n >>> 2;
        n |= n >>> 4;
        n |= n >>> 8;
        n |= n >>> 16;
        return (n < 0) ? 1 : (n >= MAXIMUM_CAPACITY) ? MAXIMUM_CAPACITY : n + 1;
    }
}
