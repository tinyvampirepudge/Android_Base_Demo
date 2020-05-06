package com.tiny.demo.firstlinecode.javareference.base;

/**
 * @Description: TODO
 * @Author wangjianzhou@qding.me
 * @Date 2020/5/3 11:46 PM
 * @Version TODO
 */
public class ByteTest {
    public static void main(String[] args) {
        byte b = Byte.MAX_VALUE;
        System.out.println("Byte.MAX_VALUE:" + b);
        b = (byte) (b + 1);
        System.out.println("Byte.MAX_VALUE+1:" + b);

        b = (byte) (Byte.MAX_VALUE + 255);
        System.out.println("Byte.MAX_VALUE+255:" + b);
        b = (byte) (Byte.MAX_VALUE + 256);
        System.out.println("Byte.MAX_VALUE+256:" + b);
        b = (byte) (Byte.MAX_VALUE + 257);
        System.out.println("Byte.MAX_VALUE+257:" + b);

        short s = Short.MAX_VALUE;
        System.out.println("Short.MAX_VALUE:" + s);
        s = (short) (s + 1);
        System.out.println("Short.MAX_VALUE+1:" + s);

        int i = Integer.MAX_VALUE;
        System.out.println("Integer.MAX_VALUE=" + i);
        i = i + 1;
        System.out.println("Integer.MAX_VALUE+1=" + i);

        long l = Long.MAX_VALUE;
        System.out.println("Long.MAX_VALUE=" + l);
        l = l + 1;
        System.out.println("Long.MAX_VALUE+1=" + l);
    }
}
