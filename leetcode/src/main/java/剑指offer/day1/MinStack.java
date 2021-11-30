package 剑指offer.day1;

import java.util.Deque;
import java.util.LinkedList;

/**
 * <p>
 *  剑指 Offer 30. 包含min函数的栈
 * </p>
 *
 * @author wfb
 * @version 1.0
 * @since 2021-11-02 20:37:23
 */
public class MinStack {

    // 存放输入的数据
    private final Deque<Integer> dataStack = new LinkedList<>();
    // 存放到当前位置，dataStack中的最小元素值
    private final Deque<Integer> minStack = new LinkedList<>();

    /** initialize your data structure here. */
    public MinStack() {}

    public void push(int x) {
        // 将输入的元素存入数据栈中
        dataStack.push(x);
        // 求出在未输入数据x时，栈中的最小元素
        int min = minStack.isEmpty()? Integer.MAX_VALUE: minStack.peek();
        // 将输入x后栈中的最小元素值放入栈中
        minStack.push(Math.min(x, min));
    }

    public void pop() {
        dataStack.pop();
        minStack.pop();
    }

    public int top() {
        if (dataStack.isEmpty()) throw new RuntimeException("已经没有数据了");
        return dataStack.peek();
    }

    public int min() {
        if (minStack.isEmpty()) throw new RuntimeException("已经没有数据了");
        return minStack.peek();
    }
}
