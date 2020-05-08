package com.tiny.demo.firstlinecode.algorithms_and_data_structures.geektime.heap;

import java.util.ArrayDeque;

/**
 * @Description: 滑动窗口最大值
 * https://leetcode-cn.com/problems/sliding-window-maximum/
 * @Author wangjianzhou@qding.me
 * @Date 2020/5/7 11:24 AM
 * @Version
 */
public class SlidingWindowMaximum {
    public static int[] maxSlidingWindow(int[] nums, int k) {
        if (nums.length * k == 0) {
            return new int[0];
        }

        int n = nums.length;

        int[] result = new int[n - k + 1];

//        // 解法一，记录当前的最大值max，时间复杂度是O(N * k)
//        for (int i = 0; i < n - k + 1; i++) {
//            int max = Integer.MIN_VALUE;
//            for (int j = i; j < i + k; j++) {
//                max = Math.max(max, nums[j]);
//            }
//            result[i] = max;
//        }

        // 解法二：通过双向队列来解决，时间复杂度为O(n)
        // 通过ArrayDeque来记录索引
        ArrayDeque<Integer> list = new ArrayDeque<>(k);
        // 初始化队列
        for (int i = 0; i < k; i++) {
            addNum(list, nums, i, k);
        }
        result[0] = nums[list.getFirst()];

        for (int i = 1; i < n - k + 1; i++) {
            addNum(list, nums, i + k - 1, k);
            result[i] = nums[list.getFirst()];
        }

        return result;
    }

    /**
     * @param src
     * @param nums
     * @param index
     * @return
     */
    private static void addNum(ArrayDeque<Integer> src, int[] nums, int index, int k) {
        // 移除窗口之外的角标，这里的index是队列右侧加入的角标
        if (!src.isEmpty() && src.getFirst() == index - k) {
            src.removeFirst();
        }

        // 移除所有比新元素小的元素的角标
        while (!src.isEmpty() && nums[index] > nums[src.getLast()]) {
            src.removeLast();
        }

        // 新增新元素到末尾
        src.addLast(index);
    }

    public static void main(String[] args) {
//        int[] nums = new int[]{1, 3, -1, -3, 5, 3, 6, 7};
//        int[] nums = new int[]{1, -1};
        int[] nums = new int[]{1, 3, 1, 2, 0, 5};
        int[] result = maxSlidingWindow(nums, 3);
        for (Integer item : result) {
            System.out.println(item);
        }
    }
}
