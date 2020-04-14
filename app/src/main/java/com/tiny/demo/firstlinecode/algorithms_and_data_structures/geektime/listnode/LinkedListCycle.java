package com.tiny.demo.firstlinecode.algorithms_and_data_structures.geektime.listnode;

/**
 * @Description: 判断链表是否有环
 * https://leetcode-cn.com/problems/linked-list-cycle/submissions/
 * @Author wangjianzhou@qding.me
 * @Date 2020/4/13 12:01 AM
 * @Version TODO
 */
public class LinkedListCycle {
    public static boolean hasCycle(ListNode head) {
        ListNode fast = head;
        ListNode slow = head;
        while (fast != null && slow != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
            // 快慢指针终将相遇
            if (slow != null && fast != null && slow.val == fast.val) {
                return true;
            }
        }
        return false;
    }

    public static void main(String[] args) {
        ListNode head = new ListNode(0);
        ListNode node1 = new ListNode(1);
        head.next = node1;
        node1.next = head;
//        ListNode node2 = new ListNode(2);
//        node1.next = node2;
//        ListNode node3 = new ListNode(3);
//        node2.next = node3;
//        ListNode node4 = new ListNode(4);
//        node3.next = node4;

        boolean result = hasCycle(head);
        System.out.println("result:" + result);
    }
}
