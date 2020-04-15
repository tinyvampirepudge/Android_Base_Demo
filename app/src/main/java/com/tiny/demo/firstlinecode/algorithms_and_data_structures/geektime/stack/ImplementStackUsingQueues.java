package com.tiny.demo.firstlinecode.algorithms_and_data_structures.geektime.stack;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @Description: 用队列实现栈
 * https://leetcode-cn.com/problems/implement-stack-using-queues/
 * @Author wangjianzhou@qding.me
 * @Date 2020/4/14 10:42 PM
 * @Version
 */
public class ImplementStackUsingQueues {
    Queue<Integer> queue1 = new LinkedList<>();
    Queue<Integer> queue2 = new LinkedList<>();

    /**
     * Initialize your data structure here.
     */
    public ImplementStackUsingQueues() {

    }

    /**
     * Push element x onto stack.
     */
    public void push(int x) {
        // 将新加入的元素添加进空的队列中，然后在将另一个队列中的数据依次取出并添加到该队列中
        if (queue1.isEmpty()) {
            queue1.add(x);
            queue1.addAll(queue2);
            queue2.clear();
        } else {
            queue2.add(x);
            queue2.addAll(queue1);
            queue1.clear();
        }
    }

    /**
     * Removes the element on top of the stack and returns that element.
     */
    public int pop() {
        if (queue1.isEmpty()) {
            return queue2.poll();
        } else {
            return queue1.poll();
        }
    }

    /**
     * Get the top element.
     */
    public int top() {
        if (queue1.isEmpty()) {
            return queue2.peek();
        } else {
            return queue1.peek();
        }
    }

    /**
     * Returns whether the stack is empty.
     */
    public boolean empty() {
        return queue1.isEmpty() && queue2.isEmpty();
    }

    public static void main(String[] args) {
        ImplementStackUsingQueues isuq = new ImplementStackUsingQueues();
        isuq.push(1);
        isuq.push(2);
        isuq.push(3);
        System.out.println("111:" + isuq);
        int pop = isuq.pop();
        System.out.println("pop:" + pop);
        isuq.push(4);
        isuq.push(5);
        System.out.println("222:" + isuq);
        System.out.println("top:" + isuq.top());
        System.out.println("empty:" + isuq.empty());
    }
}
