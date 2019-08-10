package com.tiny.demo.firstlinecode.javareference.copy.utils;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.List;

/**
 * @Description: 通过序列化/反序列化方式完成深复制
 * @Author wangjianzhou@qding.me
 * @Date 2019-08-10 09:55
 * @Version
 */
public class DeepCopyUtils {

    /**
     * 要求data对象及其引用对象都实现了Serializable接口才可以用
     * 针对Object、数组、Map、List类型
     *
     * @param source
     * @param <T>
     * @return
     * @throws Exception
     */
    public static <T> T deepCopyObject(T source) {
        T dest = null;
        if (source == null) {
            return dest;
        }
        try {
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            ObjectOutputStream oos = new ObjectOutputStream(baos);
            oos.writeObject(source);
            //从流里读出来
            ByteArrayInputStream bais = new ByteArrayInputStream(baos.toByteArray());
            ObjectInputStream oi = new ObjectInputStream(bais);
            dest = (T) oi.readObject();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return dest;
    }

    /**
     * 要求data对象及其引用对象都实现了Serializable接口才可以用
     * 针对List类型
     *
     * @param src
     * @param <T>
     * @return
     */
    public static <T> List<T> deepCopyList(List<T> src) {
        List<T> dest = null;
        if (src == null) {
            return dest;
        }
        try {
            ByteArrayOutputStream byteOut = new ByteArrayOutputStream();
            ObjectOutputStream out = new ObjectOutputStream(byteOut);
            out.writeObject(src);

            ByteArrayInputStream byteIn = new ByteArrayInputStream(byteOut.toByteArray());
            ObjectInputStream in = null;
            in = new ObjectInputStream(byteIn);

            dest = (List<T>) in.readObject();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return dest;
    }

    /**
     * 要求data对象及其引用对象都实现了Serializable接口才可以用
     * 针对数组类型
     *
     * @param source
     * @param <T>
     * @return
     */
    public static <T> T[] deepCopyArray(T[] source) {
        T[] dest = null;
        try {
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            ObjectOutputStream oos = new ObjectOutputStream(baos);
            oos.writeObject(source);
            //从流里读出来
            ByteArrayInputStream bais = new ByteArrayInputStream(baos.toByteArray());
            ObjectInputStream oi = new ObjectInputStream(bais);
            dest = (T[]) oi.readObject();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return dest;
    }

}
