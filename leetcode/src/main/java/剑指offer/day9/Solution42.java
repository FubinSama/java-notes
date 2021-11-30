package 剑指offer.day9;

/**
 * <p>
 *  剑指 Offer 42. 连续子数组的最大和
 * </p>
 *
 * @author wfb
 * @version 1.0
 * @since 2021-11-10 20:13:46
 */
public class Solution42 {
    public int maxSubArray(int[] nums) {
        int max = nums[0];
        int curMax = nums[0];
        for (int i = 1; i < nums.length; i++) {
            if (curMax + nums[i] > nums[i]) curMax += nums[i];
            else curMax = nums[i];
            max = Math.max(max, curMax);
        }
        return max;
    }

    public static void main(String[] args) {
        Solution42 solution42 = new Solution42();
        int[] nums = {-2, 1, -3, 4, -1, 2, 1, -5, 4};
        System.out.println(solution42.maxSubArray(nums));
    }

}
