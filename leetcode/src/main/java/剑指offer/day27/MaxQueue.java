package 剑指offer.day27;

import java.util.Deque;
import java.util.LinkedList;
import java.util.Queue;

/**
 * <p>
 *  剑指 Offer 59 - II. 队列的最大值
 * </p>
 *
 * @author wfb
 * @version 1.0
 * @since 2021-11-28 21:50:02
 */
public class MaxQueue {

    private final Deque<Integer> maxQ;
    private final Queue<Integer> dataQ;

    public MaxQueue() {
        maxQ = new LinkedList<>();
        dataQ = new LinkedList<>();
    }

    public int max_value() {
        if (maxQ.isEmpty()) return -1;
        return maxQ.peekFirst();
    }

    public void push_back(int value) {
        dataQ.add(value);
        while (!maxQ.isEmpty() && maxQ.peekLast() < value) maxQ.pollLast();
        maxQ.addLast(value);
    }

    public int pop_front() {
        if (dataQ.isEmpty()) return -1;
        int value = dataQ.poll();
        if (value == maxQ.peekFirst()) maxQ.pollFirst();
        return value;
    }

}
