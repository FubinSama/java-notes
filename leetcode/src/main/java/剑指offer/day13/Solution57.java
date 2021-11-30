package 剑指offer.day13;

/**
 * <p>
 *  剑指 Offer 57. 和为s的两个数字
 * </p>
 *
 * @author wfb
 * @version 1.0
 * @since 2021-11-15 00:34:21
 */
public class Solution57 {
    public int[] twoSum(int[] nums, int target) {
        if (nums.length < 2) return new int[0];
        int[] res = new int[2];
        int left = 0, right = nums.length - 1;
        while (left < right) {
            int sum = nums[left] + nums[right];
            if (sum == target) return new int[]{nums[left], nums[right]};
            else if (sum < target) left++;
            else right--;
        }

        return res;
    }

    public static void main(String[] args) {
        int[] nums = {2, 7, 11, 15};
        int target = 9;
        Solution57 solution57 = new Solution57();
        int[] res = solution57.twoSum(nums, target);
        System.out.println(res[0] + " " + res[1]);
    }

}
