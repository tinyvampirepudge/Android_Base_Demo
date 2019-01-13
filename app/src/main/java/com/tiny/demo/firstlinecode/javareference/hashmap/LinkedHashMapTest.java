package com.tiny.demo.firstlinecode.javareference.hashmap;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @Description: TODO
 * @Author wangjianzhou@qding.me
 * @Date 2018/12/17 2:04 PM
 * @Version TODO
 */
public class LinkedHashMapTest {
    public static void main(String[] args) {
//        LinkedHashMap<Integer, Integer> m = new LinkedHashMap<>(10,0.75f,true);
        LinkedHashMap<Integer, Integer> m = new LinkedHashMap<>();
        m.put(1, 1);
        m.put(2, 2);
        m.put(3, 3);
        m.put(4, 4);
        m.put(5, 5);
        m.get(1);

        for (Map.Entry e : m.entrySet()) {
            System.out.println("key:" + e.getKey() + ",value:" + e.getValue());
        }
    }
}
