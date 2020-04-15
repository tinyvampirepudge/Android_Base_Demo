package com.tiny.demo.firstlinecode.algorithms_and_data_structures.geektime.stack;

import java.util.HashMap;
import java.util.Stack;

/**
 * @Description: 有效的括号
 * https://leetcode-cn.com/problems/valid-parentheses/
 * @Author wangjianzhou@qding.me
 * @Date 2020/4/14 5:52 PM
 * @Version
 */
public class ValidParentheses {
    HashMap<Character, Character> map = new HashMap(3);

    public ValidParentheses() {
        map.put(')', '(');
        map.put(']', '[');
        map.put('}', '{');
    }

    public boolean isValid(String s) {
        if (s == null) {
            return false;
        }

        Stack<Character> stack = new Stack<>();
        for (Character c : s.toCharArray()) {
            // 右括号的话判断栈中的上一个元素是否是匹配的左括号，不是的话就返回false
            if (map.containsKey(c)) {
                if (stack.empty() || !stack.pop().equals(map.get(c))) {
                    return false;
                }
            } else {
                // 左括号入栈
                stack.push(c);
            }
        }
        return stack.isEmpty();
    }

    public static void main(String[] args) {
        String s = "[][][]()()(){}{}{}";// true
        String s1 = "{[({[()]})]}";// true
        String s2 = "{[}]";// false
        String s3 = "(((([[[]]])))";// false
        String s4 = "]]][[[";// false
        ValidParentheses vp = new ValidParentheses();
        boolean result = vp.isValid(s4);
        System.out.println("result:" + result);
    }
}
