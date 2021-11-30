package 高效制胜.day5;

import java.util.Deque;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

/**
 * <p>
 *  456. 132 模式
 * </p>
 *
 * @author wfb
 * @version 1.0
 * @since 2021-11-07 22:33:16
 */
public class Solution456 {
    public boolean find132pattern(int[] nums) {
        if (nums.length < 3) return false;
        Deque<Integer> stack = new LinkedList<>(); // 单调递减栈
        stack.push(nums[nums.length - 1]);
        int k = Integer.MIN_VALUE;
        for (int i = nums.length - 2; i >= 0; i--) {
            if (nums[i] < k) return true;
            while (!stack.isEmpty() && stack.peek() < nums[i]) k = Math.max(k, stack.pop());
            stack.push(nums[i]);
        }
        return false;
    }

    public static void main(String[] args) {
        Solution456 solution456 = new Solution456();
        int[] nums = {3, 1, 4, 2};
        System.out.println(solution456.find132pattern(nums));
    }
}
