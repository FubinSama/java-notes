package 剑指offer.day22;

/**
 * <p>
 *  剑指 Offer 56 - II. 数组中数字出现的次数 II
 * </p>
 *
 * @author wfb
 * @version 1.0
 * @since 2021-11-23 20:11:00
 */
public class Solution56_2 {
    public int singleNumber(int[] nums) {
        int two = 0, one = 0;
        for (int num : nums) {
            one = one ^ num & ~two;
            two = two ^ num & ~one;
        }
        return one;
    }

    public static void main(String[] args) {
        int[] nums = {9,1,7,9,7,9,7};
        System.out.println(new Solution56_2().singleNumber(nums));
    }
}
