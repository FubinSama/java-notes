package 剑指offer.day23;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

/**
 * <p>
 *  剑指 Offer 39. 数组中出现次数超过一半的数字
 * </p>
 *
 * @author wfb
 * @version 1.0
 * @since 2021-11-24 20:02:44
 */
public class Solution39 {
    public int majorityElement(int[] nums) {
        int count = 0;
        int candidate = 0;
        for (int num : nums) {
            if (count == 0) candidate = num;
            count += (num == candidate) ? 1 : -1;
        }
        return candidate;
    }

    public static void main(String[] args) {
        Solution39 solution39 = new Solution39();
        System.out.println(solution39.majorityElement(new int[]{1, 2, 3, 2, 2, 2, 5, 4, 2}));
    }
}
