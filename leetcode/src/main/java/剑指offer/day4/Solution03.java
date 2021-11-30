package 剑指offer.day4;

import java.util.HashSet;
import java.util.Set;

/**
 * <p>
 *  剑指 Offer 03. 数组中重复的数字
 * </p>
 *
 * @author wfb
 * @version 1.0
 * @since 2021-11-05 18:19:21
 */
public class Solution03 {
    public int findRepeatNumber(int[] nums) {
        Set<Integer> set = new HashSet<>();
        for (int num : nums) {
            if (!set.add(num)) {
                return  num;
            }
        }
        return 0;
    }

    public static void main(String[] args) {
        int[] nums = {2, 3, 1, 0, 2, 5, 3};
        System.out.println(new Solution03().findRepeatNumber(nums));
    }
}
