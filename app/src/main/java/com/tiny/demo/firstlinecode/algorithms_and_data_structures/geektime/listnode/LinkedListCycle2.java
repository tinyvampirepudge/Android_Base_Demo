package com.tiny.demo.firstlinecode.algorithms_and_data_structures.geektime.listnode;

/**
 * @Description: https://leetcode-cn.com/problems/linked-list-cycle-ii/
 * @Author wangjianzhou@qding.me
 * @Date 2020/4/13 3:45 PM
 * @Version
 */
public class LinkedListCycle2 {
    public static ListNode detectCycle(ListNode head) {
        if (head == null || head.next == null) {
            return null;
        }

        ListNode intersect = getIntersect(head);
        if (intersect == null) {
            return null;
        }
        System.out.println("intersect:" + intersect.val);

        // 根据交点，找到环的起点
        ListNode ptr1 = head;
        ListNode ptr2 = intersect;
        while (ptr1 != ptr2) {
            System.out.println("detectCycle while");
            ptr1 = ptr1.next;
            ptr2 = ptr2.next;
        }
        return ptr1;
    }

    /**
     * 龟兔赛跑，快慢指针，找到相交的结点
     *
     * @param head
     * @return
     */
    public static ListNode getIntersect(ListNode head) {
        ListNode tortoise = head;
        ListNode hare = head;

        // A fast pointer will either loop around a cycle and meet the slow
        // pointer or reach the `null` at the end of a non-cyclic list.
        while (hare != null && hare.next != null) {
            tortoise = tortoise.next;
            hare = hare.next.next;
            if (tortoise == hare) {
                return tortoise;
            }
        }

        return null;
    }

    public static void main(String[] args) {
        ListNode head = new ListNode(0);
        ListNode node1 = new ListNode(1);
        head.next = node1;
        ListNode node2 = new ListNode(2);
        node1.next = node2;
        ListNode node3 = new ListNode(3);
        node2.next = node3;
        ListNode node4 = new ListNode(4);
        node3.next = node4;
        node4.next = node1;

        ListNode result = detectCycle(head);
        System.out.println("result:" + result.val);
    }
}
