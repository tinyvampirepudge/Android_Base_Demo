package com.tiny.demo.firstlinecode.algorithms_and_data_structures.geektime.stack;

import java.util.Stack;

/**
 * @Description: 用栈实现队列
 * https://leetcode-cn.com/problems/implement-queue-using-stacks/
 * @Author wangjianzhou@qding.me
 * @Date 2020/4/14 10:41 PM
 * @Version
 */
public class ImplementQueueUsingStacks {
    Stack<Integer> stackInput = new Stack<>();
    Stack<Integer> stackOutput = new Stack<>();

    /**
     * Initialize your data structure here.
     */
    public ImplementQueueUsingStacks() {

    }

    /**
     * Push element x to the back of queue.
     */
    public void push(int x) {
        stackInput.push(x);
    }

    /**
     * Removes the element from in front of queue and returns that element.
     */
    public int pop() {
        if (stackOutput.empty()) {
            while (!stackOutput.empty()){
                stackOutput.push(stackInput.pop());
            }
        }
        return stackOutput.pop();
    }

    /**
     * Get the front element.
     */
    public int peek() {
        if (stackOutput.empty()) {
            while (!stackOutput.empty()){
                stackOutput.push(stackInput.pop());
            }
        }
        return stackOutput.peek();
    }

    /**
     * Returns whether the queue is empty.
     */
    public boolean empty() {
        return stackInput.empty() && stackOutput.empty();
    }
}
