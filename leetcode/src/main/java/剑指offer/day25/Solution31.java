package 剑指offer.day25;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * <p>
 *  剑指 Offer 31. 栈的压入、弹出序列
 * </p>
 *
 * @author wfb
 * @version 1.0
 * @since 2021-11-26 20:05:26
 */
public class Solution31 {
    public boolean validateStackSequences(int[] pushed, int[] popped) {
        if (pushed == null || popped == null || pushed.length != popped.length) return false;
        Deque<Integer> stack = new ArrayDeque<>(pushed.length);
        int i = 0;
        for (int num : pushed) {
            stack.push(num);
            while (!stack.isEmpty() && stack.peek() == popped[i]) {
                stack.pop(); i++;
            }
        }
        return stack.isEmpty();
    }

    public static void main(String[] args) {
        Solution31 solution31 = new Solution31();
        int[] pushed = new int[]{1, 2, 3, 4, 5};
        int[] popped = new int[]{4, 5, 3, 2, 1};
        System.out.println(solution31.validateStackSequences(pushed, popped));
    }

}
