package com.tinytongtong.tinyutils;

import java.util.List;

/**
 * @Description: list相关工具类。
 *
 * @Author wangjianzhou
 * @Date 2019-08-01 11:39
 * @Version
 */

public class ListUtils {
    /**
     * 判断list是否为空
     *
     * @param list
     * @return list部位空且size大于零。
     */
    public static boolean isEmpty(List list) {
        return list == null || list.size() == 0;
    }

    /**
     * 必将两个List是否相同，长度，顺序。
     * 需要重写元素的equals()方法和hashCode()方法。
     *
     * @param list1
     * @param list2
     * @return
     */
    public static boolean equals(List list1, List list2) {
        if (list1 == list2) {
            return true;
        }
        if (list1 == null || list2 == null) {
            return false;
        }
        if (list1.size() != list2.size()) {
            return false;
        }
        for (int i = 0; i < list1.size(); i++) {
            if (!list1.get(i).equals(list2.get(i))) {
                return false;
            }
        }
        return true;
    }

    public static void logList(List list) {
        if (!isEmpty(list)) {
            for (int j = 0; j < list.size(); j++) {
                LogUtils.d("position " + j + " --> " + list.get(j).toString());
            }
        }
    }
}
