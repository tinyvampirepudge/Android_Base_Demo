package com.tiny.demo.firstlinecode.algorithms_and_data_structures.geektime.mapset;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

/**
 * @Description: 有效的字母异位词
 * https://leetcode-cn.com/problems/valid-anagram/
 * @Author wangjianzhou@qding.me
 * @Date 2020/5/8 1:17 PM
 * @Version
 */
public class ValidAnagram {
    public boolean isAnagram(String s, String t) {
        if (s.length() != t.length()) {
            return false;
        }

        // 解法一：排序
        // 使用数组
//        char[] sChars = s.toCharArray();
//        char[] tChars = t.toCharArray();
//        Arrays.sort(sChars);
//        Arrays.sort(tChars);
//        return Arrays.equals(sChars, tChars);

        // 使用List
//        List<Character> sChars = new ArrayList<>();
//        List<Character> tChars = new ArrayList<>();
//        for (int i = 0; i < s.length(); i++) {
//            sChars.add(s.charAt(i));
//            tChars.add(t.charAt(i));
//        }
//        Collections.sort(sChars);
//        Collections.sort(tChars);
//        return sChars.equals(tChars);

        // 解法二：使用map计数，key为字符，value为出现次数
//        Map<Character, Integer> sMap = new HashMap<>();
//        Map<Character, Integer> tMap = new HashMap<>();
//        for (int i = 0; i < s.length(); i++) {
//            Integer sValue = sMap.get(s.charAt(i));
//            if (sValue != null) {
//                sMap.put(s.charAt(i), sValue + 1);
//            } else {
//                sMap.put(s.charAt(i), 0);
//            }
//            Integer tValue = tMap.get(t.charAt(i));
//            if (tValue != null) {
//                tMap.put(t.charAt(i), tValue + 1);
//            } else {
//                tMap.put(t.charAt(i), 0);
//            }
//        }
//        return sMap.equals(tMap);

        // 解法三：计数器——固定长度26
        // 使用一个计数器，一个字符串累加，一个字符串累减，最后看计数的数组是否都为0
//        int[] counter = new int[26];
//        for (int i = 0; i < s.length(); i++) {
//            counter[s.charAt(i) - 'a']++;
//            counter[t.charAt(i) - 'a']--;
//        }
//        for (int item : counter) {
//            if (item != 0) {
//                return false;
//            }
//        }
//        return true;
        // 使用两个int数组计数，最后比较两个数组是否相等
        int[] sCounter = new int[26];
        int[] tCounter = new int[26];
        for (int i = 0; i < s.length(); i++) {
            sCounter[s.charAt(i) - 'a']++;
            tCounter[t.charAt(i) - 'a']++;
        }
        return Arrays.equals(sCounter, tCounter);
    }

    public static void main(String[] args) {
        ValidAnagram validAnagram = new ValidAnagram();
        System.out.println(validAnagram.isAnagram("abc", "abdef"));
        System.out.println(validAnagram.isAnagram("abc", "bca"));
        System.out.println(validAnagram.isAnagram("abc", "def"));
    }
}
