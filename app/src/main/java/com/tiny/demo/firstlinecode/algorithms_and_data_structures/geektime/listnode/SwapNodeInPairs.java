package com.tiny.demo.firstlinecode.algorithms_and_data_structures.geektime.listnode;

/**
 * @Description:
 * @Author wangjianzhou@qding.me
 * @Date 2020/4/12 12:42 AM
 * @Version
 */
public class SwapNodeInPairs {
    public static ListNode swapPairs(ListNode head) {
        ListNode dummy = new ListNode(-1);
        dummy.next = head;
        ListNode firstNode = null, secondNode = null, prev = dummy;
        while (head != null && head.next != null) {
            firstNode = head;
            secondNode = head.next;
            // 交换元素
            prev.next = secondNode;
            firstNode.next = secondNode.next;
            secondNode.next = firstNode;

            // 重新赋值
            prev = head;
            head = head.next;
        }
        return dummy.next;
    }

    public static void main(String[] args) {
        ListNode head = new ListNode(1);
        ListNode temp = head;
        for (int i = 2; i < 6; i++) {
            ListNode node = new ListNode(i);
            temp.next = node;
            temp = node;
        }

        ListNode result = swapPairs(head);
        System.out.println(result.toString());
    }
}
