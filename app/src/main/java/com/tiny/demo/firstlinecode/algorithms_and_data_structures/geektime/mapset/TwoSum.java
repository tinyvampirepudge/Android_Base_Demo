package com.tiny.demo.firstlinecode.algorithms_and_data_structures.geektime.mapset;

import java.util.HashMap;
import java.util.Map;

/**
 * @Description: 两数之和
 * https://leetcode-cn.com/problems/two-sum/
 * 给定一个整数数组 nums 和一个目标值 target，请你在该数组中找出和为目标值的那 两个 整数，并返回他们的数组下标。
 * 你可以假设每种输入只会对应一个答案。但是，数组中同一个元素不能使用两遍。
 * @Author wangjianzhou@qding.me
 * @Date 2020/5/8 11:12 AM
 * @Version
 */
public class TwoSum {
    public int[] twoSum(int[] nums, int target) {
        int n = nums.length;

        // 解法一：暴力解法。两层循环嵌套，元素不能重复使用。时间复杂度为O(n^2)。
//        for (int i = 0; i < n - 1; i++) {
//            for (int j = i + 1; j < n; j++) {
//                if (nums[i] + nums[j] == target) {
//                    return new int[]{i, j};
//                }
//            }
//        }

        // 解法二，使用Map
        // 存储数据值和对应的角标
//        Map<Integer, Integer> map = new HashMap<>();
//        for (int i = 0; i < nums.length; i++) {
//            // 算出对应的差值，查找是否已经存在了
//            int key = target - nums[i];
//            // 对应数字已存在于map中，表示匹配成功，退出循环，返回结果
//            if (map.get(key) != null) {
//                return new int[]{map.get(key),i};
//            }
//            map.put(nums[i], i);
//        }

        // key为数据，value为角标
        Map<Integer, Integer> map = new HashMap<>();
        // 循环遍历数据
        for (int i = 0; i < n; i++) {
            // x + y = 9
            if (map.get(target - nums[i]) != null) {
                return new int[]{map.get(target - nums[i]), i};
            }
            map.put(nums[i], i);
        }

        return new int[]{-1, -1};
    }

    public static void main(String[] args) {
        int[] nums = new int[]{2, 7, 11, 15};
        int target = 9;
        TwoSum twoSum = new TwoSum();
        int[] result = twoSum.twoSum(nums, target);
        for (Integer item : result) {
            System.out.println(item);
        }
    }
}
