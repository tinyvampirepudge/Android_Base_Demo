package com.tiny.demo.firstlinecode.algorithms_and_data_structures.geektime.heap;

import java.util.PriorityQueue;

/**
 * @Description: TODO
 * @Author wangjianzhou
 * @Date 2020/4/16 12:30 AM
 * @Version
 */
public class KthLargest {
    private PriorityQueue<Integer> priorityQueue;
    private int limit;

    public KthLargest(int k, int[] nums) {
        priorityQueue = new PriorityQueue<>(k);
        limit = k;
        for (Integer item : nums) {
            add(item);
        }
    }

    public int add(int val) {
        if (priorityQueue.size() < limit) {
            priorityQueue.add(val);
        } else {
            if (val > priorityQueue.peek()) {
                priorityQueue.poll();
                priorityQueue.add(val);
            }
        }
        return priorityQueue.peek();
    }

    public static void main(String[] args) {
        int k = 3;
        int[] arr = new int[]{4, 5, 8, 2};
        KthLargest kthLargest = new KthLargest(k, arr);
        int result1 = kthLargest.add(3);// returns 4
        System.out.println(result1);
        int result2 = kthLargest.add(5);// returns 5
        System.out.println(result2);
        int result3 = kthLargest.add(10);// returns 5
        System.out.println(result3);
        int result4 = kthLargest.add(9);// returns 8
        System.out.println(result4);
        int result5 = kthLargest.add(4);// returns 8
        System.out.println(result5);
    }
}

/**
 * Your KthLargest object will be instantiated and called as such:
 * KthLargest obj = new KthLargest(k, nums);
 * int param_1 = obj.add(val);
 */
