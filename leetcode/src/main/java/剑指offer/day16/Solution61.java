package 剑指offer.day16;

import java.util.Arrays;

/**
 * <p>
 *  剑指 Offer 61. 扑克牌中的顺子
 * </p>
 *
 * @author wfb
 * @version 1.0
 * @since 2021-11-17 20:50:59
 */
public class Solution61 {
    public boolean isStraight(int[] nums) {
        if (nums.length != 5) return false;
        Arrays.sort(nums);
        int c0 = 0;
        for (int i = 0; i < 5; i++) if (nums[i] == 0) c0++;
        int min = nums[c0];
        int max = nums[4];
        if (max - min + 1 > 5) return false;
        for (int i = c0 + 1; i < 5; i++) {
            if (nums[i] == min) return false;
            if (nums[i] != ++min) {
                c0--; i--;
            }
        }
        return c0 >= 0;
    }

    public static void main(String[] args) {
        Solution61 solution61 = new Solution61();
        int[] nums = {9, 10, 7, 0, 6};
        System.out.println(solution61.isStraight(nums));
    }
}
