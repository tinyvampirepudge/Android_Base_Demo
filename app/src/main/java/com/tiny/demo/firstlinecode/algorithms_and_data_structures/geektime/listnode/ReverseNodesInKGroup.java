package com.tiny.demo.firstlinecode.algorithms_and_data_structures.geektime.listnode;

/**
 * @Description: https://leetcode-cn.com/problems/reverse-nodes-in-k-group/
 * @Author wangjianzhou@qding.me
 * @Date 2020/4/13 5:53 PM
 * @Version
 */
public class ReverseNodesInKGroup {
    public static ListNode reverseKGroup(ListNode head, int k) {
        ListNode dummy = new ListNode(-1);
        dummy.next = head;

        ListNode prev = dummy, end = dummy;
        while (end.next != null) {
            for (int i = 0; i < k && end != null; i++) {
                end = end.next;
            }
            if (end == null) {
                break;
            }
            // 记录下后面的节点
            ListNode next = end.next;
            // 翻转子链表 [start -- end]
            end.next = null;
            ListNode start = prev.next;
            prev.next = reverse(start);// 将翻转后的链表跟前面链接起来
            // 将翻转后的链表跟后面节点链接起来
            start.next = next;
            // 重新给prev 和 end变量赋值
            end = prev = start;
        }

        return dummy.next;
    }

    // 反转一个链表
    public static ListNode reverse(ListNode head) {
        ListNode prev = null;
        ListNode curr = head;
        while (curr != null) {
            ListNode next = curr.next;
            curr.next = prev;
            prev = curr;
            curr = next;
        }
        return prev;
    }

    public static void main(String[] args) {
        ListNode head = new ListNode(1);
        ListNode temp = head;
        for (int i = 2; i < 11; i++) {
            ListNode node = new ListNode(i);
            temp.next = node;
            temp = node;
        }

        ListNode result = reverseKGroup(head, 3);
        System.out.println(result.toString());
    }
}
