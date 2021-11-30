package 剑指offer.day27;

import java.util.Deque;
import java.util.LinkedList;

/**
 * <p>
 *  剑指 Offer 59 - I. 滑动窗口的最大值
 * </p>
 *
 * @author wfb
 * @version 1.0
 * @since 2021-11-28 21:34:22
 */
public class Solution59_1 {
    public int[] maxSlidingWindow(int[] nums, int k) {
        if (nums == null || nums.length == 0) return new int[0];
        int[] res = new int[nums.length - k + 1];
        Deque<Integer> depue = new LinkedList<>();
        for (int i = 0; i < nums.length; i++) {
            while (!depue.isEmpty() && depue.getLast() < nums[i]) depue.removeLast();
            depue.addLast(nums[i]);
            if (i >= k - 1) {
                res[i - k + 1] = depue.getFirst();
                if (depue.getFirst() == nums[i - k + 1]) depue.removeFirst();
            }
        }
        return res;
    }

    /*
    1
    3
    3 -1
    3 -1 -3
    5
    5 3
    6
    7
     */
    public static void main(String[] args) {
        int[] nums = {1, 3, -1, -3, 5, 3, 6, 7};
        int k = 3;
        Solution59_1 solution59_1 = new Solution59_1();
        int[] res = solution59_1.maxSlidingWindow(nums, k);
        for (int i : res) {
            System.out.println(i);
        }
    }
}
