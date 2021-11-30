package 高效制胜.day4;

/**
 * <p>
 *  53. 最大子序和
 * </p>
 *
 * @author wfb
 * @version 1.0
 * @since 2021-11-06 23:42:36
 */
public class Solution53 {
    public int maxSubArray(int[] nums) {
        if (nums == null || nums.length == 0) return Integer.MIN_VALUE;
        int dp = nums[0], max = nums[0];
        for (int i = 1; i < nums.length; i++) {
            dp = Math.max(dp + nums[i], nums[i]);
            max = Math.max(max, dp);
        }
        return max;
    }

    public static void main(String[] args) {
        Solution53 solution53 = new Solution53();
        int[] nums = {-2, 1, -3, 4, -1, 2, 1, -5, 4};
        System.out.println(solution53.maxSubArray(nums));
    }
}
