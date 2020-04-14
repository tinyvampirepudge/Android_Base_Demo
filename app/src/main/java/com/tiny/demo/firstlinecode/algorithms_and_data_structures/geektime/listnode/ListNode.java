package com.tiny.demo.firstlinecode.algorithms_and_data_structures.geektime.listnode;

/**
 * @Description: 单链表
 * @Author wangjianzhou@qding.me
 * @Date 2020/4/12 12:42 AM
 * @Version
 */
public class ListNode {
    int val;
    ListNode next;

    ListNode(int x) {
        val = x;
    }

    @Override
    public String toString() {
        return val + " --> " + next;
    }
}
