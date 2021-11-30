package 剑指offer.day1;

import java.util.Deque;
import java.util.LinkedList;

/**
 * <p>
 *  剑指 Offer 09. 用两个栈实现队列
 * </p>
 *
 * @author wfb
 * @version 1.0
 * @since 2021-11-02 20:28:33
 */
public class CQueue {

    private final Deque<Integer> stackPush = new LinkedList<>();
    private final Deque<Integer> stackPop = new LinkedList<>();

    public CQueue() {}

    public void appendTail(int value) {
        stackPush.push(value);
    }

    public int deleteHead() {
        // 若队列中没有元素，deleteHead 操作返回 -1
        if (stackPop.isEmpty() && stackPush.isEmpty()) return -1;
        // 如果stackPop中还有元素，则直接返回栈顶
        if (!stackPop.isEmpty()) return stackPop.pop();
        // 否则，尝试从stackPush中加载元素到stackPush，元素通过两次先进后出后，就变成了先进先出了
        while (!stackPush.isEmpty()) stackPop.push(stackPush.pop());
        return stackPop.pop();
    }

}
